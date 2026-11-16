package top.ireed.scene02.common.verify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import top.ireed.scene02.common.config.Scene02Properties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 案例验证器
 * <p>
 * 按方案文档逐条验证预期场景,全部通过HTTP调用双方真实接口完成,验证的是运行中的链路而不是内部方法。
 * 案例清单:
 * C01 双方首次拉取对端证书并缓存
 * C02 当日重复请求命中缓存不再真实拉取
 * C03 平台方加密报文送达资方方并解密成功
 * C04 资方方加密报文送达平台方并解密成功
 * C05 证书轮换直接覆盖,同一时刻只有一张证书在用
 * C06 轮换后旧缓存导致解密失败,强制刷新重试恢复
 * C07 过期证书被有效期校验拒绝
 * C08 非受信链证书被证书链校验拒绝
 * C09 证书接口不可用时降级使用有效期内缓存
 * C10 证书临期触发更换提醒
 * C11 无缓存且拉取失败时请求失败并告警
 * C12 双方互相校验对方证书全部通过
 * C13 定时任务按周期执行证书交换
 */
public class VerifyRunner {

    private static final Logger log = LoggerFactory.getLogger(VerifyRunner.class);

    private final Scene02Properties props;

    private final RestTemplate restTemplate;

    public VerifyRunner(Scene02Properties props) {
        this.props = props;
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(15000);
        this.restTemplate = new RestTemplate(factory);
    }

    /**
     * 执行全部案例
     *
     * @return 案例结果列表
     */
    public List<CaseResult> run() {
        List<CaseResult> results = new ArrayList<>();
        results.add(execute("C01", "双方首次拉取对端证书并缓存", "§3.1 证书分发流", this::caseFirstPull));
        results.add(execute("C02", "当日重复请求命中缓存不再真实拉取", "§3.1 拉取策略", this::caseDailyCache));
        results.add(execute("C03", "平台方加密报文送达资方方并解密成功", "§3.2 业务报文加解交流", this::casePlatformToFund));
        results.add(execute("C04", "资方方加密报文送达平台方并解密成功", "§3.2 业务报文加解交流", this::caseFundToPlatform));
        results.add(execute("C05", "证书轮换直接覆盖不共存", "§4.1 双方轮换规则", this::caseRotateOverwrite));
        results.add(execute("C06", "轮换后旧缓存解密失败强制刷新重试恢复", "§4.2 轮换时序 / R1", this::caseRotateThenRefresh));
        results.add(execute("C07", "过期证书被有效期校验拒绝", "§2 / §5 证书校验", this::caseExpiredCert));
        results.add(execute("C08", "非受信链证书被证书链校验拒绝", "§2 / §5 证书校验", this::caseBadChainCert));
        results.add(execute("C09", "证书接口不可用降级使用有效期内缓存", "§4.3 R5", this::casePullFailFallback));
        results.add(execute("C10", "证书临期触发更换提醒", "§4.1 到期前提醒", this::caseExpiryWarn));
        results.add(execute("C11", "无缓存且拉取失败请求失败告警", "§4.3 R5", this::casePullFailNoCache));
        results.add(execute("C12", "双方互相校验对方证书全部通过", "§2 双方校验", this::caseBothValidate));
        results.add(execute("C13", "定时任务按周期执行证书交换", "§3.1 定时交换", this::caseScheduledJob));
        return results;
    }

    private interface CaseBody {
        String run();
    }

    private CaseResult execute(String id, String name, String docRef, CaseBody body) {
        CaseResult result = new CaseResult(id, name, docRef);
        long start = System.currentTimeMillis();
        try {
            String detail = body.run();
            result.setPassed(detail.startsWith("PASS"));
            result.setDetail(detail.startsWith("PASS") ? detail.substring(5) : detail);
        } catch (Exception e) {
            result.setPassed(false);
            result.setDetail("案例执行异常:" + e.getMessage());
        }
        result.setCostMs(System.currentTimeMillis() - start);
        log.info("案例{} {} 结果={} 耗时={}ms", id, name, result.isPassed() ? "PASS" : "FAIL", result.getCostMs());
        return result;
    }

    /**
     * C01 双方首次拉取对端证书并缓存
     */
    private String caseFirstPull() {
        clearCache(platform());
        clearCache(fund());
        Map<String, Object> pullPlatform = pull(platform(), true);
        Map<String, Object> pullFund = pull(fund(), true);
        if (!Boolean.TRUE.equals(pullPlatform.get("success")) || !Boolean.TRUE.equals(pullFund.get("success"))) {
            return "FAIL 首次拉取失败 platform=" + pullPlatform.get("message") + " fund=" + pullFund.get("message");
        }
        String platformPeer = String.valueOf(peerInfo(platform()).get("fingerprint"));
        String fundPeer = String.valueOf(peerInfo(fund()).get("fingerprint"));
        String fundLocal = String.valueOf(localInfo(fund()).get("fingerprint"));
        String platformLocal = String.valueOf(localInfo(platform()).get("fingerprint"));
        if (!platformPeer.equals(fundLocal) || !fundPeer.equals(platformLocal)) {
            return "FAIL 证书指纹不匹配 platform缓存=" + platformPeer + " fundLocal=" + fundLocal;
        }
        return "PASS 双方首次拉取成功 平台方缓存资方证书=" + platformPeer + " 资方方缓存平台证书=" + fundPeer;
    }

    /**
     * C02 当日重复请求命中缓存
     */
    private String caseDailyCache() {
        clearCache(platform());
        int threshold = (int) peerInfo(platform()).get("dailyPullThreshold");
        for (int i = 0; i < threshold + 2; i++) {
            pull(platform(), false);
        }
        Map<String, Object> info = peerInfo(platform());
        int todayCount = (int) info.get("todayPullCount");
        if (todayCount != threshold) {
            return "FAIL 当日真实拉取次数应为" + threshold + " 实际=" + todayCount;
        }
        return "PASS 当日真实拉取次数达到上限" + threshold + "后不再增加(总次数=" + info.get("totalPullCount") + ")";
    }

    /**
     * C03 平台方加密报文送达资方方
     */
    private String casePlatformToFund() {
        String text = "平台方到资方方加密报文验证";
        Map<String, Object> result = send(platform(), text);
        if (!"0".equals(String.valueOf(result.get("code")))) {
            return "FAIL 平台方发送失败 code=" + result.get("code") + " message=" + result.get("message");
        }
        if (!text.equals(result.get("plainText"))) {
            return "FAIL 解密明文不一致 plainText=" + result.get("plainText");
        }
        return "PASS 资方方解密成功 明文一致 keyId=" + result.get("keyId");
    }

    /**
     * C04 资方方加密报文送达平台方
     */
    private String caseFundToPlatform() {
        String text = "资方方到平台方加密报文验证";
        Map<String, Object> result = send(fund(), text);
        if (!"0".equals(String.valueOf(result.get("code")))) {
            return "FAIL 资方方发送失败 code=" + result.get("code") + " message=" + result.get("message");
        }
        if (!text.equals(result.get("plainText"))) {
            return "FAIL 解密明文不一致 plainText=" + result.get("plainText");
        }
        return "PASS 平台方解密成功 明文一致 keyId=" + result.get("keyId");
    }

    /**
     * C05 证书轮换直接覆盖
     */
    private String caseRotateOverwrite() {
        String oldFund = String.valueOf(localInfo(fund()).get("fingerprint"));
        rotate(fund());
        String newFund = String.valueOf(localInfo(fund()).get("fingerprint"));
        if (oldFund.equals(newFund)) {
            return "FAIL 轮换后指纹未变化";
        }
        pull(platform(), true);
        String platformCached = String.valueOf(peerInfo(platform()).get("fingerprint"));
        if (!newFund.equals(platformCached)) {
            return "FAIL 平台方未获取到新证书 cached=" + platformCached + " 期望=" + newFund;
        }
        return "PASS 旧证书" + oldFund.substring(0, 12) + "已被覆盖 新证书" + newFund.substring(0, 12) + " 无并存窗口";
    }

    /**
     * C06 轮换后旧缓存导致解密失败,强制刷新重试恢复
     */
    private String caseRotateThenRefresh() {
        pull(platform(), true);
        String oldFp = String.valueOf(peerInfo(platform()).get("fingerprint"));
        rotate(fund());
        String text = "轮换后旧缓存验证";
        Map<String, Object> result = send(platform(), text);
        if (!"0".equals(String.valueOf(result.get("code")))) {
            return "FAIL 强制刷新重试后仍未成功 code=" + result.get("code") + " message=" + result.get("message");
        }
        if (!logContains(platform(), "强制刷新证书缓存后重试")) {
            return "FAIL 未记录强制刷新重试动作";
        }
        if (!logContains(fund(), "解密失败")) {
            return "FAIL 资方方未记录解密失败";
        }
        String newFp = String.valueOf(peerInfo(platform()).get("fingerprint"));
        if (oldFp.equals(newFp)) {
            return "FAIL 平台方证书缓存未刷新";
        }
        return "PASS 旧证书" + oldFp.substring(0, 12) + "解密失败后强制刷新为" + newFp.substring(0, 12) + "重试成功";
    }

    /**
     * C07 过期证书被拒绝
     */
    private String caseExpiredCert() {
        try {
            setFault(fund(), "EXPIRED_CERT");
            clearCache(platform());
            Map<String, Object> result = pull(platform(), true);
            if (Boolean.TRUE.equals(result.get("success"))) {
                return "FAIL 过期证书被接受了";
            }
            String message = String.valueOf(result.get("message"));
            if (!message.contains("过期")) {
                return "FAIL 失败原因不是有效期问题 message=" + message;
            }
            return "PASS 过期证书被拒绝 原因=" + message;
        } finally {
            setFault(fund(), "NONE");
        }
    }

    /**
     * C08 非受信链证书被拒绝
     */
    private String caseBadChainCert() {
        try {
            setFault(fund(), "BAD_CHAIN_CERT");
            clearCache(platform());
            Map<String, Object> result = pull(platform(), true);
            if (Boolean.TRUE.equals(result.get("success"))) {
                return "FAIL 非受信链证书被接受了";
            }
            String message = String.valueOf(result.get("message"));
            if (!message.contains("签发者不匹配") && !message.contains("校验")) {
                return "FAIL 失败原因不是证书链问题 message=" + message;
            }
            return "PASS 非受信链证书被拒绝 原因=" + message;
        } finally {
            setFault(fund(), "NONE");
        }
    }

    /**
     * C09 拉取失败降级使用缓存
     */
    private String casePullFailFallback() {
        try {
            setFault(fund(), "NONE");
            clearCache(platform());
            pull(platform(), true);
            String cachedFp = String.valueOf(peerInfo(platform()).get("fingerprint"));
            setFault(fund(), "CERT_HTTP_500");
            Map<String, Object> result = pull(platform(), true);
            if (!Boolean.TRUE.equals(result.get("success"))) {
                return "FAIL 拉取失败后未降级使用缓存 message=" + result.get("message");
            }
            if (!Boolean.TRUE.equals(result.get("degraded"))) {
                return "FAIL 未标记为降级使用缓存";
            }
            if (!cachedFp.equals(String.valueOf(result.get("fingerprint")))) {
                return "FAIL 降级使用的不是原缓存证书";
            }
            return "PASS 证书接口500时降级使用有效期内缓存 " + cachedFp.substring(0, 12);
        } finally {
            setFault(fund(), "NONE");
        }
    }

    /**
     * C10 临期提醒
     */
    private String caseExpiryWarn() {
        try {
            setFault(fund(), "EXPIRING_CERT");
            clearCache(platform());
            Map<String, Object> result = pull(platform(), true);
            if (!Boolean.TRUE.equals(result.get("success"))) {
                return "FAIL 临期证书未被接受 message=" + result.get("message");
            }
            if (!logContains(platform(), "即将到期")) {
                return "FAIL 未产生到期前提醒记录";
            }
            return "PASS 剩余有效期" + result.get("remainDays") + "天 触发更换提醒";
        } finally {
            setFault(fund(), "NONE");
        }
    }

    /**
     * C11 无缓存且拉取失败
     */
    private String casePullFailNoCache() {
        try {
            setFault(fund(), "CERT_HTTP_500");
            clearCache(platform());
            Map<String, Object> result = pull(platform(), true);
            if (Boolean.TRUE.equals(result.get("success"))) {
                return "FAIL 无缓存时不应返回成功";
            }
            if (!logContains(platform(), "拉取失败且无可用缓存")) {
                return "FAIL 未记录无可用缓存的告警";
            }
            return "PASS 无缓存且拉取失败时请求失败并记录告警 message=" + result.get("message");
        } finally {
            setFault(fund(), "NONE");
        }
    }

    /**
     * C12 双方互相校验通过
     */
    private String caseBothValidate() {
        clearCache(platform());
        clearCache(fund());
        Map<String, Object> pullPlatform = pull(platform(), true);
        Map<String, Object> pullFund = pull(fund(), true);
        if (!Boolean.TRUE.equals(pullPlatform.get("success")) || !Boolean.TRUE.equals(pullFund.get("success"))) {
            return "FAIL 双向校验未全部通过";
        }
        return "PASS 平台方校验资方证书通过 资方方校验平台证书通过";
    }

    /**
     * C13 定时任务
     */
    private String caseScheduledJob() {
        boolean platformJob = logContains(platform(), "定时证书交换");
        boolean fundJob = logContains(fund(), "定时证书交换");
        if (!platformJob || !fundJob) {
            return "FAIL 定时任务尚未执行 platform=" + platformJob + " fund=" + fundJob
                    + " 间隔=" + peerInfo(platform()).get("exchangeIntervalMs") + "ms";
        }
        return "PASS 双方定时任务均已执行证书交换";
    }

    // ------------------------------------------------------------------ 工具方法

    private String platform() {
        return props.getPlatformBaseUrl();
    }

    private String fund() {
        return props.getFundBaseUrl();
    }

    private Map<String, Object> pull(String base, boolean force) {
        return safePost(base + "/scene02/api/admin/pull?force=" + force);
    }

    private Map<String, Object> send(String base, String text) {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("text", text);
        return safePostJson(base + "/scene02/api/admin/send", body);
    }

    private Map<String, Object> rotate(String base) {
        return safePost(base + "/scene02/api/admin/rotate");
    }

    private Map<String, Object> setFault(String base, String mode) {
        return safePost(base + "/scene02/api/admin/fault?mode=" + mode);
    }

    private Map<String, Object> clearCache(String base) {
        return safePost(base + "/scene02/api/admin/cache/clear");
    }

    private Map<String, Object> localInfo(String base) {
        return safeGet(base + "/scene02/api/admin/cert/local");
    }

    private Map<String, Object> peerInfo(String base) {
        return safeGet(base + "/scene02/api/admin/cert/peer");
    }

    private boolean logContains(String base, String keyword) {
        try {
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    base + "/scene02/api/admin/log",
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {
                    });
            List<Map<String, Object>> records = response.getBody();
            if (records == null) {
                return false;
            }
            for (Map<String, Object> record : records) {
                Object detail = record.get("detail");
                if (detail != null && String.valueOf(detail).contains(keyword)) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.warn("读取流水失败 {}", e.getMessage());
        }
        return false;
    }

    private Map<String, Object> safeGet(String url) {
        try {
            Map<String, Object> body = restTemplate.getForObject(url, Map.class);
            return body == null ? new LinkedHashMap<>() : body;
        } catch (Exception e) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }

    private Map<String, Object> safePost(String url) {
        try {
            Map<String, Object> body = restTemplate.postForObject(url, new LinkedHashMap<String, Object>(), Map.class);
            return body == null ? new LinkedHashMap<>() : body;
        } catch (Exception e) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }

    /**
     * 以JSON请求体发起POST,避免中文被二次URL编码
     *
     * @param url  地址
     * @param body 请求体
     * @return 响应体
     */
    private Map<String, Object> safePostJson(String url, Map<String, String> body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
            Map<String, Object> result = restTemplate.postForObject(url, entity, Map.class);
            return result == null ? new LinkedHashMap<>() : result;
        } catch (Exception e) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }
}

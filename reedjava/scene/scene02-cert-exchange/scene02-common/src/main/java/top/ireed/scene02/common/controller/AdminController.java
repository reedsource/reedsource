package top.ireed.scene02.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.scene02.common.model.CertPayload;
import top.ireed.scene02.common.model.FaultMode;
import top.ireed.scene02.common.model.MessageResult;
import top.ireed.scene02.common.service.CertParty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景管理接口
 * <p>
 * 用于演练与验证:证书轮换、故障注入、缓存清理、证书与流水查询。
 * 平台方与资方方暴露同构接口。
 */
@RestController
@RequestMapping("/scene02/api/admin")
public class AdminController {

    private final CertParty party;

    public AdminController(CertParty party) {
        this.party = party;
    }

    /**
     * 本端证书信息
     *
     * @return 证书信息
     */
    @GetMapping("/cert/local")
    public Map<String, Object> localCert() {
        CertPayload payload = party.localStore().payload();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("role", party.role());
        result.put("fingerprint", payload.getFingerprint());
        result.put("subject", payload.getSubject());
        result.put("issuer", payload.getIssuer());
        result.put("notBefore", payload.getNotBefore());
        result.put("notAfter", payload.getNotAfter());
        result.put("remainDays", payload.remainDays());
        return result;
    }

    /**
     * 对端证书缓存与拉取统计
     *
     * @return 缓存信息
     */
    @GetMapping("/cert/peer")
    public Map<String, Object> peerCert() {
        CertPayload cached = party.peerCache().payload();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("role", party.role());
        result.put("cached", cached != null);
        result.put("fingerprint", cached == null ? null : cached.getFingerprint());
        result.put("notAfter", cached == null ? null : cached.getNotAfter());
        result.put("remainDays", cached == null ? null : cached.remainDays());
        result.put("lastPullDate", party.peerCache().lastPullDate());
        result.put("todayPullCount", party.peerCache().todayPullCount());
        result.put("totalPullCount", party.peerCache().totalPullCount());
        result.put("dailyPullThreshold", party.peerCache().dailyPullThreshold());
        result.put("needPullToday", party.peerCache().needPullToday());
        result.put("lastError", party.peerCache().lastError());
        result.put("faultMode", party.faultMode().name());
        return result;
    }

    /**
     * 轮换本端证书 直接覆盖
     *
     * @return 轮换后的证书信息
     */
    @PostMapping("/rotate")
    public Map<String, Object> rotate() {
        party.rotate();
        return localCert();
    }

    /**
     * 注入故障模式
     *
     * @param mode 故障模式 NONE/CERT_HTTP_500/EXPIRED_CERT/EXPIRING_CERT/BAD_CHAIN_CERT
     * @return 当前故障模式
     */
    @PostMapping("/fault")
    public Map<String, Object> fault(@RequestParam(value = "mode", required = false) String mode) {
        FaultMode target = FaultMode.NONE;
        if (mode != null && !mode.trim().isEmpty()) {
            try {
                target = FaultMode.valueOf(mode.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                Map<String, Object> error = new LinkedHashMap<>();
                error.put("success", false);
                error.put("message", "不支持的故障模式:" + mode);
                return error;
            }
        }
        party.setFaultMode(target);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("faultMode", party.faultMode().name());
        return result;
    }

    /**
     * 清空对端证书缓存
     *
     * @return 操作结果
     */
    @PostMapping("/cache/clear")
    public Map<String, Object> clearCache() {
        party.clearPeerCache();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("message", "对端证书缓存已清空");
        return result;
    }

    /**
     * 证书交换流水
     *
     * @return 流水列表
     */
    @GetMapping("/log")
    public Object log() {
        return party.exchangeLog().list();
    }

    /**
     * 场景存活信息
     *
     * @return 存活信息
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("role", party.role());
        result.put("peerBaseUrl", party.props().getPeerBaseUrl());
        result.put("peerCertPath", party.props().getPeerCertPath());
        result.put("selfCertPath", party.props().getSelfCertPath());
        result.put("exchangeIntervalMs", party.props().getExchangeIntervalMs());
        result.put("dailyPullThreshold", party.props().getDailyPullThreshold());
        result.put("faultMode", party.faultMode().name());
        return result;
    }

    /**
     * 主动拉取对端证书
     *
     * @param force 是否强制刷新 绕过每日拉取次数限制
     * @return 拉取结果
     */
    @PostMapping("/pull")
    public Map<String, Object> pull(@RequestParam(value = "force", defaultValue = "false") boolean force) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            CertPayload payload = party.peerCert(force);
            result.put("success", true);
            result.put("fingerprint", payload.getFingerprint());
            result.put("notAfter", payload.getNotAfter());
            result.put("remainDays", payload.remainDays());
            // 缓存中残留错误信息说明本次是降级使用旧缓存
            result.put("degraded", party.peerCache().lastError() != null);
            result.put("message", "拉取成功");
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("degraded", false);
            result.put("message", e.getMessage());
            return result;
        }
    }

    /**
     * 向对端发送加密报文
     *
     * @param body 请求体 {"text":"明文"}
     * @return 对端响应
     */
    @PostMapping("/send")
    public Map<String, Object> send(@RequestBody Map<String, String> body) {
        Map<String, Object> result = new LinkedHashMap<>();
        String text = body == null ? null : body.get("text");
        try {
            MessageResult response = party.sendToPeer(text == null ? "" : text);
            result.put("code", response.getCode());
            result.put("message", response.getMessage());
            result.put("plainText", response.getPlainText());
            result.put("keyId", response.getKeyId());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
            result.put("plainText", null);
        }
        return result;
    }
}

package top.ireed.scene02.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import top.ireed.scene02.common.cert.CertCache;
import top.ireed.scene02.common.cert.CertExchangeLog;
import top.ireed.scene02.common.cert.CertGenerator;
import top.ireed.scene02.common.cert.CertValidationException;
import top.ireed.scene02.common.cert.CertValidator;
import top.ireed.scene02.common.cert.LocalCertStore;
import top.ireed.scene02.common.cert.TrustRootProvider;
import top.ireed.scene02.common.config.Scene02Properties;
import top.ireed.scene02.common.crypto.EnvelopeCrypto;
import top.ireed.scene02.common.model.CertPayload;
import top.ireed.scene02.common.model.EncryptedEnvelope;
import top.ireed.scene02.common.model.FaultMode;
import top.ireed.scene02.common.model.MessageResult;

import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 证书交换参与方公共逻辑
 * <p>
 * 平台方与资方方在这一层完全同构,差异只体现在角色配置与暴露的接口路径上。
 * 覆盖方案文档中的:证书下发、缓存与每日拉取策略、加解密、轮换、校验失败处理、拉取失败降级。
 */
public class CertParty {

    private static final Logger log = LoggerFactory.getLogger(CertParty.class);

    protected final Scene02Properties props;

    protected final CertExchangeLog exchangeLog;

    private final TrustRootProvider trustRoot;

    private final LocalCertStore localStore;

    private final CertCache peerCache;

    private final RestTemplate restTemplate;

    private final AtomicLong exchangeSequence = new AtomicLong();

    private volatile FaultMode faultMode = FaultMode.NONE;

    public CertParty(Scene02Properties props, CertExchangeLog exchangeLog, TrustRootProvider trustRoot) {
        this.props = props;
        this.exchangeLog = exchangeLog;
        this.trustRoot = trustRoot;
        props.applyRoleDefaults();
        this.localStore = new LocalCertStore(props.getRole(), props.getCertValidityDays(), trustRoot);
        this.peerCache = new CertCache(props.getDailyPullThreshold());
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(props.getPeerTimeoutMs());
        factory.setReadTimeout(props.getPeerTimeoutMs());
        this.restTemplate = new RestTemplate(factory);
    }

    /**
     * 本端角色
     *
     * @return platform 或 fund
     */
    public String role() {
        return props.getRole();
    }

    /**
     * 场景配置
     *
     * @return 配置
     */
    public Scene02Properties props() {
        return props;
    }

    /**
     * 交换流水
     *
     * @return 流水
     */
    public CertExchangeLog exchangeLog() {
        return exchangeLog;
    }

    /**
     * 共享信任根
     *
     * @return 信任根
     */
    public TrustRootProvider trustRoot() {
        return trustRoot;
    }

    /**
     * 本端证书仓库
     *
     * @return 证书仓库
     */
    public LocalCertStore localStore() {
        return localStore;
    }

    /**
     * 对端证书缓存
     *
     * @return 缓存
     */
    public CertCache peerCache() {
        return peerCache;
    }

    /**
     * 当前故障注入模式
     *
     * @return 故障模式
     */
    public FaultMode faultMode() {
        return faultMode;
    }

    /**
     * 注入故障模式
     *
     * @param mode 故障模式
     */
    public void setFaultMode(FaultMode mode) {
        this.faultMode = mode == null ? FaultMode.NONE : mode;
        exchangeLog.add("FAULT", role(), null, "SUCCESS", "故障模式切换为:" + this.faultMode);
        log.warn("故障模式切换为:{}", this.faultMode);
    }

    /**
     * 本端对外提供的证书
     * <p>
     * 对应8.1.1 / 8.1.2证书接口的返回体,按故障模式返回不同证书用于验证异常场景。
     *
     * @return 证书载荷
     */
    public CertPayload selfPayload() {
        switch (faultMode) {
            case EXPIRED_CERT:
                return localStore.expiredPayload();
            case EXPIRING_CERT:
                return localStore.expiringPayload();
            case BAD_CHAIN_CERT:
                return localStore.badChainPayload();
            default:
                return localStore.payload();
        }
    }

    /**
     * 获取对端证书
     * <p>
     * force=true 表示强制刷新,绕过每日拉取次数限制,用于解密失败后的重试。
     *
     * @param force 是否强制刷新
     * @return 对端证书
     * @throws Exception 拉取或校验失败且无可用缓存
     */
    public CertPayload peerCert(boolean force) throws Exception {
        try {
            CertPayload payload = peerCache.get(force, this::fetchPeerCert);
            checkExpiryWarn(payload);
            return payload;
        } catch (CertValidationException e) {
            // 校验不通过属于安全事件,不做缓存降级,直接拒绝
            exchangeLog.add("PULL_CERT", role(), null, "FAIL", "证书校验不通过 拒绝使用 原因:" + e.getMessage());
            throw e;
        } catch (Exception e) {
            CertPayload cached = peerCache.payload();
            if (cached != null && cached.getNotAfter() > System.currentTimeMillis()) {
                exchangeLog.add("PULL_CERT", role(), cached.getFingerprint(), "FAIL",
                        "拉取失败降级使用有效期内缓存 原因:" + e.getMessage());
                log.warn("拉取对端证书失败,降级使用缓存 原因={}", e.getMessage());
                return cached;
            }
            exchangeLog.add("PULL_CERT", role(), null, "FAIL", "拉取失败且无可用缓存 原因:" + e.getMessage());
            throw e;
        }
    }

    private CertPayload fetchPeerCert() throws Exception {
        String url = props.getPeerBaseUrl() + props.getPeerCertPath();
        CertPayload payload = restTemplate.getForObject(url, CertPayload.class);
        if (payload == null) {
            throw new IllegalStateException("对端证书接口返回为空");
        }
        CertValidator.validate(payload, trustRoot.rootCert());
        exchangeLog.add("PULL_CERT", role(), payload.getFingerprint(), "SUCCESS", "拉取并校验通过");
        return payload;
    }

    private void checkExpiryWarn(CertPayload payload) {
        long remain = payload.remainDays();
        if (remain <= props.getExpiryWarnDays()) {
            exchangeLog.add("WARN", role(), payload.getFingerprint(), "FAIL",
                    "证书即将到期 剩余有效期" + remain + "天 低于提醒阈值" + props.getExpiryWarnDays() + "天 请提前更换");
            log.warn("对端证书即将到期 剩余{}天 指纹={}", remain, payload.getFingerprint());
        }
    }

    /**
     * 使用对端公钥加密报文体
     *
     * @param plainText 明文
     * @return 加密信封
     * @throws Exception 加密失败
     */
    public EncryptedEnvelope encrypt(String plainText) throws Exception {
        CertPayload peer = peerCache.payload();
        if (peer == null) {
            peer = peerCert(false);
        }
        X509Certificate cert = CertGenerator.fromBase64(peer.getChainBase64().get(0));
        EncryptedEnvelope envelope = EnvelopeCrypto.encrypt(plainText, cert.getPublicKey(), peer.getFingerprint());
        exchangeLog.add("ENCRYPT", role(), peer.getFingerprint(), "SUCCESS", "使用对端公钥加密报文体");
        return envelope;
    }

    /**
     * 使用本端私钥解密报文体
     *
     * @param envelope 加密信封
     * @return 解密结果 失败时code=409
     */
    public MessageResult decrypt(EncryptedEnvelope envelope) {
        MessageResult result = new MessageResult();
        try {
            String plainText = EnvelopeCrypto.decrypt(envelope, localStore.privateKey());
            result.setCode(MessageResult.CODE_SUCCESS);
            result.setMessage("success");
            result.setPlainText(plainText);
            result.setKeyId(localStore.payload().getFingerprint());
            exchangeLog.add("DECRYPT", role(), envelope.getKeyId(), "SUCCESS", "解密成功");
        } catch (Exception e) {
            result.setCode(MessageResult.CODE_CERT_MISMATCH);
            result.setMessage("证书不一致或解密失败:" + e.getMessage());
            exchangeLog.add("DECRYPT", role(), envelope.getKeyId(), "FAIL", "解密失败 原因:" + e.getMessage());
            log.warn("解密失败,判定为证书不一致 原因={}", e.getMessage());
        }
        return result;
    }

    /**
     * 向对端发送加密报文
     * <p>
     * 对端返回证书不一致时,强制刷新对端证书缓存并重试一次。
     *
     * @param plainText 明文
     * @return 对端响应
     * @throws Exception 通信失败
     */
    public MessageResult sendToPeer(String plainText) throws Exception {
        EncryptedEnvelope envelope = encrypt(plainText);
        MessageResult response = post(envelope);
        if (response.getCode() == MessageResult.CODE_CERT_MISMATCH) {
            exchangeLog.add("ENCRYPT", role(), envelope.getKeyId(), "FAIL",
                    "对端提示证书不一致 强制刷新证书缓存后重试一次");
            peerCert(true);
            envelope = encrypt(plainText);
            response = post(envelope);
        }
        exchangeLog.add("ENCRYPT", role(), envelope.getKeyId(),
                response.getCode() == MessageResult.CODE_SUCCESS ? "SUCCESS" : "FAIL",
                "报文发送结果:" + response.getMessage());
        return response;
    }

    private MessageResult post(EncryptedEnvelope envelope) throws Exception {
        String url = props.getPeerBaseUrl() + props.getMessagePath();
        MessageResult response = restTemplate.postForObject(url, envelope, MessageResult.class);
        if (response == null) {
            throw new IllegalStateException("对端报文接口返回为空");
        }
        return response;
    }

    /**
     * 轮换本端证书 直接覆盖 旧证书立即失效
     */
    public void rotate() {
        localStore.rotate();
        CertPayload current = localStore.payload();
        exchangeLog.add("ROTATE", role(), current.getFingerprint(), "SUCCESS",
                "本端证书轮换完成 直接覆盖 不存在新旧共存 新指纹=" + current.getFingerprint());
        log.info("本端证书轮换完成 新指纹={}", current.getFingerprint());
    }

    /**
     * 清空对端证书缓存
     */
    public void clearPeerCache() {
        peerCache.clear();
        exchangeLog.add("CACHE", role(), null, "SUCCESS", "清空对端证书缓存");
    }

    /**
     * 定时任务:执行一次证书交换
     * <p>
     * 拉取对端证书(按每日策略) -> 用对端公钥加密 -> 发送对端 -> 校验解密结果。
     */
    public void doExchange() {
        try {
            CertPayload peer = peerCert(false);
            long seq = exchangeSequence.incrementAndGet();
            String plainText = "{\"from\":\"" + role() + "\",\"seq\":" + seq + ",\"time\":" + System.currentTimeMillis() + "}";
            MessageResult response = sendToPeer(plainText);
            if (response.getCode() == MessageResult.CODE_SUCCESS) {
                exchangeLog.add("EXCHANGE", role(), peer.getFingerprint(), "SUCCESS", "定时证书交换成功 序号=" + seq);
                log.info("证书交换成功 角色={} 对端证书指纹={}", role(), peer.getFingerprint());
            } else {
                exchangeLog.add("EXCHANGE", role(), peer.getFingerprint(), "FAIL",
                        "定时证书交换未通过 序号=" + seq + " 对端返回=" + response.getMessage());
                log.warn("证书交换未通过对端返回={}", response.getMessage());
            }
        } catch (Exception e) {
            exchangeLog.add("ERROR", role(), null, "FAIL", "定时证书交换失败:" + e.getMessage());
            log.error("定时证书交换失败", e);
        }
    }
}

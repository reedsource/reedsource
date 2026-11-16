package top.ireed.scene02.common.model;

/**
 * 故障注入模式
 * <p>
 * 用于在验证环境中模拟方案文档中记录的异常场景,生产环境不启用。
 */
public enum FaultMode {

    /**
     * 正常
     */
    NONE,

    /**
     * 证书接口返回500 模拟证书服务不可用
     */
    CERT_HTTP_500,

    /**
     * 返回已过期证书 模拟有效期校验失败
     */
    EXPIRED_CERT,

    /**
     * 返回临期证书 剩余有效期不足 触发到期前提醒
     */
    EXPIRING_CERT,

    /**
     * 返回非受信根签发的证书 模拟证书链校验失败
     */
    BAD_CHAIN_CERT
}

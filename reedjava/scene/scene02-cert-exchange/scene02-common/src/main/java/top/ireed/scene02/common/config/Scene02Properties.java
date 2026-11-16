package top.ireed.scene02.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 场景02配置项
 * <p>
 * 平台方与资方方各有一份独立配置文件(application-platform.properties / application-fund.properties),
 * 角色相关项在各自配置文件中显式声明,此处不再设默认角色值。
 */
@ConfigurationProperties(prefix = "scene02")
public class Scene02Properties {

    /**
     * 本端角色 platform=平台方 fund=资方方
     */
    private String role;

    /**
     * 对端服务地址
     */
    private String peerBaseUrl;

    /**
     * 对端证书接口路径
     */
    private String peerCertPath;

    /**
     * 本端证书接口路径
     */
    private String selfCertPath;

    /**
     * 报文接收路径 双方同路径
     */
    private String messagePath = "/scene02/api/message";

    /**
     * 本端证书有效期 天
     */
    private int certValidityDays = 365;

    /**
     * 证书交换间隔 毫秒 默认5分钟
     */
    private long exchangeIntervalMs = 300000L;

    /**
     * 定时任务首次延迟 毫秒
     */
    private long exchangeInitialDelayMs = 5000L;

    /**
     * 每个自然日真实拉取对端证书的次数上限
     */
    private int dailyPullThreshold = 3;

    /**
     * 到期前提醒阈值 天
     */
    private int expiryWarnDays = 30;

    /**
     * 对端连接超时 毫秒
     */
    private int peerTimeoutMs = 5000;

    /**
     * 共享信任根证书库口令
     */
    private String trustRootPassword = "scene02";

    /**
     * 验证用例中平台方地址
     */
    private String platformBaseUrl = "http://localhost:8082";

    /**
     * 验证用例中资方方地址
     */
    private String fundBaseUrl = "http://localhost:8083";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPeerBaseUrl() {
        return peerBaseUrl;
    }

    public void setPeerBaseUrl(String peerBaseUrl) {
        this.peerBaseUrl = peerBaseUrl;
    }

    public String getPeerCertPath() {
        return peerCertPath;
    }

    public void setPeerCertPath(String peerCertPath) {
        this.peerCertPath = peerCertPath;
    }

    public String getSelfCertPath() {
        return selfCertPath;
    }

    public void setSelfCertPath(String selfCertPath) {
        this.selfCertPath = selfCertPath;
    }

    public String getMessagePath() {
        return messagePath;
    }

    public void setMessagePath(String messagePath) {
        this.messagePath = messagePath;
    }

    public int getCertValidityDays() {
        return certValidityDays;
    }

    public void setCertValidityDays(int certValidityDays) {
        this.certValidityDays = certValidityDays;
    }

    public long getExchangeIntervalMs() {
        return exchangeIntervalMs;
    }

    public void setExchangeIntervalMs(long exchangeIntervalMs) {
        this.exchangeIntervalMs = exchangeIntervalMs;
    }

    public long getExchangeInitialDelayMs() {
        return exchangeInitialDelayMs;
    }

    public void setExchangeInitialDelayMs(long exchangeInitialDelayMs) {
        this.exchangeInitialDelayMs = exchangeInitialDelayMs;
    }

    public int getDailyPullThreshold() {
        return dailyPullThreshold;
    }

    public void setDailyPullThreshold(int dailyPullThreshold) {
        this.dailyPullThreshold = dailyPullThreshold;
    }

    public int getExpiryWarnDays() {
        return expiryWarnDays;
    }

    public void setExpiryWarnDays(int expiryWarnDays) {
        this.expiryWarnDays = expiryWarnDays;
    }

    public int getPeerTimeoutMs() {
        return peerTimeoutMs;
    }

    public void setPeerTimeoutMs(int peerTimeoutMs) {
        this.peerTimeoutMs = peerTimeoutMs;
    }

    public String getTrustRootPassword() {
        return trustRootPassword;
    }

    public void setTrustRootPassword(String trustRootPassword) {
        this.trustRootPassword = trustRootPassword;
    }

    public String getPlatformBaseUrl() {
        return platformBaseUrl;
    }

    public void setPlatformBaseUrl(String platformBaseUrl) {
        this.platformBaseUrl = platformBaseUrl;
    }

    public String getFundBaseUrl() {
        return fundBaseUrl;
    }

    public void setFundBaseUrl(String fundBaseUrl) {
        this.fundBaseUrl = fundBaseUrl;
    }

    /**
     * 按角色补全未显式配置的角色相关项
     * <p>
     * 平台方(京东方):本端证书接口 8.1.1,对端为资方方 8.1.2
     * 资方方(中信):本端证书接口 8.1.2,对端为平台方 8.1.1
     */
    public void applyRoleDefaults() {
        if (this.role == null || this.role.trim().isEmpty()) {
            this.role = "platform";
        }
        boolean isFund = "fund".equalsIgnoreCase(this.role.trim());
        if (this.selfCertPath == null || this.selfCertPath.trim().isEmpty()) {
            this.selfCertPath = isFund ? "/scene02/api/8.1.2/fund-cert" : "/scene02/api/8.1.1/platform-cert";
        }
        if (this.peerCertPath == null || this.peerCertPath.trim().isEmpty()) {
            this.peerCertPath = isFund ? "/scene02/api/8.1.1/platform-cert" : "/scene02/api/8.1.2/fund-cert";
        }
        if (this.peerBaseUrl == null || this.peerBaseUrl.trim().isEmpty()) {
            this.peerBaseUrl = isFund ? "http://localhost:8082" : "http://localhost:8083";
        }
    }
}

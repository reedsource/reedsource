package top.ireed.scene02.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 证书传输载荷
 * <p>
 * 对应方案文档 8.1.1 / 8.1.2 两个证书接口的返回体,只承载公钥证书及链信息,不含私钥。
 */
public class CertPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 持有方角色 platform=平台方 fund=资方方
     */
    private String role;

    /**
     * 证书主体
     */
    private String subject;

    /**
     * 签发者
     */
    private String issuer;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * 生效时间 毫秒时间戳
     */
    private long notBefore;

    /**
     * 失效时间 毫秒时间戳
     */
    private long notAfter;

    /**
     * 证书指纹 SHA-256 十六进制
     */
    private String fingerprint;

    /**
     * 证书链 DER Base64 数组 第0个为叶子证书 其后为签发者
     */
    private List<String> chainBase64 = new ArrayList<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(long notBefore) {
        this.notBefore = notBefore;
    }

    public long getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(long notAfter) {
        this.notAfter = notAfter;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<String> getChainBase64() {
        return chainBase64;
    }

    public void setChainBase64(List<String> chainBase64) {
        this.chainBase64 = chainBase64;
    }

    /**
     * 剩余有效天数 用于到期前提醒
     *
     * @return 剩余天数 已过期返回负数
     */
    public long remainDays() {
        long ms = notAfter - System.currentTimeMillis();
        return ms / (24L * 60 * 60 * 1000);
    }
}

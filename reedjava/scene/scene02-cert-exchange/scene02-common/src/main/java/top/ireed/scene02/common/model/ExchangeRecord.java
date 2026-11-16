package top.ireed.scene02.common.model;

import java.io.Serializable;

/**
 * 证书交换流水
 * <p>
 * 记录每一次证书拉取、报文加解密、校验与异常,用于方案文档中各案例的证据留存。
 */
public class ExchangeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private long id;

    /**
     * 操作类型 PULL_CERT/ENCRYPT/DECRYPT/ROTATE/VALIDATE/WARN/ERROR/EXCHANGE/FAULT/CACHE
     */
    private String action;

    /**
     * 执行方角色
     */
    private String role;

    /**
     * 关联证书指纹
     */
    private String fingerprint;

    /**
     * 结果 SUCCESS/FAIL
     */
    private String result;

    /**
     * 描述信息
     */
    private String detail;

    /**
     * 发生时间 毫秒时间戳
     */
    private long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

package top.ireed.scene02.common.model;

import java.io.Serializable;

/**
 * 报文接收结果
 * <p>
 * code=0 解密成功;code=409 证书不一致(对端私钥与本地缓存证书不匹配),触发方应强制刷新证书缓存后重试。
 */
public class MessageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int CODE_SUCCESS = 0;

    /**
     * 证书不一致
     */
    public static final int CODE_CERT_MISMATCH = 409;

    /**
     * 解密失败
     */
    public static final int CODE_DECRYPT_FAILED = 500;

    private int code;

    private String message;

    /**
     * 解密后的明文
     */
    private String plainText;

    /**
     * 解密时证书指纹
     */
    private String keyId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}

package top.ireed.scene02.common.model;

import java.io.Serializable;

/**
 * 加密报文信封
 * <p>
 * 网关加解密后的传输结构:RSA加密对称密钥,AES加密报文体。
 * keyId为加密时使用的证书指纹,用于识别双方证书是否一致。
 */
public class EncryptedEnvelope implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 加密时使用的对端证书指纹
     */
    private String keyId;

    /**
     * 密钥算法
     */
    private String keyAlg;

    /**
     * 数据算法
     */
    private String cipherAlg;

    /**
     * RSA加密后的对称密钥 Base64
     */
    private String encKey;

    /**
     * 初始向量 Base64
     */
    private String iv;

    /**
     * 密文 Base64
     */
    private String cipherText;

    /**
     * 加密时间 毫秒时间戳
     */
    private long timestamp;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyAlg() {
        return keyAlg;
    }

    public void setKeyAlg(String keyAlg) {
        this.keyAlg = keyAlg;
    }

    public String getCipherAlg() {
        return cipherAlg;
    }

    public void setCipherAlg(String cipherAlg) {
        this.cipherAlg = cipherAlg;
    }

    public String getEncKey() {
        return encKey;
    }

    public void setEncKey(String encKey) {
        this.encKey = encKey;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

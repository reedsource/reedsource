package top.ireed.scene02.common.crypto;

import top.ireed.scene02.common.model.EncryptedEnvelope;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 信封加解密
 * <p>
 * 对应方案文档"加解密直接使用SDK封装好的方法"的实现形态:
 * RSA加密对称密钥 + 对称算法加密报文体,报文体密文与被加密的密钥一起以信封结构传输。
 */
public final class EnvelopeCrypto {

    /**
     * 密钥算法
     */
    public static final String KEY_ALG = "RSA/ECB/PKCS1Padding";

    /**
     * 数据算法
     */
    public static final String CIPHER_ALG = "AES/CBC/PKCS5Padding";

    /**
     * 对称密钥长度 bit
     */
    public static final int KEY_SIZE = 256;

    private static final SecureRandom RANDOM = new SecureRandom();

    private EnvelopeCrypto() {
    }

    /**
     * 加密报文体
     *
     * @param plainText 明文
     * @param peerKey   对端公钥
     * @param keyId     证书指纹
     * @return 加密信封
     * @throws GeneralSecurityException 加密失败
     */
    public static EncryptedEnvelope encrypt(String plainText, PublicKey peerKey, String keyId) throws GeneralSecurityException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE, RANDOM);
        SecretKeySpec secretKey = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");

        byte[] iv = new byte[16];
        RANDOM.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(CIPHER_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        Cipher keyCipher = Cipher.getInstance(KEY_ALG);
        keyCipher.init(Cipher.ENCRYPT_MODE, peerKey);
        byte[] encKey = keyCipher.doFinal(secretKey.getEncoded());

        EncryptedEnvelope envelope = new EncryptedEnvelope();
        envelope.setKeyId(keyId);
        envelope.setKeyAlg(KEY_ALG);
        envelope.setCipherAlg(CIPHER_ALG);
        envelope.setEncKey(Base64.getEncoder().encodeToString(encKey));
        envelope.setIv(Base64.getEncoder().encodeToString(iv));
        envelope.setCipherText(Base64.getEncoder().encodeToString(cipherBytes));
        envelope.setTimestamp(System.currentTimeMillis());
        return envelope;
    }

    /**
     * 解密报文体
     *
     * @param envelope   加密信封
     * @param privateKey 本端私钥
     * @return 明文
     * @throws GeneralSecurityException 解密失败
     */
    public static String decrypt(EncryptedEnvelope envelope, PrivateKey privateKey) throws GeneralSecurityException {
        byte[] encKey = Base64.getDecoder().decode(envelope.getEncKey());
        byte[] iv = Base64.getDecoder().decode(envelope.getIv());
        byte[] cipherText = Base64.getDecoder().decode(envelope.getCipherText());

        Cipher keyCipher = Cipher.getInstance(envelope.getKeyAlg());
        keyCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] keyBytes = keyCipher.doFinal(encKey);

        Cipher cipher = Cipher.getInstance(envelope.getCipherAlg());
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(iv));
        return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
    }
}

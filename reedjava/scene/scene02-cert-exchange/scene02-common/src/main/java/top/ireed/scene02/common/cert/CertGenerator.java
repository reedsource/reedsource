package top.ireed.scene02.common.cert;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;

/**
 * 证书生成工具
 * <p>
 * 使用BouncyCastle生成真实X509证书与证书链:
 * 根CA自签,叶子证书由根CA签发,并带SKI/AKI/KeyUsage/BasicConstraints扩展,可被JDK的PKIX校验器完整校验。
 */
public final class CertGenerator {

    /**
     * BouncyCastle 提供者
     */
    public static final Provider BC = new BouncyCastleProvider();

    /**
     * 密钥算法
     */
    public static final String KEY_ALG = "RSA";

    /**
     * 密钥长度
     */
    public static final int KEY_SIZE = 2048;

    /**
     * 签名算法
     */
    public static final String SIGN_ALG = "SHA256withRSA";

    private static final SecureRandom RANDOM = new SecureRandom();

    private CertGenerator() {
    }

    /**
     * 生成密钥对
     *
     * @return RSA密钥对
     * @throws Exception 密钥生成失败
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALG);
        generator.initialize(KEY_SIZE, RANDOM);
        return generator.generateKeyPair();
    }

    /**
     * 创建自签根CA证书
     *
     * @param keyPair    根密钥对
     * @param dn         主体名
     * @param notBefore  生效时间
     * @param notAfter   失效时间
     * @return 根证书
     * @throws Exception 生成失败
     */
    public static X509Certificate createCa(KeyPair keyPair, String dn, Date notBefore, Date notAfter) throws Exception {
        X500Name name = new X500Name(dn);
        JcaX509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
                name, new BigInteger(64, RANDOM), notBefore, notAfter, name, keyPair.getPublic());
        builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
        builder.addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.keyCertSign | KeyUsage.cRLSign));
        JcaX509ExtensionUtils utils = new JcaX509ExtensionUtils();
        builder.addExtension(Extension.subjectKeyIdentifier, false, utils.createSubjectKeyIdentifier(keyPair.getPublic()));
        return sign(builder, keyPair.getPrivate());
    }

    /**
     * 签发叶子证书
     *
     * @param leafKeyPair 叶子密钥对
     * @param dn          主体名
     * @param caCert      签发者证书
     * @param caKey       签发者私钥
     * @param notBefore   生效时间
     * @param notAfter    失效时间
     * @return 叶子证书
     * @throws Exception 签发失败
     */
    public static X509Certificate issueLeaf(KeyPair leafKeyPair, String dn, X509Certificate caCert,
                                           PrivateKey caKey, Date notBefore, Date notAfter) throws Exception {
        // 签发者直接取CA证书主体的编码形式,避免字符串二次解析导致RDN顺序反转
        X500Name issuer = X500Name.getInstance(caCert.getSubjectX500Principal().getEncoded());
        JcaX509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
                issuer, new BigInteger(64, RANDOM), notBefore, notAfter, new X500Name(dn), leafKeyPair.getPublic());
        builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(false));
        builder.addExtension(Extension.keyUsage, true,
                new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment));
        JcaX509ExtensionUtils utils = new JcaX509ExtensionUtils();
        builder.addExtension(Extension.subjectKeyIdentifier, false, utils.createSubjectKeyIdentifier(leafKeyPair.getPublic()));
        builder.addExtension(Extension.authorityKeyIdentifier, false, utils.createAuthorityKeyIdentifier(caCert));
        return sign(builder, caKey);
    }

    private static X509Certificate sign(JcaX509v3CertificateBuilder builder, PrivateKey signerKey) throws Exception {
        return new JcaX509CertificateConverter()
                .setProvider(BC)
                .getCertificate(builder.build(new JcaContentSignerBuilder(SIGN_ALG).setProvider(BC).build(signerKey)));
    }

    /**
     * 计算证书指纹 SHA-256
     *
     * @param cert 证书
     * @return 十六进制大写指纹
     * @throws Exception 摘要计算失败
     */
    public static String fingerprint(X509Certificate cert) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(cert.getEncoded());
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }

    /**
     * 证书转Base64
     *
     * @param cert 证书
     * @return DER的Base64
     * @throws Exception 编码失败
     */
    public static String toBase64(X509Certificate cert) throws Exception {
        return Base64.getEncoder().encodeToString(cert.getEncoded());
    }

    /**
     * Base64还原证书
     *
     * @param base64 DER的Base64
     * @return 证书
     * @throws Exception 解析失败
     */
    public static X509Certificate fromBase64(String base64) throws Exception {
        byte[] der = Base64.getDecoder().decode(base64);
        return new JcaX509CertificateConverter()
                .setProvider(BC)
                .getCertificate(new X509CertificateHolder(der));
    }

    /**
     * 取证书公钥
     *
     * @param cert 证书
     * @return 公钥
     */
    public static PublicKey publicKey(X509Certificate cert) {
        return cert.getPublicKey();
    }
}

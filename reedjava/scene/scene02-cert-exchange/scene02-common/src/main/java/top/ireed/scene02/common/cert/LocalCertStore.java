package top.ireed.scene02.common.cert;

import top.ireed.scene02.common.model.CertPayload;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本端证书仓库
 * <p>
 * 持有本端叶子证书与私钥,支持轮换(直接覆盖,不做新旧共存)。
 * 同时可生成用于验证异常案例的证书:过期证书、临期证书、非受信根签发证书。
 */
public class LocalCertStore {

    /**
     * 持有方角色
     */
    private final String role;

    /**
     * 证书有效期 天
     */
    private final int validityDays;

    /**
     * 共享信任根
     */
    private final TrustRootProvider trustRoot;

    private volatile X509Certificate leafCert;

    private volatile KeyPair leafKeyPair;

    private volatile CertPayload payload;

    private volatile CertPayload expiredPayload;

    private volatile CertPayload expiringPayload;

    private volatile CertPayload badChainPayload;

    public LocalCertStore(String role, int validityDays, TrustRootProvider trustRoot) {
        this.role = role;
        this.validityDays = validityDays;
        this.trustRoot = trustRoot;
        this.leafKeyPair = generateKeyPair();
        this.leafCert = issueLeaf(leafKeyPair, validityDays);
    }

    private static KeyPair generateKeyPair() {
        try {
            return CertGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("生成密钥对失败", e);
        }
    }

    private X509Certificate issueLeaf(KeyPair keyPair, int days) {
        try {
            Date notBefore = new Date(System.currentTimeMillis() - 24L * 60 * 60 * 1000);
            Date notAfter = new Date(System.currentTimeMillis() + days * 24L * 60 * 60 * 1000);
            return CertGenerator.issueLeaf(keyPair, subjectDn(), trustRoot.rootCert(), trustRoot.rootKey(),
                    notBefore, notAfter);
        } catch (Exception e) {
            throw new IllegalStateException("生成本端证书失败", e);
        }
    }

    private String subjectDn() {
        return "CN=SCENE02-" + role.toUpperCase() + ",OU=SCENE02,O=REEDSOURCE";
    }

    /**
     * 本端证书
     *
     * @return 叶子证书
     */
    public X509Certificate cert() {
        return leafCert;
    }

    /**
     * 本端私钥
     *
     * @return 私钥
     */
    public PrivateKey privateKey() {
        return leafKeyPair.getPrivate();
    }

    /**
     * 本端证书载荷
     *
     * @return 证书载荷
     */
    public CertPayload payload() {
        CertPayload result = payload;
        if (result == null) {
            synchronized (this) {
                result = payload;
                if (result == null) {
                    result = build(leafCert);
                    payload = result;
                }
            }
        }
        return result;
    }

    /**
     * 轮换本端证书 直接覆盖 旧证书立即失效
     */
    public synchronized void rotate() {
        this.leafKeyPair = generateKeyPair();
        this.leafCert = issueLeaf(leafKeyPair, validityDays);
        this.payload = null;
        this.expiredPayload = null;
        this.expiringPayload = null;
        this.badChainPayload = null;
    }

    /**
     * 已过期证书 用于验证有效期校验
     *
     * @return 证书载荷
     */
    public CertPayload expiredPayload() {
        if (expiredPayload == null) {
            synchronized (this) {
                if (expiredPayload == null) {
                    expiredPayload = buildExpired();
                }
            }
        }
        return expiredPayload;
    }

    /**
     * 临期证书 剩余有效期1天 用于验证到期前提醒
     *
     * @return 证书载荷
     */
    public CertPayload expiringPayload() {
        if (expiringPayload == null) {
            synchronized (this) {
                if (expiringPayload == null) {
                    expiringPayload = buildExpiring();
                }
            }
        }
        return expiringPayload;
    }

    /**
     * 非受信根签发证书 用于验证证书链校验
     *
     * @return 证书载荷
     */
    public CertPayload badChainPayload() {
        if (badChainPayload == null) {
            synchronized (this) {
                if (badChainPayload == null) {
                    badChainPayload = buildBadChain();
                }
            }
        }
        return badChainPayload;
    }

    private CertPayload build(X509Certificate cert) {
        try {
            CertPayload result = new CertPayload();
            result.setRole(role);
            result.setSubject(cert.getSubjectX500Principal().getName());
            result.setIssuer(cert.getIssuerX500Principal().getName());
            result.setSerialNumber(cert.getSerialNumber().toString());
            result.setNotBefore(cert.getNotBefore().getTime());
            result.setNotAfter(cert.getNotAfter().getTime());
            result.setFingerprint(CertGenerator.fingerprint(cert));
            List<String> chain = new ArrayList<>();
            chain.add(CertGenerator.toBase64(cert));
            chain.add(CertGenerator.toBase64(trustRoot.rootCert()));
            result.setChainBase64(chain);
            return result;
        } catch (Exception e) {
            throw new IllegalStateException("构建证书载荷失败", e);
        }
    }

    private CertPayload buildExpired() {
        try {
            KeyPair keyPair = CertGenerator.generateKeyPair();
            Date notBefore = new Date(System.currentTimeMillis() - 400L * 24 * 60 * 60 * 1000);
            Date notAfter = new Date(System.currentTimeMillis() - 24L * 60 * 60 * 1000);
            X509Certificate cert = CertGenerator.issueLeaf(keyPair, subjectDn(), trustRoot.rootCert(),
                    trustRoot.rootKey(), notBefore, notAfter);
            return build(cert);
        } catch (Exception e) {
            throw new IllegalStateException("生成过期证书失败", e);
        }
    }

    private CertPayload buildExpiring() {
        try {
            KeyPair keyPair = CertGenerator.generateKeyPair();
            Date notBefore = new Date(System.currentTimeMillis() - 24L * 60 * 60 * 1000);
            Date notAfter = new Date(System.currentTimeMillis() + 24L * 60 * 60 * 1000);
            X509Certificate cert = CertGenerator.issueLeaf(keyPair, subjectDn(), trustRoot.rootCert(),
                    trustRoot.rootKey(), notBefore, notAfter);
            return build(cert);
        } catch (Exception e) {
            throw new IllegalStateException("生成临期证书失败", e);
        }
    }

    private CertPayload buildBadChain() {
        try {
            KeyPair keyPair = CertGenerator.generateKeyPair();
            // 另起一个不受信的根CA签发
            KeyPair rogueRootKeyPair = CertGenerator.generateKeyPair();
            Date notBefore = new Date(System.currentTimeMillis() - 24L * 60 * 60 * 1000);
            Date notAfter = new Date(System.currentTimeMillis() + validityDays * 24L * 60 * 60 * 1000);
            X509Certificate rogueRoot = CertGenerator.createCa(rogueRootKeyPair, "CN=SCENE02-ROGUE-CA,O=REEDSOURCE",
                    notBefore, notAfter);
            X509Certificate cert = CertGenerator.issueLeaf(keyPair, subjectDn(), rogueRoot,
                    rogueRootKeyPair.getPrivate(), notBefore, notAfter);
            CertPayload result = build(cert);
            List<String> chain = new ArrayList<>();
            chain.add(CertGenerator.toBase64(cert));
            chain.add(CertGenerator.toBase64(rogueRoot));
            result.setChainBase64(chain);
            return result;
        } catch (Exception e) {
            throw new IllegalStateException("生成非受信链证书失败", e);
        }
    }
}

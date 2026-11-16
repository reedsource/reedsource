package top.ireed.scene02.common.cert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ireed.scene02.common.model.CertPayload;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Collections;

/**
 * 证书校验器
 * <p>
 * 对应方案文档"拿到对方证书后会校验有效期、证书链"的口径:
 * 1.有效期校验:当前时间必须落在证书生效期与失效期之间
 * 2.证书链校验:叶子证书必须由本地信任根签发,并通过JDK PKIX路径校验
 */
public final class CertValidator {

    private static final Logger log = LoggerFactory.getLogger(CertValidator.class);

    private CertValidator() {
    }

    /**
     * 校验对端证书
     *
     * @param payload     对端返回的证书载荷
     * @param trustAnchor 本地信任锚
     * @throws CertValidationException 校验不通过
     */
    public static void validate(CertPayload payload, X509Certificate trustAnchor) throws CertValidationException {
        if (payload == null || payload.getChainBase64() == null || payload.getChainBase64().isEmpty()) {
            throw new CertValidationException("证书内容为空");
        }
        X509Certificate leaf;
        try {
            leaf = CertGenerator.fromBase64(payload.getChainBase64().get(0));
        } catch (Exception e) {
            throw new CertValidationException("证书解析失败:" + e.getMessage(), e);
        }

        long now = System.currentTimeMillis();
        if (now < leaf.getNotBefore().getTime()) {
            throw new CertValidationException("证书尚未生效 notBefore=" + leaf.getNotBefore());
        }
        if (now > leaf.getNotAfter().getTime()) {
            throw new CertValidationException("证书已过期 notAfter=" + leaf.getNotAfter());
        }
        if (now < trustAnchor.getNotBefore().getTime() || now > trustAnchor.getNotAfter().getTime()) {
            throw new CertValidationException("信任根证书不在有效期内");
        }

        // 签发者必须是本地信任根
        if (!leaf.getIssuerX500Principal().equals(trustAnchor.getSubjectX500Principal())) {
            throw new CertValidationException("证书签发者不匹配 issuer=" + leaf.getIssuerX500Principal()
                    + " 期望=" + trustAnchor.getSubjectX500Principal());
        }

        // 签名校验:由信任根公钥验证叶子证书签名
        try {
            leaf.verify(trustAnchor.getPublicKey());
        } catch (Exception e) {
            throw new CertValidationException("证书链签名校验失败:" + e.getMessage(), e);
        }

        // JDK PKIX 路径校验 叶子证书须能由本地信任根完成路径构建
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            CertPath path = factory.generateCertPath(Collections.singletonList(leaf));
            PKIXParameters parameters = new PKIXParameters(
                    Collections.singleton(new TrustAnchor(trustAnchor, null)));
            parameters.setRevocationEnabled(false);
            CertPathValidator.getInstance("PKIX").validate(path, parameters);
        } catch (Exception e) {
            log.warn("PKIX路径校验异常", e);
            throw new CertValidationException("证书链路径校验失败:" + e.getMessage(), e);
        }
    }
}

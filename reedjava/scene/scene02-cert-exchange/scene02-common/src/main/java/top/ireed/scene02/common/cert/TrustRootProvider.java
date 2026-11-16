package top.ireed.scene02.common.cert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * 场景共享信任根
 * <p>
 * 平台方与资方方是两个独立进程,必须使用同一份根CA才能互相校验证书链。
 * 根CA以PKCS12证书库的形式存放在公共模块资源中(scene02-trust-root.p12.base64),
 * 由keytool离线生成,双方启动时从classpath加载为信任锚。
 * 该文件仅为场景验证用的测试信任根,不是生产密钥。
 */
public class TrustRootProvider {

    /**
     * 根证书库资源文件
     */
    public static final String RESOURCE = "scene02-trust-root.p12.base64";

    /**
     * 根证书库默认口令
     */
    public static final String DEFAULT_PASSWORD = "scene02";

    /**
     * 根CA别名
     */
    public static final String ROOT_ALIAS = "scen02root";

    private final X509Certificate rootCert;

    private final PrivateKey rootKey;

    public TrustRootProvider(String password) {
        try (InputStream in = TrustRootProvider.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            if (in == null) {
                throw new IllegalStateException("未找到信任根证书库资源:" + RESOURCE);
            }
            StringBuilder text = new StringBuilder();
            try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                char[] buffer = new char[1024];
                int len;
                while ((len = reader.read(buffer)) > 0) {
                    text.append(buffer, 0, len);
                }
            }
            byte[] storeBytes = Base64.getDecoder().decode(text.toString().replaceAll("\\s", ""));
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new ByteArrayInputStream(storeBytes), password.toCharArray());
            String alias = keyStore.containsAlias(ROOT_ALIAS) ? ROOT_ALIAS : keyStore.aliases().nextElement();
            this.rootCert = (X509Certificate) keyStore.getCertificate(alias);
            this.rootKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        } catch (Exception e) {
            throw new IllegalStateException("加载场景信任根失败:" + e.getMessage(), e);
        }
    }

    /**
     * 根证书 信任锚
     *
     * @return 根证书
     */
    public X509Certificate rootCert() {
        return rootCert;
    }

    /**
     * 根私钥 用于签发本端叶子证书
     *
     * @return 根私钥
     */
    public PrivateKey rootKey() {
        return rootKey;
    }
}

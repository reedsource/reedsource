package top.ireed.scene02.common.cert;

/**
 * 证书校验异常
 */
public class CertValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public CertValidationException(String message) {
        super(message);
    }

    public CertValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

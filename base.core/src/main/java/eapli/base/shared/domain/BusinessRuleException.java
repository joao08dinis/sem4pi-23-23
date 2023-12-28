package eapli.base.shared.domain;

/**
 * @author Pedro Campos 1211511
 */
public class BusinessRuleException extends Exception {

    public BusinessRuleException() {
    }

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuleException(Throwable cause) {
        super(cause);
    }

    public BusinessRuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

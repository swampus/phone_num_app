package load.phone.app.exception;

public class ApplicationInitilizationException extends RuntimeException {
    public ApplicationInitilizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInitilizationException(String s) {
        super(s);
    }
}

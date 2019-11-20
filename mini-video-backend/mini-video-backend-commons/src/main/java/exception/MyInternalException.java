package exception;

public class MyInternalException extends RuntimeException {

    public MyInternalException() {
    }

    public MyInternalException(String message) {
        super(message);
    }

    public MyInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyInternalException(Throwable cause) {
        super(cause);
    }

    public MyInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
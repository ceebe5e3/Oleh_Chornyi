package ua.nure.chornyi.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 */
public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}

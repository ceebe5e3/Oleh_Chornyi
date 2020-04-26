package ua.nure.chornyi.SummaryTask4.exception;

/**
 *  An exception that provides information on a database access error.
 */
public class DBException extends RuntimeException {

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}

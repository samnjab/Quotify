package quotify_app.data_access.exceptions;

/**
 *  Custom exception class for PredictionClient errors.
 */
public class ComparatorClientException extends Exception {
    public ComparatorClientException(String message) {
        super(message);
    }

    public ComparatorClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

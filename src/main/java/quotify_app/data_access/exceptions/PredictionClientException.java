package quotify_app.data_access.exceptions;

/**
 *  Custom exception class for PredictionClient errors.
 */
public class PredictionClientException extends Exception {
    public PredictionClientException(String message) {
        super(message);
    }

    public PredictionClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

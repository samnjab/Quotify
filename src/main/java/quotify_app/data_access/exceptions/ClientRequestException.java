package quotify_app.data_access.exceptions;

/**
 * Custom exception for general client request errors.
 */
public class ClientRequestException extends Exception {

    /**
     * Constructs a new ClientRequestException with the specified detail message.
     * @param message The detail message.
     */
    public ClientRequestException(String message) {
        super(message);
    }

    /**
     * Constructs a new ClientRequestException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public ClientRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

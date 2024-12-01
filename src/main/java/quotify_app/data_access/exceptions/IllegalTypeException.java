package quotify_app.data_access.exceptions;

/**
 * Exception thrown when an API request fails due to an unauthorized request,
 * bad request, internal server error, or other API-related issues.
 */
public class IllegalTypeException extends RuntimeException {

    /**
     * Constructs a new ApiRequestException with the specified detail message.
     * @param message The detail message.
     */
    public IllegalTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApiRequestException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

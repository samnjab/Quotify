package quotify_app.data_access.exceptions;

/**
 * Class of exception thrown when Api request fails due to an unauthorized or
 * bad request, or an internal server error.
 */
public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

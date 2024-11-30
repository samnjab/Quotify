package quotify_app.usecases.landing.exceptions;

/**
 * Class of exception thrown when Api request fails due to an unauthorized or
 * bad request, or an internal server error.
 */
public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

package quotify_app.usecases.login;

/**
 * Output Data for the Login Use Case, holding the result of a login attempt.
 */
public class LoginOutputData {
    private final String username;
    private final boolean loginFailed;

    public LoginOutputData(String username, boolean loginFailed) {
        this.username = username;
        this.loginFailed = loginFailed;
    }

    /**
     * Getter function for the username.
     * @return a string representation of the input username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Check function for login status. NOT IN USE yet.
     * @return the login status.
     */
    public boolean isLoginFailed() {
        return loginFailed;
    }

    /**
     * Retrieves the logged-in user.
     *
     * @return The User object representing the logged-in user.
     */
    public String getUser() {
        return username;
    }
}

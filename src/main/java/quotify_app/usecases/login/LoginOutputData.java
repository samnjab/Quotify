package quotify_app.usecases.login;

/**
 * Output Data for the Login Use Case, holding the result of a login attempt.
 */
public class LoginOutputData {
    private final String username;

    public LoginOutputData(String username) {
        this.username = username;
    }

    /**
     * Getter function for the username.
     * @return a string representation of the input username.
     */
    public String getUsername() {
        return username;
    }

}

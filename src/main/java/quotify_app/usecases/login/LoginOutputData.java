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

    public String getUsername() {
        return username;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }
}

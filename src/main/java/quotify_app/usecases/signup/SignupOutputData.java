package quotify_app.usecases.signup;

/**
 * Output Data for the Signup Use Case.
 * Contains information about a successfully signed-up user.
 */
public class SignupOutputData {
    private final String username;

    public SignupOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

package quotify_app.usecases.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {
    private final String username;
    private final String email;
    private final String password;

    public SignupInputData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

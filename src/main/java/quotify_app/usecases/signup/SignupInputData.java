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

    /**
     * Getter function for the input data username field.
     * @return a string representation of the input username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter function for the input data email field.
     * @return a string representation of the input email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter function for the input data password field.
     * @return a string representation of the input password.
     */
    public String getPassword() {
        return password;
    }
}

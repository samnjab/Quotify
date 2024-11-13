package quotify_app.adapters.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    // user info fields
    private String username = "";
    private String password = "";
    private String email = "";

    // error message fields
    private String usernameError;
    private String passwordError;
    private String signupError;

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getEmail() {
        return email;
    }

    public String getSignupError() {
        return signupError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSignupError(String signupError) {
        this.signupError = signupError;
    }

    /**
     * Resets the signup state.
     */
    public void clear() {
        // reset user info fields
        this.username = "";
        this.password = "";
        this.email = "";

        // reset error messages
        this.usernameError = "";
        this.passwordError = "";
        this.signupError = "";

    }

    /**
     * Debugging method to see the current state.
     */
    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}

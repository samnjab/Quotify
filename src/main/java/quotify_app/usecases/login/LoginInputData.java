package quotify_app.usecases.login;

/**
 * Input Data for the Login Use Case, containing login credentials.
 */
public class LoginInputData {
    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter function for the username.
     * @return a string representation of the input username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter function for the password.
     * @return a string representation of the input password.
     */
    public String getPassword() {
        return password;
    }
}

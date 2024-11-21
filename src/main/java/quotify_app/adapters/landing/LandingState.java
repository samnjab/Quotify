package quotify_app.adapters.landing;

/**
 * The state for the Landing View Model.
 */
public class LandingState {
    private String username = "";
    private String password = "";
    private String loginError = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
}

package quotify_app.adapters.login;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    // Labels for UI elements in the login view
    public static final String TITLE_LABEL = "Login View";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TO_SIGNUP_BUTTON_LABEL = "Go to Sign Up";

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }
}

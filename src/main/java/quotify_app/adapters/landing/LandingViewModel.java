package quotify_app.adapters.landing;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the Landing View.
 */
public class LandingViewModel extends ViewModel<LandingState> {

    // Labels for UI elements in the login view
    public static final String TITLE_LABEL = "Quotify";

    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String LOGIN_BUTTON_LABEL = "SignUp";

    public static final String Country_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TO_SIGNUP_BUTTON_LABEL = "Go to Sign Up";

    public LandingViewModel() {
        super("landing");
        setState(new LandingState());
    }
}

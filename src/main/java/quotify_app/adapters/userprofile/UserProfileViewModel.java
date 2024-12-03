package quotify_app.adapters.userprofile;

import quotify_app.adapters.ViewModel;

/**
 * View model for the userprofile view
 */
public class UserProfileViewModel extends ViewModel<UserProfileState> {
    public static final String TITLE_LABEL = "View your account information";
    public static final String LANDING_PAGE_BUTTON_LABEL = "Add A New Property";
    public static final String ACCOUNT_USERNAME_INFO = "Your Username: ";
    public static final String ACCOUNT_EMAIL_INFO = "Your Email: ";
    public static final String ACCOUNT_CREATION_INFO = "Account Created At: ";

    public UserProfileViewModel() {
        super("user profile");
        setState(new UserProfileState());
    }
}

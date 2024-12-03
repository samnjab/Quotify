package quotify_app.adapters.userprofile;

import quotify_app.usecases.userprofile.UserProfileInputBoundary;

public class UserProfileController {

    private UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    public void execute() { userProfileInteractor.execute(); }

    public void goToLanding() { userProfileInteractor.goToLanding(); }
}

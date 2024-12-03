package quotify_app.usecases.userprofile;

import quotify_app.entities.User;

public class UserProfileInteractor implements UserProfileInputBoundary{
    private UserProfileOutputBoundary userProfilePresenter;
    private UserProfileDataAccessInterface userProfileDataAccess;

    public UserProfileInteractor(
            UserProfileOutputBoundary userProfilePresenter,
            UserProfileDataAccessInterface userProfileDataAccess) {
        this.userProfilePresenter = userProfilePresenter;
        this.userProfileDataAccess = userProfileDataAccess;
    }

    @Override
    public void execute() {
        final String username = userProfileDataAccess.getCurrentUserName();
        final String timestamp = userProfileDataAccess.getCurrentCreatedAt().toString();
        if (!userProfileDataAccess.existsByName(username)) {
            userProfilePresenter.prepareFailureView();
        }
        else {
            final User currentUser = userProfileDataAccess.get(username);
            final String email = currentUser.getEmail();
            final UserProfileOutputData outputData = new UserProfileOutputData(username, email, timestamp);
            userProfilePresenter.prepareSuccessView(outputData);

        }
    }

    @Override
    public void goToLanding() {
        this.userProfilePresenter.goToLanding();
    }
}

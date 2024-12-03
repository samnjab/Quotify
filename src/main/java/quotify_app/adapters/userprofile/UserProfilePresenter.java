package quotify_app.adapters.userprofile;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.usecases.userprofile.UserProfileOutputBoundary;
import quotify_app.usecases.userprofile.UserProfileOutputData;

public class UserProfilePresenter implements UserProfileOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private UserProfileViewModel userProfileViewModel;

    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareFailureView() {

    }

    @Override
    public void prepareSuccessView(UserProfileOutputData data) {
        userProfileViewModel.getState().setCurrentUser(data.getUsername());
        userProfileViewModel.getState().setCurrentEmail(data.getEmail());
        userProfileViewModel.getState().setCreatedAt(data.getCreatedAt());
    }

    @Override
    public void goToLanding() {
        viewManagerModel.setState("landing");
        viewManagerModel.firePropertyChanged();
    }
}

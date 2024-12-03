package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.userprofile.UserProfileController;
import quotify_app.adapters.userprofile.UserProfilePresenter;
import quotify_app.adapters.userprofile.UserProfileViewModel;
import quotify_app.ui.UserProfileView;
import quotify_app.usecases.userprofile.UserProfileDataAccessInterface;
import quotify_app.usecases.userprofile.UserProfileInputBoundary;
import quotify_app.usecases.userprofile.UserProfileInteractor;
import quotify_app.usecases.userprofile.UserProfileOutputBoundary;

/**
 * The UserProfileFactory class is responsible for setting up and wiring together the UserProfile components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class UserProfileFactory {

    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileView userProfileView;
    private UserProfileController userProfileController;

    /**
     * Initializes the UserProfileFactory with its View and ViewModel.
     */
    public UserProfileFactory() {
        this.userProfileViewModel = new UserProfileViewModel();
        this.userProfileView = new UserProfileView(userProfileViewModel);
    }

    /**
     * Sets up the UserProfileController.
     *
     * @param viewManagerModel the ViewManagerModel from the AppBuilder.
     * @param dataAccessInterface the UserProfileDataAccessInterface for database operations.
     */
    public void setUpController(ViewManagerModel viewManagerModel, UserProfileDataAccessInterface dataAccessInterface) {
        // Setup Presenter and Interactor for UserProfile with necessary dependencies
        final UserProfileOutputBoundary userProfilePresenter = new UserProfilePresenter(
                viewManagerModel,
                userProfileViewModel
        );

        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                userProfilePresenter,
                dataAccessInterface
        );

        this.userProfileController = new UserProfileController(userProfileInteractor);
    }

    /**
     * Gets the UserProfileView.
     *
     * @return the UserProfileView.
     */
    public UserProfileView getUserProfileView() {
        return userProfileView;
    }

    /**
     * Gets the UserProfileController.
     *
     * @return the UserProfileController.
     */
    public UserProfileController getUserProfileController() {
        return userProfileController;
    }

    /**
     * Gets the UserProfileViewModel.
     *
     * @return the UserProfileViewModel.
     */
    public UserProfileViewModel getUserProfileViewModel() {
        return userProfileViewModel;
    }
}

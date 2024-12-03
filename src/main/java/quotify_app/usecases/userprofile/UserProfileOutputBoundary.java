package quotify_app.usecases.userprofile;

/**
 * The interface through which the UserProfile Presenter is called by the interactor.
 */
public interface UserProfileOutputBoundary {
    /**
     * The user exists and has valid data -- in this case we update the UserProfileState with the updated data.
     * @param data the output data to update the UserProfileState with.
     */
    void prepareSuccessView(UserProfileOutputData data);

    /**
     * The user does not exist in the database. In this we will display an error view.
     */
    void prepareFailureView();

    /**
     * Navigate to the landing screen.
     */
    void goToLanding();
}

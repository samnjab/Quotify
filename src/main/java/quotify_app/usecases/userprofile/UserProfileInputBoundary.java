package quotify_app.usecases.userprofile;

/**
 * A interface through which the controller manipulates the landing Interactor.
 */
public interface UserProfileInputBoundary {
    /**
     * Execute the UserProfile usecase -- try and query for the existence of the user in the database.
     */
    void execute();

    /**
     * Navigate to the landing screen.
     */
    void goToLanding();

}

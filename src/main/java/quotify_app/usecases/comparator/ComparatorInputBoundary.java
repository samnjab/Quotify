package quotify_app.usecases.comparator;

/**
 * Input Boundary for actions related to seeing comparison.
 */
public interface ComparatorInputBoundary {

    /**
     * Switches to the current price view.
     */
    void goToCurrentPrice();

    /**
     * Switches to the input view.
     */
    void goToInput();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void checkLoginStatus();

    /**
     * Handles the action to navigate to the User Profile View.
     */
    void goToUserProfile();
}
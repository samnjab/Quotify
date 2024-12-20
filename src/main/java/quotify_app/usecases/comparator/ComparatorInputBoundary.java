package quotify_app.usecases.comparator;

import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.entities.regionEntities.Area;

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
    void goToLanding();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void checkLoginStatus();

    /**
     * Handles the action to navigate to the User Profile View.
     */
    void goToUserProfile();

    /**
     * Interface for the getComparables controller function.
     */
    void getComparables();
}

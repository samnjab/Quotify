package quotify_app.adapters.comparator;

import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.comparator.ComparatorInputBoundary;

/**
 * The controller for the Comparator Case.
 */
public class ComparatorController {

    private final ComparatorInputBoundary comparatorInteractor;

    public ComparatorController(ComparatorInputBoundary comparatorInteractor) {

        this.comparatorInteractor = comparatorInteractor;
    }

    /**
     * Triggers the navigation to CurrentPrice through the comparatorInteractor.
     */
    public void goToCurrentPrice() {
        comparatorInteractor.goToCurrentPrice();
    }

    /**
     * Triggers the navigation to Input through the comparatorInteractor.
     */
    public void goToLanding() {
        comparatorInteractor.goToLanding();
    }

    /**
     * Triggers a check on the login status of the user.
     */
    public void checkLoginStatus() {
        comparatorInteractor.checkLoginStatus();
    }

    /**
     * Triggers navigation to the User Profile View.
     */
    public void goToUserProfile() {
        comparatorInteractor.goToUserProfile();
    }

    /**
     * Gets the list of comparables for the Comparator View.
     */
    public void getComparables()  { comparatorInteractor.getComparables(); }
}

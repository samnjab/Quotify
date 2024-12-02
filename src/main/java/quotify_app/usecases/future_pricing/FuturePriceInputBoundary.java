package quotify_app.usecases.future_pricing;

/**
 * Input Boundary for actions related to the Future Price use case.
 */
public interface FuturePriceInputBoundary {

    /**
     * Handles the action to navigate to the Login View.
     */
    void goToLogin();

    /**
     * Handles the action to navigate to the Signup View.
     */
    void goToSignup();

    /**
     * Handles the action to navigate to the Current Pricing View.
     */
    void goToCurrentPricing();

    /**
     * Handles the action to navigate to the Comparator View.
     */
    void goToComparator();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void checkLoginStatus();

    /**
     * Handles the action to navigate to the User Profile View.
     */
    void goToUserProfile();

    /**
     * Handles the action to navigate to the Landing Page View.
     */
    void goToLandingPage();

    /**
     * Handles the action to retrieve the data for the predicted future prices.
     */
    void fetchFuturePrices();

}

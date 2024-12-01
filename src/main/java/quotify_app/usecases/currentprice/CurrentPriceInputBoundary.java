package quotify_app.usecases.currentprice;

/**
 * Input Boundary for actions related to the Current Price use case.
 */
public interface CurrentPriceInputBoundary {

    /**
     * Handles the action to navigate to the Login View.
     */
    void goToLogin();

    /**
     * Handles the action to navigate to the Signup View.
     */
    void goToSignup();

    /**
     * Handles the action to navigate to the Future Pricing View.
     */
    void goToFuturePricing();

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
     * Fetches the current price prediction and passes it to the presenter.
     */
    void fetchCurrentPrice();
}

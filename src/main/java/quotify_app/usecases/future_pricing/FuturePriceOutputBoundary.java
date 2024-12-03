package quotify_app.usecases.future_pricing;

/**
 * Output Boundary for presenting the result of the Future Price use case.
 */
public interface FuturePriceOutputBoundary {

    /**
     * Prepares the response to navigate to the Login View.
     */
    void presentGoToLogin();

    /**
     * Prepares the response to navigate to the Signup View.
     */
    void presentGoToSignup();

    /**
     * Prepares the response to navigate to the Current Pricing View.
     */
    void presentGoToCurrentPricing();

    /**
     * Prepares the response to navigate to the Comparator View.
     */
    void presentGoToComparator();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void updateLoginStatus();

    /**
     * Prepares the response to navigate to the UserProfileView.
     */
    void presentGoToUserProfile();

    /**
     * Prepares the response to navigate to the LandingPageView.
     */
    void presentGoToLandingPage();

    /**
     * Prepares the response to display the future prices.
     * @param futurePrices array containing predicted prices
     */
    void presentFuturePrices(double[] futurePrices);

    /**
     * Prepares the response to display that there was an error predicting the future prices.
     * @param errorMessage describing what happened.
     */
    void presentPredictionError(String errorMessage);
}

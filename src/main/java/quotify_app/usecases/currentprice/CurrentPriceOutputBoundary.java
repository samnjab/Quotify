package quotify_app.usecases.currentprice;

/**
 * Output Boundary for presenting the result of the Current Price use case.
 */
public interface CurrentPriceOutputBoundary {

    /**
     * Prepares the response to navigate to the Login View.
     */
    void presentGoToLogin();

    /**
     * Prepares the response to navigate to the Signup View.
     */
    void presentGoToSignup();

    /**
     * Prepares the response to navigate to the Future Pricing View.
     */
    void presentGoToFuturePricing();

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
     * Presents the current price prediction.
     *
     * @param price The predicted price.
     */
    void presentCurrentPrice(double price);

    /**
     * Presents an error message related to prediction.
     *
     * @param message The error message.
     */
    void presentPredictionError(String message);
}

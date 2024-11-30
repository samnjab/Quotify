package quotify_app.adapters.future_price;

import quotify_app.usecases.future_pricing.FuturePriceInputBoundary;

/**
 * The Controller for the Future Price use case.
 * Receives user input from the View and calls methods on the Interactor.
 */
public class FuturePriceController {

    private final FuturePriceInputBoundary futurePriceInteractor;

    /**
     * Initializes the FuturePriceController with the given Interactor.
     *
     * @param futurePriceInteractor the Interactor for the Current Price use case.
     */
    public FuturePriceController(FuturePriceInputBoundary futurePriceInteractor) {
        this.futurePriceInteractor = futurePriceInteractor;
    }

    /**
     * Triggers navigation to the Login View.
     */
    public void goToLogin() {
        futurePriceInteractor.goToLogin();
    }

    /**
     * Triggers navigation to the Signup View.
     */
    public void goToSignup() {
        futurePriceInteractor.goToSignup();
    }

    /**
     * Triggers navigation to the Future Pricing View.
     */
    public void goToCurrentPricing() {
        futurePriceInteractor.goToCurrentPricing();
    }

    /**
     * Triggers navigation to the Comparator View. Same case with this one i will replace the names if needed.
     */
    public void goToComparator() {
        futurePriceInteractor.goToComparator();
    }

    /**
     * Triggers a check on the login status of the user.
     */
    public void checkLoginStatus() {
        futurePriceInteractor.checkLoginStatus();
    }

    /**
     * Triggers navigation to the User Profile View.
     */
    public void goToUserProfile() {
        futurePriceInteractor.goToUserProfile();
    }

    /**
     * Triggers navigation to the Landing Page View.
     */
    public void goToLandingPage() {
        futurePriceInteractor.goToLandingPage();
    }
}

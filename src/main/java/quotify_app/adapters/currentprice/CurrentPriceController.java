package quotify_app.adapters.currentprice;

import quotify_app.usecases.currentprice.CurrentPriceInputBoundary;

/**
 * The Controller for the Current Price use case.
 * Receives user input from the View and calls methods on the Interactor.
 */
public class CurrentPriceController {

    private final CurrentPriceInputBoundary currentPriceInteractor;

    /**
     * Initializes the CurrentPriceController with the given Interactor.
     *
     * @param currentPriceInteractor the Interactor for the Current Price use case.
     */
    public CurrentPriceController(CurrentPriceInputBoundary currentPriceInteractor) {
        this.currentPriceInteractor = currentPriceInteractor;
    }

    /**
     * Triggers navigation to the Login View.
     */
    public void goToLogin() {
        currentPriceInteractor.goToLogin();
    }

    /**
     * Triggers navigation to the Signup View.
     */
    public void goToSignup() {
        currentPriceInteractor.goToSignup();
    }

    /**
     * Triggers navigation to the Future Pricing Guest View, i can change this to just Future Pricing View if Future
     * Pricing and Future Pricing Logged In View logic is merged into one using the application status.
     */
    public void goToFuturePricing() {
        currentPriceInteractor.goToFuturePricing();
    }

    /**
     * Triggers navigation to the Comparator Guest View. Same case with this one i will replace the names if needed.
     */
    public void goToComparator() {
        currentPriceInteractor.goToComparator();
    }

    /**
     * Triggers a check on the login status of the user.
     */
    public void checkLoginStatus() {
        currentPriceInteractor.checkLoginStatus();
    }

    /**
     * Triggers navigation to the User Profile View.
     */
    public void goToUserProfile() {
        currentPriceInteractor.goToUserProfile();
    }

    /**
     * Triggers navigation to the Landing Page View.
     */
    public void goToLandingPage() {
        currentPriceInteractor.goToLandingPage();
    }
}

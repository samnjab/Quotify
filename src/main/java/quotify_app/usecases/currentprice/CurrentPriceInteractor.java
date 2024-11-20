package quotify_app.usecases.currentprice;

/**
 * The Interactor for the Current Price use case.
 * Implements the business logic and interacts with the Presenter.
 */
public class CurrentPriceInteractor implements CurrentPriceInputBoundary {

    private final CurrentPriceOutputBoundary currentPricePresenter;

    /**
     * Initializes the CurrentPriceInteractor with the given Presenter.
     *
     * @param currentPricePresenter the Presenter for the Current Price use case.
     */
    public CurrentPriceInteractor(CurrentPriceOutputBoundary currentPricePresenter) {
        this.currentPricePresenter = currentPricePresenter;
    }

    @Override
    public void goToLogin() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToLogin();
    }

    @Override
    public void goToSignup() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToSignup();
    }

    @Override
    public void goToFuturePricing() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToFuturePricing();
    }

    @Override
    public void goToComparator() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToComparator();
    }

    @Override
    public void checkLoginStatus() {
        // Instruct the presenter to update the login status
        currentPricePresenter.updateLoginStatus();
    }

    @Override
    public void goToUserProfile() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToUserProfile();
    }

    @Override
    public void goToLandingPage() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToLandingPage();
    }

}

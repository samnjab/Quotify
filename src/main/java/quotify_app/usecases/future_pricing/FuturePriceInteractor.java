package quotify_app.usecases.future_pricing;

/**
 * The Interactor for the Future Price use case.
 * Implements the business logic and interacts with the Presenter.
 */
public class FuturePriceInteractor implements FuturePriceInputBoundary {

    private final FuturePriceOutputBoundary futurePricePresenter;

    /**
     * Initializes the FuturePriceInteractor with the given Presenter.
     *
     * @param futurePricePresenter the Presenter for the Future Price use case.
     */
    public FuturePriceInteractor(FuturePriceOutputBoundary futurePricePresenter) {
        this.futurePricePresenter = futurePricePresenter;
    }

    @Override
    public void goToLogin() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToLogin();
    }

    @Override
    public void goToSignup() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToSignup();
    }

    @Override
    public void goToCurrentPricing() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToCurrentPricing();
    }

    @Override
    public void goToComparator() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToComparator();
    }

    @Override
    public void checkLoginStatus() {
        // Instruct the presenter to update the login status
        futurePricePresenter.updateLoginStatus();
    }

    @Override
    public void goToUserProfile() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToUserProfile();
    }

    @Override
    public void goToLandingPage() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToLandingPage();
    }

}

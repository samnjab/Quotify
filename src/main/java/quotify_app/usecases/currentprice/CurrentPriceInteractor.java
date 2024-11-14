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
    public void goToFuturePricingGuest() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToFuturePricingGuest();
    }

    @Override
    public void goToComparatorGuest() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToComparatorGuest();
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

}

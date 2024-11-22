package quotify_app.adapters.currentprice;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.app.ApplicationState;
import quotify_app.usecases.currentprice.CurrentPriceOutputBoundary;

/**
 * The Presenter for the Current Price use case.
 * Processes the output from the Interactor and updates the ViewModel or handles navigation.
 */
public class CurrentPricePresenter implements CurrentPriceOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CurrentPriceViewModel currentPriceViewModel;

    /**
     * Initializes the CurrentPricePresenter with the given ViewManagerModel and CurrentPriceViewModel.
     *
     * @param viewManagerModel      the model managing view transitions.
     * @param currentPriceViewModel the ViewModel for the Current Price View.
     */
    public CurrentPricePresenter(ViewManagerModel viewManagerModel, CurrentPriceViewModel currentPriceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.currentPriceViewModel = currentPriceViewModel;
    }

    @Override
    public void presentGoToLogin() {
        viewManagerModel.setState("log in");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToSignup() {
        viewManagerModel.setState("sign up");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToFuturePricing() {
        viewManagerModel.setState("FuturePricingView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToComparator() {
        viewManagerModel.setState("ComparatorView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToUserProfile() {
        viewManagerModel.setState("UserProfileView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateLoginStatus() {
        // Get the login status from the ApplicationState
        final boolean isLoggedIn = ApplicationState.getInstance().isLoggedIn();
        currentPriceViewModel.getState().setLoggedIn(isLoggedIn);
        currentPriceViewModel.firePropertyChanged();
    }

    @Override
    public void presentGoToLandingPage() {
        viewManagerModel.setState("LandingPageView");
        viewManagerModel.firePropertyChanged();
    }
}
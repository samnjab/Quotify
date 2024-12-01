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
        viewManagerModel.setState("future price");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToComparator() {
        viewManagerModel.setState("comparator");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToUserProfile() {
        viewManagerModel.setState("user profile");
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
        viewManagerModel.setState("landing page");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentCurrentPrice(double price) {
        // Update the ViewModel's state with the predicted price
        currentPriceViewModel.getState().setCurrentPrice(String.format("$%.2f", price));
        currentPriceViewModel.firePropertyChanged();
    }

    @Override
    public void presentPredictionError(String message) {
        // Update the ViewModel's state with error message
        currentPriceViewModel.getState().setCurrentPrice("Error: " + message);
        currentPriceViewModel.firePropertyChanged();
    }

}

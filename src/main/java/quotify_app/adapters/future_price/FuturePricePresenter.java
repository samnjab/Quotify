package quotify_app.adapters.future_price;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.app.ApplicationState;
import quotify_app.usecases.future_pricing.FuturePriceOutputBoundary;

/**
 * The Presenter for the Future Price use case.
 * Processes the output from the Interactor and updates the ViewModel or handles navigation.
 */
public class FuturePricePresenter implements FuturePriceOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final FuturePriceViewModel futurePriceViewModel;

    /**
     * Initializes the FuturePriceViewModel with the given ViewManagerModel and FuturePriceViewModel.
     *
     * @param viewManagerModel      the model managing view transitions.
     * @param futurePriceViewModel the ViewModel for the Future Price View.
     */
    public FuturePricePresenter(ViewManagerModel viewManagerModel, FuturePriceViewModel futurePriceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.futurePriceViewModel = futurePriceViewModel;
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
    public void presentGoToCurrentPricing() {
        viewManagerModel.setState("current price");
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
        futurePriceViewModel.getState().setLoggedIn(isLoggedIn);
        futurePriceViewModel.firePropertyChanged();
    }

    @Override
    public void presentGoToLandingPage() {
        viewManagerModel.setState("landing");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentFuturePrices(double[] futurePrices) {
        // Update the ViewModel's state with the predicted price
        futurePriceViewModel.getState().setFuturePrices(futurePrices);
        futurePriceViewModel.firePropertyChanged();
    }

    @Override
    public void presentPredictionError(String message) {
        // Update the ViewModel's state with error message

        futurePriceViewModel.getState().setPredictionErrorMsg("Error: " + message);
        futurePriceViewModel.getState().setIsPredictionError();
        futurePriceViewModel.firePropertyChanged();
    }
}

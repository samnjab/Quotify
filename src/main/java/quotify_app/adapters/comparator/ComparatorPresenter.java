package quotify_app.adapters.comparator;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.app.ApplicationState;
import quotify_app.usecases.comparator.ComparatorOutputBoundary;

/**
 * The Presenter for the Comparator Use Case.
 */
public class ComparatorPresenter implements ComparatorOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ComparatorViewModel comparatorViewModel;

    public ComparatorPresenter(
            ViewManagerModel viewManagerModel,
            ComparatorViewModel comparatorViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.comparatorViewModel = comparatorViewModel;
    }

    @Override
    public void goToCurrentPrice() {
        // Transition to current price view
        viewManagerModel.setState("current price");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goToInput() {
        // Transition to input view
        viewManagerModel.setState("input");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void goToUserProfile() {
        viewManagerModel.setState("user profile");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateLoginStatus() {
        // Get the login status from the ApplicationState
        final boolean isLoggedIn = ApplicationState.getInstance().isLoggedIn();
        comparatorViewModel.getState().setLoggedIn(isLoggedIn);
    }

}

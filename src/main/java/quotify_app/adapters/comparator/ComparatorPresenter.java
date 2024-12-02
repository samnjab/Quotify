package quotify_app.adapters.comparator;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.app.ApplicationState;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.comparator.ComparatorOutputBoundary;

import java.util.List;

/**
 * The Presenter for the Comparator Use Case.
 */
public class ComparatorPresenter implements ComparatorOutputBoundary {
    private static ComparatorViewModel comparatorViewModel;
    private final ViewManagerModel viewManagerModel;

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

    /**
     * Updates the ComparatorState with the provided list of properties.
     *
     * @param properties the list of properties to update in the state.
     */
    public void updateProperties(List<Property> properties) {
        comparatorViewModel.getState().setProperties(properties);
    }
}

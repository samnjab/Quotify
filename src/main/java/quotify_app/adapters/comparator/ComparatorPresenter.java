package quotify_app.adapters.comparator;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.usecases.comparator.ComparatorOutputBoundary;
import quotify_app.usecases.comparator.ComparatorOutputBoundary;
import quotify_app.usecases.comparator.ComparatorInputBoundary;

/**
 * The Presenter for the Login Use Case.
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
        viewManagerModel.setState(comparatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goToInput() {
        // Transition to input view
        viewManagerModel.setState(comparatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}

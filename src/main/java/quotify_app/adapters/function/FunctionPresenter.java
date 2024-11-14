package quotify_app.adapters.function;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.usecases.function.FunctionOutputBoundary;

/**
 * The Presenter for the Login Use Case.
 */
public class FunctionPresenter implements FunctionOutputBoundary {

    private final FunctionViewModel functionViewModel;
    private final ViewManagerModel viewManagerModel;

    public FunctionPresenter(
            ViewManagerModel viewManagerModel,
            FunctionViewModel functionViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.functionViewModel = functionViewModel;
    }

    /**
     * Navigates to the Current Price for guest.
     */
    @Override
    public void goToCurrentPriceGuest() {
        // Transition to current price view for guest
        viewManagerModel.setState(currentPriceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    /**
     * Navigates to the Comparator.
     */
    @Override
    public void goToCurrentPriceUser() {
        // Transition to current price view for user
        viewManagerModel.setState(currentPriceLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the Comparator for guest.
     */
    @Override
    public void goToComparatorGuest() {
        // Transition to comparator view for guest
        viewManagerModel.setState(comparatorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the Comparator for user.
     */
    @Override
    public void goToComparatorUser() {
        // Transition to comparator view for user
        viewManagerModel.setState(comparatorLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}




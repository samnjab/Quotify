
package quotify_app.adapters.function;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.usecases.function.FunctionOutputBoundary;

/**
 * The Presenter for the Function Use Case.
 */
public class FunctionPresenter implements FunctionOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final FunctionViewModel functionViewModel;

    /**
     * Initializes the FunctionPresenter with the given ViewManagerModel and FunctionViewModel.
     *
     * @param viewManagerModel      the model managing view transitions.
     * @param functionViewModel the ViewModel for the Function View.
     */
    public FunctionPresenter(
            ViewManagerModel viewManagerModel,
            FunctionViewModel functionViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.functionViewModel = functionViewModel;
    }

    /**
     * Navigates to the Function.
     */
    @Override
    public void goToCurrentPrice() {
        // Transition to current price view
        viewManagerModel.setState("current price");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the Comparator.
     */
    @Override
    public void goToComparator() {
        // Transition to comparator view
        viewManagerModel.setState("comparator");
        viewManagerModel.firePropertyChanged();
    }
}

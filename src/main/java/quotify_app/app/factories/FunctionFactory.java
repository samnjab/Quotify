
package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.function.FunctionController;
import quotify_app.adapters.function.FunctionPresenter;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.ui.FunctionView;
import quotify_app.usecases.function.FunctionInputBoundary;
import quotify_app.usecases.function.FunctionInteractor;
import quotify_app.usecases.function.FunctionOutputBoundary;

/**
 * The FunctionFactory class is responsible for setting up and wiring together the Function components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class FunctionFactory {

    private final FunctionViewModel functionViewModel;
    private final FunctionView functionView;
    private FunctionController functionController;

    /**
     * Initializes the FunctionFactory with its View and ViewModel.
     */
    public FunctionFactory() {
        this.functionViewModel = new FunctionViewModel();
        this.functionView = new FunctionView(functionViewModel);
    }

    /**
     * Sets up the FunctionController.
     *
     * @param viewManagerModel the ViewManagerModel from the AppBuilder.
     */
    public void setUpController(ViewManagerModel viewManagerModel) {
        // Setup Presenter and Interactor for CurrentPrice with necessary dependencies
        final FunctionOutputBoundary currentPricePresenter = new FunctionPresenter(
                viewManagerModel,
                functionViewModel
        );

        final FunctionInputBoundary currentPriceInteractor = new FunctionInteractor(currentPricePresenter);

        functionController = new FunctionController(currentPriceInteractor);
    }

    /**
     * Gets the FunctionView.
     *
     * @return the Functioniew.
     */
    public FunctionView getFunctionView() {
        return functionView;
    }

    /**
     * Gets the FunctionController.
     *
     * @return the FunctionController.
     */
    public FunctionController getFunctionController() {
        return functionController;
    }

    /**
     * Gets the FunctionViewModel.
     *
     * @return the FunctionModel.
     */
    public FunctionViewModel getFunctionViewModel() {
        return functionViewModel;
    }
}
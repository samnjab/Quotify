package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.currentprice.CurrentPriceController;
import quotify_app.adapters.currentprice.CurrentPricePresenter;
import quotify_app.adapters.currentprice.CurrentPriceViewModel;
import quotify_app.ui.CurrentPriceView;
import quotify_app.usecases.currentprice.CurrentPriceInputBoundary;
import quotify_app.usecases.currentprice.CurrentPriceInteractor;
import quotify_app.usecases.currentprice.CurrentPriceOutputBoundary;
import quotify_app.usecases.currentprice.PredictionDataAccessInterface;
import quotify_app.usecases.currentprice.CurrentPropertyDataAccessInterface;

/**
 * The CurrentPriceFactory class is responsible for setting up and wiring together the CurrentPrice components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class CurrentPriceFactory {

    private final CurrentPriceViewModel currentPriceViewModel;
    private final CurrentPriceView currentPriceView;
    private CurrentPriceController currentPriceController;

    /**
     * Initializes the CurrentPriceFactory with its View and ViewModel.
     */
    public CurrentPriceFactory() {
        this.currentPriceViewModel = new CurrentPriceViewModel();
        this.currentPriceView = new CurrentPriceView(currentPriceViewModel);
    }

    /**
     * Sets up the CurrentPriceController.
     *
     * @param viewManagerModel     the ViewManagerModel from the AppBuilder.
     * @param predictionDataAccess the PredictionDataAccessInterface for predictions.
     * @param propertyDataAccess   the PropertyDataAccessInterface for property data.
     */
    public void setUpController(ViewManagerModel viewManagerModel,
                                PredictionDataAccessInterface predictionDataAccess,
                                CurrentPropertyDataAccessInterface propertyDataAccess) {
        // Setup Presenter and Interactor for CurrentPrice with necessary dependencies
        final CurrentPriceOutputBoundary currentPricePresenter = new CurrentPricePresenter(
                viewManagerModel,
                currentPriceViewModel
        );

        final CurrentPriceInputBoundary currentPriceInteractor = new CurrentPriceInteractor(
                currentPricePresenter, predictionDataAccess, propertyDataAccess);

        currentPriceController = new CurrentPriceController(currentPriceInteractor);
    }

    /**
     * Gets the CurrentPriceView.
     *
     * @return the CurrentPriceView.
     */
    public CurrentPriceView getCurrentPriceView() {
        return currentPriceView;
    }

    /**
     * Gets the CurrentPriceController.
     *
     * @return the CurrentPriceController.
     */
    public CurrentPriceController getCurrentPriceController() {
        return currentPriceController;
    }

    /**
     * Gets the CurrentPriceViewModel.
     *
     * @return the CurrentPriceViewModel.
     */
    public CurrentPriceViewModel getCurrentPriceViewModel() {
        return currentPriceViewModel;
    }
}

package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.future_price.FuturePriceController;
import quotify_app.adapters.future_price.FuturePricePresenter;
import quotify_app.adapters.future_price.FuturePriceViewModel;
import quotify_app.ui.FuturePriceView;
import quotify_app.usecases.future_pricing.*;

/**
 * The FuturePriceFactory class is responsible for setting up and wiring together the FuturePrice components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class FuturePriceFactory {

    private final FuturePriceViewModel futurePriceViewModel;
    private final FuturePriceView futurePriceView;
    private FuturePriceController futurePriceController;

    /**
     * Initializes the CurrentPriceFactory with its View and ViewModel.
     */
    public FuturePriceFactory() {
        this.futurePriceViewModel = new FuturePriceViewModel();
        this.futurePriceView = new FuturePriceView(futurePriceViewModel);
    }

    /**
     * Sets up the FuturePriceController.
     *
     * @param viewManagerModel the ViewManagerModel from the AppBuilder.
     * @param futurePredictionDataAccessObject future prediction array
     * @param futurePropertyDataAccess property for the future prediction array
     */
    public void setUpController(ViewManagerModel viewManagerModel,
                                FuturePropertyDataAccessInterface futurePropertyDataAccess,
                                FuturePredictionDataAccessInterface futurePredictionDataAccessObject) {
        // Setup Presenter and Interactor for CurrentPrice with necessary dependencies
        final FuturePriceOutputBoundary futurePricePresenter = new FuturePricePresenter(
                viewManagerModel,
                futurePriceViewModel
        );

        final FuturePriceInputBoundary futurePriceInteractor = new FuturePriceInteractor(futurePricePresenter,
                futurePredictionDataAccessObject,
                futurePropertyDataAccess);

        futurePriceController = new FuturePriceController(futurePriceInteractor);
    }

    /**
     * Gets the FuturePriceView.
     *
     * @return the FuturePriceView.
     */
    public FuturePriceView getFuturePriceView() {
        return futurePriceView;
    }

    /**
     * Gets the FuturePriceController.
     *
     * @return the FuturePriceController.
     */
    public FuturePriceController getFuturePriceController() {
        return futurePriceController;
    }

    /**
     * Gets the FuturePriceViewModel.
     *
     * @return the FuturePriceViewModel.
     */
    public FuturePriceViewModel getFuturePriceViewModel() {
        return futurePriceViewModel;
    }
}

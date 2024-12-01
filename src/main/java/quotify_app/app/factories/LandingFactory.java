package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.landing.LandingController;
import quotify_app.adapters.landing.LandingPresenter;
import quotify_app.adapters.landing.LandingViewModel;
import quotify_app.data_access.AreaDataAccessObject;
import quotify_app.data_access.PropertyDataAccessObject;
import quotify_app.ui.LandingView;
import quotify_app.usecases.landing.LandingInputBoundary;
import quotify_app.usecases.landing.LandingInteractor;
import quotify_app.usecases.landing.LandingOutputBoundary;

/**
 * The LandingFactory class is responsible for setting up and wiring together the Landing components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class LandingFactory {

    private final LandingViewModel landingViewModel;
    private final LandingView landingView;
    private LandingController landingController;

    /**
     * Initializes the LandingFactory with its View and ViewModel.
     */
    public LandingFactory() {
        this.landingViewModel = new LandingViewModel();
        this.landingView = new LandingView(landingViewModel);
    }

    /**
     * Sets up the LandingController.
     * @param viewManagerModel The ViewManagerModel from the AppBuilder.
     * @param areaDataAccessObject the data access object for the area data.
     * @param propertyDataAccessObject the data access object for the property data.
     */
    public void setUpController(ViewManagerModel viewManagerModel,
                                AreaDataAccessObject areaDataAccessObject,
                                PropertyDataAccessObject propertyDataAccessObject) {

        // Setup Presenter and Interactor for Landing with necessary dependencies
        final LandingOutputBoundary landingPresenter = new LandingPresenter(
                landingViewModel,
                viewManagerModel
        );

        final LandingInputBoundary landingInteractor = new LandingInteractor(
                areaDataAccessObject, propertyDataAccessObject, landingPresenter);

        this.landingController = new LandingController(landingInteractor);
    }

    /**
     * Gets the LandingView.
     * @return the LandingView.
     */
    public LandingView getLandingView() {
        return landingView;
    }

    /**
     * Gets the LandingController.
     * @return the LandingController.
     */
    public LandingController getLandingController() {
        return landingController;
    }

    /**
     * Gets the LandingViewModel.
     * @return the LandingViewModel.
     */
    public LandingViewModel getLandingViewModel() {
        return landingViewModel;
    }
}

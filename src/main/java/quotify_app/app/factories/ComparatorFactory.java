package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.comparator.ComparatorController;
import quotify_app.adapters.comparator.ComparatorPresenter;
import quotify_app.adapters.comparator.ComparatorViewModel;
import quotify_app.data_access.ComparatorDataAccessObject;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;
import quotify_app.ui.ComparatorView;
import quotify_app.usecases.comparator.ComparatorDataAccessInterface;
import quotify_app.usecases.comparator.ComparatorInputBoundary;
import quotify_app.usecases.comparator.ComparatorInteractor;
import quotify_app.usecases.comparator.ComparatorOutputBoundary;

import java.util.List;

/**
 * The ComparatorFactory class is responsible for setting up and wiring together the Comparator components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class ComparatorFactory {

    private final ComparatorViewModel comparatorViewModel;
    private final ComparatorView comparatorView;
    private ComparatorController comparatorController;
    private ComparatorDataAccessInterface comparatorDataAccessObject;

    /**
     * Initializes the ComparatorFactory with its View and ViewModel.
     */
    public ComparatorFactory() {
        this.comparatorViewModel = new ComparatorViewModel();
        this.comparatorView = new ComparatorView(comparatorViewModel);
        this.comparatorDataAccessObject = new ComparatorDataAccessInterface() {
            @Override
            public Property getCurrentProperty() {
                return null;
            }

            @Override
            public void setCurrentProperty(Property property) {

            }

            @Override
            public List<Property> getSaleComparables(Area zipCode) throws ApiRequestException, ClientRequestException {
                return List.of();
            }
        };
    }

    /**
     * Sets up the ComparatorController.
     *
     * @param viewManagerModel the ViewManagerModel from the AppBuilder.
     */
    public void setUpController(ViewManagerModel viewManagerModel) {
        // Setup Presenter and Interactor for Comparator with necessary dependencies
        final ComparatorOutputBoundary comparatorPresenter = new ComparatorPresenter(
                viewManagerModel,
                comparatorViewModel
        );

        final ComparatorInputBoundary comparatorInteractor = new
                ComparatorInteractor(comparatorPresenter, comparatorDataAccessObject);

        comparatorController = new ComparatorController(comparatorInteractor);
    }

    /**
     * Gets the ComparatorView.
     *
     * @return the ComparatorView.
     */
    public ComparatorView getComparatorView() {
        return comparatorView;
    }

    /**
     * Gets the ComparatorController.
     *
     * @return the ComparatorController.
     */
    public ComparatorController getComparatorController() {
        return comparatorController;
    }

    /**
     * Gets the ComparatorViewModel.
     *
     * @return the ComparatorViewModel.
     */
    public ComparatorViewModel getComparatorViewModel() {
        return comparatorViewModel;
    }
}

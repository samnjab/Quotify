package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.comparator.ComparatorController;
import quotify_app.adapters.comparator.ComparatorPresenter;
import quotify_app.adapters.comparator.ComparatorViewModel;
import quotify_app.ui.ComparatorView;
import quotify_app.usecases.comparator.*;

/**
 * The ComparatorFactory class is responsible for setting up and wiring together the Comparator components
 * so that they can be built in the AppBuilder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class ComparatorFactory {

    private final ComparatorViewModel comparatorViewModel;
    private final ComparatorView comparatorView;
    private ComparatorController comparatorController;

    /**
     * Initializes the ComparatorFactory with its View and ViewModel.
     */
    public ComparatorFactory() {
        this.comparatorViewModel = new ComparatorViewModel();
        this.comparatorView = new ComparatorView(comparatorViewModel);

    }

    /**
     * Sets up the ComparatorController.
     *
     * @param viewManagerModel the ViewManagerModel from the AppBuilder.
     * @param comparatorDataAccessObject the propertydataaccessobject for the comparator usecase.
     */
    public void setUpController(ViewManagerModel viewManagerModel,
                                ComparatorDataAccessInterface comparatorDataAccessObject) {
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

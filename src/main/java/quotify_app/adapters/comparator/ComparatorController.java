package quotify_app.adapters.comparator;

import quotify_app.usecases.comparator.ComparatorInputBoundary;

/**
 * The controller for the Login Use Case.
 */
public class ComparatorController {

    private final ComparatorInputBoundary comparatorInteractor;

    public ComparatorController(ComparatorInputBoundary comparatorInteractor) {

        this.comparatorInteractor = comparatorInteractor;
    }

    /**
     * Triggers the navigation to CurrentPrice through the ComparatorInteractor.
     */
    public void goToCurrentPrice() {
        comparatorInteractor.goToCurrentPrice();
    }

    /**
     * Triggers the navigation to Input through the ComparatorInteractor.
     */
    public void goToInput() {
        comparatorInteractor.goToInput();
    }
}

package quotify_app.adapters.function;

import quotify_app.usecases.function.FunctionInputBoundary;

/**
 * The controller for the Login Use Case.
 */
public class FunctionController {

    private final FunctionInputBoundary functionInteractor;

    public FunctionController(FunctionInputBoundary functionInteractor) {

        this.functionInteractor = functionInteractor;
    }

    /**
     * Executes the Function Case.
     */
    public void execute() {
        functionInteractor.execute();
    }

    /**
     * Trigger view transition to currentPrice through the presenter.
     */
    public void goToCurrentPriceGuest() {
        functionInteractor.goToCurrentPrice();
    }

    /**
     * Trigger view transition to currentPriceLoggedIn.
     */
    public void goToCurrentPriceUser() {
        functionInteractor.goToCurrentPriceUser();
    }

    /**
     * Trigger view transition to comparator.
     */
    public void goToComparatorGuest() {
        functionInteractor.goToComparator();
    }

    /**
     * Trigger view transition to comparatorLoggedIn.
     */
    public void goToComparatorUser() {
        functionInteractor.goToComparatorUser();
    }
}

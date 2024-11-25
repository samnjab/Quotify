package quotify_app.adapters.function;

import quotify_app.usecases.function.FunctionInputBoundary;

/**
 * The controller for the Function Use Case.
 */
public class FunctionController {

    private final FunctionInputBoundary functionInteractor;

    public FunctionController(FunctionInputBoundary functionInteractor) {

        this.functionInteractor = functionInteractor;
    }

    /**
     * Trigger view transition to currentPrice through functionInteractor.
     */
    public void goToCurrentPrice() {
        functionInteractor.goToCurrentPrice();
    }

    /**
     * Trigger view transition to comparator through functionInteractor.
     */
    public void goToComparator() {
        functionInteractor.goToComparator();
    }

}

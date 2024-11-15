package quotify_app.usecases.function;

/**
 * Interactor for handling signup logic.
 */
public class FunctionInteractor implements FunctionInputBoundary {
    private final FunctionOutputBoundary functionPresenter;

    public FunctionInteractor(FunctionOutputBoundary functionPresenter) {
        this.functionPresenter = functionPresenter;
    }

    /**
     * Trigger view transition to currentPrice through the presenter.
     */
    public void goToCurrentPrice() {
        functionPresenter.goToCurrentPrice();
    }

    /**
     * Trigger view transition to comparator through the presenter.
     */
    public void goToComparator() {
        functionPresenter.goToComparator();
    }

}

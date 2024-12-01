package quotify_app.usecases.function;

/**
 * Interactor for handling function logic.
 */
public class FunctionInteractor implements FunctionInputBoundary {
    private final FunctionOutputBoundary functionPresenter;

    public FunctionInteractor(FunctionOutputBoundary functionPresenter) {
        this.functionPresenter = functionPresenter;
    }

    /**
     * Trigger view transition to function through the presenter.
     */
    @Override
    public void goToCurrentPrice() {
        functionPresenter.goToCurrentPrice();
    }

    /**
     * Trigger view transition to comparator through the presenter.
     */
    @Override
    public void goToComparator() {
        functionPresenter.goToComparator();
    }

}

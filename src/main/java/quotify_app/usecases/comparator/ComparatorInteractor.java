package quotify_app.usecases.comparator;

import quotify_app.entities.User;

/**
 * The Comparator Interactor.
 */
public class ComparatorInteractor implements ComparatorInputBoundary {
    private final ComparatorOutputBoundary comparatorPresenter;

    public ComparatorInteractor(
                                ComparatorOutputBoundary comparatorPresenter) {
        this.comparatorPresenter = comparatorPresenter;

    }

    /**
     * Trigger view transition to CurrentPrice through the presenter.
     */
    @Override
    public void goToCurrentPrice() {
        comparatorPresenter.goToCurrentPrice();
    }

    /**
     * Trigger view transition to Input through the presenter.
     */
    @Override
    public void goToInput() {
        comparatorPresenter.goToInput();
    }
}

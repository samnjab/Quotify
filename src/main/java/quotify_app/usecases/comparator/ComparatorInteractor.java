package quotify_app.usecases.comparator;

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

    @Override
    public void checkLoginStatus() {
        comparatorPresenter.updateLoginStatus();
    }

    @Override
    public void goToUserProfile() {
        comparatorPresenter.goToUserProfile();
    }
}

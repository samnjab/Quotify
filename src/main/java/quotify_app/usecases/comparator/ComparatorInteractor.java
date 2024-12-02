package quotify_app.usecases.comparator;

/**
 * The Comparator Interactor.
 */
public class ComparatorInteractor implements ComparatorInputBoundary {
    private final ComparatorOutputBoundary comparatorPresenter;
    private final ComparatorDataAccessInterface comparatorDataAccessObject;

    public ComparatorInteractor(
            ComparatorOutputBoundary comparatorPresenter,
            ComparatorDataAccessInterface comparatorDataAccessInterface) {
        this.comparatorPresenter = comparatorPresenter;
        this.comparatorDataAccessObject = comparatorDataAccessInterface;

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

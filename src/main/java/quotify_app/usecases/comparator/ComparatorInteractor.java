package quotify_app.usecases.comparator;

import quotify_app.data_access.AreaStore;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.landing.AreaOutputData;

import java.util.List;

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

    @Override
    public void getComparables() {
        try {
            final List<Property> comparables = comparatorDataAccessObject.getSaleComparables();
            // Update the presenter with the fetched properties
            comparatorPresenter.updateProperties(comparables);
        }
        catch (ClientRequestException | ApiRequestException err) {
            comparatorPresenter.presentCompareFailed();
        }
    }
}

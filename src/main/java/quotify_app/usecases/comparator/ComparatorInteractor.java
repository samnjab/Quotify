package quotify_app.usecases.comparator;

import quotify_app.adapters.comparator.ComparatorPresenter;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;

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
     * Fetches a list of properties that are similar to the current property based on certain criteria.
     *
     * @param area The area to search for comparable properties.
     * @return A list of `Property` objects that are most similar to the cached property.
     * @throws ApiRequestException If there is an issue with the external API request while fetching properties.
     * @throws ClientRequestException If there is an issue with the client request when fetching properties.
     */
    public void getComparables(Area area) throws ClientRequestException {
        final List<Property> comparables = comparatorDataAccessObject.getSaleComparables(area);
        // Update the presenter with the fetched properties
        comparatorPresenter.updateProperties(comparables);
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

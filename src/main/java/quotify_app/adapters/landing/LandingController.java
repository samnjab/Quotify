package quotify_app.adapters.landing;

import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.landing.AddressInputData;
import quotify_app.usecases.landing.LandingInputBoundary;

/**
 * The controller to communicate the actions performed by the user on the landing page to the interactor.
 */
public class LandingController {

    private final LandingInputBoundary landingInteractor;

    public LandingController(LandingInputBoundary landingInteractor) {
        this.landingInteractor = landingInteractor;
    }

    /**
     * Triggers a fetch of available countries from the Interactor.
     */
    public void fetchCountries() {
        landingInteractor.fetchCountries();
    }

    /**
     * Triggers a selection of the passed area.
     * @param area the area selected by the user.
     */
    public void selectArea(Area area) {
        landingInteractor.selectArea(area);
    }

    /**
     * Triggers a fetch of available subareas of the parenArea.
     * @param parentArea the selected parent area for which to fetch subareas.
     */
    public void fetchAvailableSubAreas(Area parentArea) {
        landingInteractor.fetchAreas(parentArea.getGeoIdV4(), parentArea.getType());
    }

    /**
     * Triggers select address with the address information collected from
     * user selection passed to the Interactor.
     * @param country the selected country.
     * @param state   the selected state.
     * @param city    the selected city.
     * @param zipCode the selected zip code area.
     * @param address the street address of the property.
     */
    public void selectAddress(Area country, Area state, Area city, Area zipCode, String address) {
        final AddressInputData addressInputData = new AddressInputData(country, state, city, zipCode, address);
        landingInteractor.selectAddress(addressInputData);
    }

    /**
     * Triggers navigation to the signup view.
     */
    public void goToSignup() {
        landingInteractor.goToSignup();
    }

    /**
     * Triggers navigation to the login view.
     */
    public void goToLogin() {
        landingInteractor.goToLogin();
    }

    /**
     * Triggers selection of property in cache and navigation to the login view.
     */
    public void goToNextPage() {
        landingInteractor.selectPropertyInCache();
    }
}

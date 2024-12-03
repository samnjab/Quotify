package quotify_app.adapters.landing;

import quotify_app.usecases.landing.AddressDataTransferObj;
import quotify_app.usecases.landing.AreaDataTransferObj;
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
     * @param areaDto the DTO representation of area selected by the user.
     */
    public void selectArea(AreaDataTransferObj areaDto) {
        landingInteractor.selectArea(areaDto);
    }

    /**
     * Triggers a fetch of available subareas of the parenArea.
     * @param parentAreaDto the DTO representation of selected parent area for which to fetch subareas.
     */
    public void fetchAvailableSubAreas(AreaDataTransferObj parentAreaDto) {
        landingInteractor.fetchAreas(parentAreaDto.getGeoIdV4(), parentAreaDto.getType());
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
    public void selectAddress(AreaDataTransferObj country, AreaDataTransferObj state,
                              AreaDataTransferObj city, AreaDataTransferObj zipCode, String address) {
        final AddressDataTransferObj addressDataTransferObj =
                new AddressDataTransferObj(country, state, city, zipCode, address);
        landingInteractor.selectAddress(addressDataTransferObj);
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

    /**
     * Triggers navigation to the user profile view.
     */
    public void goToUserProfile() {
        landingInteractor.goToUserProfile();
    }

    /**
     * Checks the login status.
     */
    public void checkLoginStatus() {
        landingInteractor.checkLoginStatus();
    }
}

package quotify_app.adapters.landing;

import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AddressInputData;
import quotify_app.usecases.landing.LandingInputBoundary;

/**
 * The controller for actions performed on the landing page.
 */
public class LandingController {

    private final LandingInputBoundary landingInteractor;

    public LandingController(LandingInputBoundary landingInteractor) {
        this.landingInteractor = landingInteractor;
    }

    /**
     * Executes the Select Address use case.
     * @param country the selected country.
     * @param state   the selected state.
     * @param city    the selected city.
     * @param zipCode the selected zip code area.
     * @param address the street address of the property.
     * @throws Exception when address selection fails.
     */
    public void selectAddress(Area country, Area state, Area city, Area zipCode, String address) {
        final AddressInputData addressInputData = new AddressInputData(country, state, city, zipCode, address);
        landingInteractor.selectAddress(addressInputData);
    }

    /**
     * Fetches available countries from the interactor.
     * @return a list of available countries.
     * @throws RuntimeException when interactor fails at fetching countries.
     */
    public void fetchCountries() {
        //   landingInteractor.getCountries();
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
}

package quotify_app.adapters.landing;

import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.LandingInputBoundary;
import quotify_app.usecases.landing.AddressInputData;

/**
 * The controller for actions performed on the landing page.
 */

public class LandingController {

    private final LandingInputBoundary landingInteractor;

    public LandingController(LandingInputBoundary landingInteractor) {
        this.landingInteractor = landingInteractor;
    }

    /**
     * Executes the Select Address usecase.
     * @param country the name of the country to pass to the interactor.
     * @param state the name of the state to pass to the interactor.
     * @param city the name of the city to pass to the interactor.
     * @param zipCode the zipCode to pass to the interactor.
     * @param stAddress the street address of the property to pass to the interactor.
     * @throws Exception when the selected region is not found within the database.
     */
    public void selectRegion(Area country, Area state, Area city, String stAddress) throws Exception {
        final AddressInputData addressInputData = new AddressInputData(country, state, city, stAddress);
        landingInteractor.selectAddress(addressInputData);
    }

    /**
     * Triggers the navigation to SignupView through the LandingInteractor.
     */
    public void goToSignup() {
        landingInteractor.goToSignup();
    }

    /**
     * Triggers the navigation to LoginView through the LandingInteractor.
     */
    public void goToLogin() {
        landingInteractor.goToLogin();
    }
}

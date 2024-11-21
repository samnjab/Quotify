package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Address;
import quotify_app.usecases.landing.AddressInputData;

import java.util.List;

/**
 * The Landing Interactor.
 */
public class LandingInteractor implements LandingInputBoundary {
    private final RegionDataAccessInterface regionDataAccessObject;
    private final LandingOutputBoundary landingPresenter;

    public LandingInteractor(RegionDataAccessInterface regionDataAccessInterface,
                             LandingOutputBoundary landingOutputBoundary) {
        this.regionDataAccessObject = regionDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
    }

    @Override
    public void selectAddress(AddressInputData addressInputData) throws Exception {
        final Area country = addressInputData.getCountry();
        final Area state = addressInputData.getState();
        final Area city = addressInputData.getCity();
        final Address address = addressInputData.getAddress();
//        makes an API call to find the property at address.
//        presenter moves to property confimation window if property is found.
//        presenter prepares a fail window if the property can't be accessed via the API.
    }

    /**
     * Trigger view transition to Signup through the presenter.
     */
    @Override
    public void goToSignup() {
        landingPresenter.goToSignup();
    }

    /**
     * Trigger view transition to Login through the presenter.
     */
    @Override
    public void goToLogin() {
        landingPresenter.goToLogin();
    }

    /**
     * Fetches and displays a list of available countries for the user to select.
     * @return a list of country names.
     */
    @Override
    public List<String> selectCountry() throws Exception {
//       locks in the country selected.
//       makes an API call to /state/lookup  to retrieve a list of states
//
        return null;
    }

    /**
     * Fetches and displays a list of states for the selected country.
     * @param countryCode the code of the selected country.
     * @return a list of state names.
     */
    @Override
    public List<String> selectState(String countryCode) throws Exception {
        // Empty implementation
        return null;
    }

    /**
     * Fetches and displays a list of cities for the selected state.
     * @param stateCode the code of the selected state.
     * @return a list of city names.
     */
    @Override
    public List<String> selectCity(String stateCode) throws Exception {
        // Empty implementation
        return null;
    }

    /**
     * Provides address suggestions based on a partial address input.
     * @param partialAddress the partial address input by the user.
     * @return a list of suggested address strings.
     */
    @Override
    public List<String> autoCompleteAddress(String partialAddress) throws Exception {
        // Empty implementation
        return null;
    }
}

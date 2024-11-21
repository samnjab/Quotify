package quotify_app.usecases.landing;

import java.util.List;

/**
 * Input Boundary for actions related to the landing page.
 */
public interface LandingInputBoundary {

    /**
     * Executes the Select Address use case.
     * @param regionInputData the input data containing the selected region.
     */
    void selectAddress(AddressInputData addressInputData) throws Exception;

    /**
     * Switches to the signup view.
     */
    void goToSignup();

    /**
     * Switches to the login view.
     */
    void goToLogin();

    /**
     * Fetches and displays a list of available countries for the user to select.
     * @return a list of country names.
     * @throws Exception if fetching the countries fails.
     */
    List<String> selectCountry() throws Exception;

    /**
     * Fetches and displays a list of states for the selected country.
     * @param countryCode the code of the selected country.
     * @return a list of state names.
     * @throws Exception if fetching the states fails.
     */
    List<String> selectState(String countryCode) throws Exception;

    /**
     * Fetches and displays a list of cities for the selected state.
     * @param stateCode the code of the selected state.
     * @return a list of city names.
     * @throws Exception if fetching the cities fails.
     */
    List<String> selectCity(String stateCode) throws Exception;

    /**
     * Provides address suggestions based on a partial address input.
     * @param partialAddress the partial address input by the user.
     * @return a list of suggested address strings.
     * @throws Exception if address autocomplete fails.
     */
    List<String> autoCompleteAddress(String partialAddress) throws Exception;
}

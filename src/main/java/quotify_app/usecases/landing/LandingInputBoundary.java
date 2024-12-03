package quotify_app.usecases.landing;

/**
 * Input Boundary for actions related to the landing page.
 */
public interface LandingInputBoundary {

    /**
     * Switches to the signup view.
     */
    void goToSignup();

    /**
     * Switches to the login view.
     */
    void goToLogin();

    /**
     * Fetches and calls presenter with the available countries for the user to select.
     */
    void fetchCountries();

    /**
     * Fetches and calls presenter with the available subareas of parent area
     * for the user to select.
     * @param geoIdV4 the geoId of the selected area
     * @param type the type of subarea to be fetched.
     */
    void fetchAreas(String geoIdV4, String type);

    /**
     * Fetches and calls presenter with the available areas for the user to select.
     * @param partialName the partial name of the area.
     * @param type  the type of the area.
     */
    void autoCompleteByName(String partialName, String type);

    /**
     * Selects area and calls presenter with the selected area.
     * @param areaDto the DTO representation of selected area.
     */
    void selectArea(AreaDataTransferObj areaDto);

    /**
     * Executes the Select Address use case.
     * @param addressDataTransferObj the input data containing the selected region.
     */
    void selectAddress(AddressDataTransferObj addressDataTransferObj);

    /**
     * * Selects passed property as current property and caches in data access.
     */
    void selectPropertyInCache();

    /**
     * Triggers navigation to the user profile view.
     */
    void goToUserProfile();

    /**
     * Checks the login status.
     */
    void checkLoginStatus();
}

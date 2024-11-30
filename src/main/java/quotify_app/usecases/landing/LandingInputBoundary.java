package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;

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
     * Selects area and calls presenter with the selected area.
     * @param area the selected area
     */
    void selectArea(Area area);

    /**
     * Fetches and calls presenter with the available areas for the user to select.
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
     * Executes the Select Address use case.
     * @param addressInputData the input data containing the selected region.
     */
    void selectAddress(AddressInputData addressInputData);
}

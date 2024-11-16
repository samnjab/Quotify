package quotify_app.usecases.landing;

/**
 * Input Boundary for actions related to the landing page.
 */
public interface LandingInputBoundary {

    /**
     * Executes the Select Region use case.
     * @param regionInputData the input data containing the selected region.
     */
    void selectRegion(RegionInputData regionInputData);

    /**
     * Switches to the signup view.
     */
    void goToSignup();

    /**
     * Switches to the login view.
     */
    void goToLogin();
}

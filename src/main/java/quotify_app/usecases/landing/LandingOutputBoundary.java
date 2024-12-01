package quotify_app.usecases.landing;

/**
 * Output Boundary for presenting the result of the actions perfomred on the landing page.
 */
public interface LandingOutputBoundary {

    /**
     * Prepares the success view for the Select Area Use Case.
     * @param areaOutputData the output data containing selected area result
     */
    void prepareAreaSuccessView(AreaOutputData areaOutputData);

    /**
     * Prepares the success view for the fetch list of areas Use Case.
     * @param outputData the output data containing list of available areas.
     */
    void prepareAreaListSuccessView(AreaListOutputData outputData);

    /**
     * Prepares the failure view for the fetch list of area Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareAreaListFailView(String errorMessage);

    /**
     *  Interface for executing the switch to sign up screen usecase.
     */
    void goToSignup();

    /**
     *  Interface for executing the switch to Login screen usecase.
     */
    void goToLogin();

    /**
     *  Prepares an Error message view for a general error.
     * @param errorMessage the message to be presented to the user.
     */
    void prepareErrorView(String errorMessage);
}

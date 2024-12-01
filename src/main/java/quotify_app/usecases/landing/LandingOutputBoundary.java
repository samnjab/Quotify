package quotify_app.usecases.landing;

/**
 * Output Boundary for presenting the result of the actions performed on the landing page.
 */
public interface LandingOutputBoundary {

    /**
     * Prepares the success view for the Select Area Use Case.
     * @param areaOutputData The output data containing the selected area result.
     */
    void prepareAreaSuccessView(AreaOutputData areaOutputData);

    /**
     * Prepares the success view for the fetch list of areas Use Case.
     * @param outputData The output data containing the list of available areas.
     */
    void prepareAreaListSuccessView(AreaListOutputData outputData);

    /**
     * Prepares the failure view for the fetch list of areas Use Case.
     * @param errorMessage The explanation of the failure.
     */
    void prepareAreaListFailView(String errorMessage);

    /**
     * Prepares the success view for property retrieval.
     * @param propertyOutputData The output data containing property details.
     */
    void preparePropertySuccessView(PropertyOutputData propertyOutputData);

    /**
     * Prepares the failure view for property retrieval.
     * @param errorMessage The explanation of the failure.
     */
    void preparePropertyFailView(String errorMessage);

    /**
     * Prepares an error message view for a general error.
     * @param errorMessage The message to be presented to the user.
     */
    void prepareErrorView(String errorMessage);

    /**
     * Interface for executing the switch to the sign-up screen Use Case.
     */
    void goToSignup();

    /**
     * Interface for executing the switch to the login screen Use Case.
     */
    void goToLogin();
}

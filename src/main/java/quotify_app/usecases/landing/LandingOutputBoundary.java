package quotify_app.usecases.landing;

/**
 * Output Boundary for presenting the result of the actions perfomred on the landing page.
 */
public interface LandingOutputBoundary {

    /**
     * Prepares the success view for the Select Region Use Case.
     * @param regionOutputData the output data containing login result
     */
    void prepareSuccessView(RegionOutputData regionOutputData);

    /**
     * Prepares the failure view for the Select Region Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     *  Interface for executing the switch to sign up screen usecase.
     */
    void goToSignup();

    /**
     *  Interface for executing the switch to Login screen usecase.
     */
    void goToLogin();
}

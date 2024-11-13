package quotify_app.usecases.login;

/**
 * Output Boundary for presenting the result of the Login Use Case.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data containing login result
     */
    void prepareSuccessView(LoginOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     *  Interface for executing the switch to sign up screen usecase.
     */
    void goToSignup();
}

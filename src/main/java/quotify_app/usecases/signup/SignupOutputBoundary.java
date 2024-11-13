package quotify_app.usecases.signup;

/**
 * Output Boundary for the Signup Use Case.
 * Defines methods for handling success and failure outcomes.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the view for a successful signup.
     *
     * @param outputData Data related to the successful signup
     */
    void prepareSuccessView(SignupOutputData outputData);

    /**
     * Prepares the view for a failed signup attempt.
     *
     * @param errorMessage The error message explaining the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Interface for the presenter to execute the switch to login usecase.
     */
    void goToLogin();
}

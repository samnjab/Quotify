package quotify_app.usecases.signup;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface SignupInputBoundary {
    /**
     * Executes the signup use case.
     * @param inputData the input data containing signup credentials
     */
    void execute(SignupInputData inputData);

    /**
     * Executes the switch to login screen use case.
     */
    void goToLogin();
}

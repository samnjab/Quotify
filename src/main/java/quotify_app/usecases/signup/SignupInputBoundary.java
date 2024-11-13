package quotify_app.usecases.signup;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface SignupInputBoundary {
    void execute(SignupInputData inputData);
    void goToLogin();
}

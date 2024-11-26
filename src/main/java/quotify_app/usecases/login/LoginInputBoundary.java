package quotify_app.usecases.login;

/**
 * Input Boundary for actions related to logging in.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case.
     * @param loginInputData the input data containing login credentials
     */
    void execute(LoginInputData loginInputData);

    /**
     * Switches to the signup view.
     */
    void goToSignup();

    /**
     * Switches to the function view.
     */
    void goToFunction();
}

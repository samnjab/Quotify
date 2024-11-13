package quotify_app.adapters.login;

import quotify_app.usecases.login.LoginInputBoundary;
import quotify_app.usecases.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * Executes the Login usecase.
     * @param username the username to pass to the interactor.
     * @param password the password to pass to the interactor
     */
    public void execute(String username, String password) {
        final LoginInputData inputData = new LoginInputData(username, password);
        loginInteractor.execute(inputData);
    }

    /**
     * Triggers the navigation to SignupView through the LoginInteractor.
     */
    public void goToSignup() {
        loginInteractor.goToSignup();
    }
}

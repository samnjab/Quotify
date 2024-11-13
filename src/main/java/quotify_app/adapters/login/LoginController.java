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

    public void execute(String username, String password) {
        LoginInputData inputData = new LoginInputData(username, password);
        loginInteractor.execute(inputData);
    }

    /**
     * Triggers the navigation to SignupView through the LoginInteractor.
     */
    public void goToSignup() {
        loginInteractor.goToSignup();
    }
}

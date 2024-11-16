package quotify_app.adapters.landing;

import quotify_app.usecases.landing.LandingInputBoundary;
import quotify_app.usecases.landing.LandingInputData;

/**
 * The controller for the Select Region usecase on the landing page .
 */
public class LandingController {

    private final LandingInputBoundary landingInteractor;

    public LandingController(LoginInputBoundary loginInteractor) {
        this.landingInteractor = landingInteractor;
    }

    /**
     * Executes the Login usecase.
     * @param username the username to pass to the interactor.
     * @param password the password to pass to the interactor
     */
    public void execute(String username, String password) {
        final LoginInputData inputData = new LoginInputData(username, password);
        landingInteractor.execute(inputData);
    }

    /**
     * Triggers the navigation to SignupView through the LoginInteractor.
     */
    public void goToSignup() {
        landingInteractor.goToSignup();
    }
}

package quotify_app.adapters.signup;

import quotify_app.usecases.signup.SignupInputBoundary;
import quotify_app.usecases.signup.SignupInputData;

/**
 * The controller for the Signup Use Case.
 */
public class SignupController {
    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {

        this.signupInteractor = signupInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username of the user signing up
     * @param email the email of the user signing up
     * @param password the password of the user signing up
     */
    public void execute(String username, String email, String password) {
        final SignupInputData inputData = new SignupInputData(username, email, password);
        signupInteractor.execute(inputData);
    }

    /**
     *  Executes the Switch to Login usecase.
     */
    public void goToLogin() {
        signupInteractor.goToLogin();
    }
}

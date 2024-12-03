// LoginPresenter.java
package quotify_app.adapters.login;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.app.ApplicationState;
import quotify_app.entities.User;
import quotify_app.usecases.login.LoginOutputBoundary;
import quotify_app.usecases.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    /**
     * Constructs a new LoginPresenter with the specified view models.
     *
     * @param viewManagerModel The ViewManagerModel used to manage view transitions.
     * @param loginViewModel   The LoginViewModel used to manage the login view state.
     * @param signupViewModel  The SignupViewModel used to navigate to the signup view.
     */
    public LoginPresenter(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        // Clear any login error and update state
        loginViewModel.getState().setLoginError("");
        loginViewModel.firePropertyChanged();

        // Update the ApplicationState to reflect the successful login
        final String loggedInUser = outputData.getUser();
        ApplicationState.getInstance().setLoggedIn(true, loggedInUser);

        // Transition to the desired view after login
        viewManagerModel.setState("current price");
        viewManagerModel.firePropertyChanged();

        System.out.println("Successfully logged in");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Set login error and notify view
        loginViewModel.getState().setLoginError(errorMessage);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Navigates to the Signup View.
     */
    @Override
    public void goToSignup() {
        // Transition to signup view
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

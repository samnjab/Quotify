package quotify_app.adapters.login;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.usecases.login.LoginOutputBoundary;
import quotify_app.usecases.login.LoginOutputData;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.adapters.signup.SignupState;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        // Clear any login error and update state
        loginViewModel.getState().setLoginError("");
        loginViewModel.firePropertyChanged();

        // Trigger view change if login is successful
        viewManagerModel.setState("logged in");  // Assumes there's a "logged in" view state
        viewManagerModel.firePropertyChanged();
        //Debugging
        System.out.println("successfully logged in");
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
        viewManagerModel.setState(signupViewModel.getViewName());  // Transition to signup view
        viewManagerModel.firePropertyChanged();
    }
}

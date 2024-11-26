package quotify_app.adapters.login;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.usecases.login.LoginOutputBoundary;
import quotify_app.usecases.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final FunctionViewModel functionViewModel;

    public LoginPresenter(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.functionViewModel = new FunctionViewModel();
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        // Clear any login error and update state
        loginViewModel.getState().setLoginError("");
        loginViewModel.firePropertyChanged();

        // Trigger view change if login is successful, assumes there's a "logged in" view state
        viewManagerModel.setState("logged in");
        viewManagerModel.firePropertyChanged();
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
        // Transition to signup view
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goToFunctionView() {
        viewManagerModel.setState(functionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

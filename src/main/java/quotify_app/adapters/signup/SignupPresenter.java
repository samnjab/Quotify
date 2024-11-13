package quotify_app.adapters.signup;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.usecases.signup.SignupOutputBoundary;
import quotify_app.usecases.signup.SignupOutputData;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.login.LoginState;

/**
 * Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        viewManagerModel.setState("login");  // Switch to login view on success
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        signupViewModel.getState().setSignupError(errorMessage);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void goToLogin() {
        viewManagerModel.setState(loginViewModel.getViewName());
        System.out.println(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package quotify_app.adapters.landing;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.usecases.landing.LandingOutputBoundary;
import quotify_app.usecases.landing.AreaOutputData;

/**
 * The Presenter for the Landing page.
 */

public class LandingPresenter implements LandingOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;

    public LandingPresenter(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            LandingViewModel landingViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(AreaOutputData areaOutputData) {
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

    /**
     * Navigates to the Signup View.
     */
    @Override
    public void goToLoginView() {
        // Transition to Login view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

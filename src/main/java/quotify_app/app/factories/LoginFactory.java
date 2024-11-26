package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.login.LoginController;
import quotify_app.adapters.login.LoginPresenter;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.ui.LoginView;
import quotify_app.usecases.login.LoginInputBoundary;
import quotify_app.usecases.login.LoginInteractor;
import quotify_app.usecases.login.LoginOutputBoundary;

/**
 * The LoginFactory class is responsible for setting up and wiring together the Login Adapter such
 * that it can be built in the App Builder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class LoginFactory {
    private final LoginViewModel loginViewModel;
    private final LoginView loginView;
    private LoginController loginController;

    /**
     * Instantiate a login factory with it's view and view model.
     */
    public LoginFactory() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
    }

    /**
     * Set up the login controller.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param signupFactory pass the signupFactory object to retrieve it's viewModel
     * @param userDataAccessObject pass the DAO from the app builder holding the user data
     */
    public void setUpController(SignupFactory signupFactory, ViewManagerModel viewManagerModel,
                                DBUserDataAccessObject userDataAccessObject) {
        // Setup Controller for Login with necessary dependencies
        loginController = new LoginController(
                getLoginInputBoundary(viewManagerModel, signupFactory, userDataAccessObject));
    }

    /**
     * Setup the login Input boundary and return the Login interactor.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param signupFactory Signup Factory
     * @param userDataAccessObject pass the DAO from the app builder holding the user data
     * @return Login interactor
     */
    private LoginInputBoundary getLoginInputBoundary(ViewManagerModel viewManagerModel, SignupFactory signupFactory,
                                                     DBUserDataAccessObject userDataAccessObject) {
        return new LoginInteractor(userDataAccessObject,
                getLoginOutputBoundary(viewManagerModel, signupFactory));
    }

    /**
     * Setup Presenter for Login with necessary dependencies.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param signupFactory pass the signup Factory to retrieve it's view model
     * @return the login output boundary, the presenter
     */
    private LoginOutputBoundary getLoginOutputBoundary(ViewManagerModel viewManagerModel, SignupFactory signupFactory) {
        return new LoginPresenter(
                viewManagerModel,
                loginViewModel,
                signupFactory.getSignupViewModel()
        );
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}

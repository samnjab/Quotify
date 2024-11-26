package quotify_app.app.factories;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.signup.SignupController;
import quotify_app.adapters.signup.SignupPresenter;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.ui.SignupView;
import quotify_app.usecases.signup.SignupInputBoundary;
import quotify_app.usecases.signup.SignupInteractor;
import quotify_app.usecases.signup.SignupOutputBoundary;

/**
 * The SignupFactory class is responsible for setting up and wiring together the Signup Adapter such
 * that it can be built in the App Builder.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class SignupFactory {

    private final SignupViewModel signupViewModel;
    private final SignupView signupView;

    private SignupController signupController;

    /**
     * Instantiate a signup factory with its view and view model.
     */
    public SignupFactory() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
    }

    /**
     * Set up the login controller.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param loginFactory pass the signupFactory object to retrieve it's viewModel
     * @param userDataAccessObject pass the DAO from the app builder holding the user data
     */
    public void setUpController(LoginFactory loginFactory, ViewManagerModel viewManagerModel,
                                DBUserDataAccessObject userDataAccessObject) {
        // Setup Controller for Login with necessary dependencies
        signupController = new SignupController(getSignupInputBoundary(viewManagerModel, loginFactory,
                userDataAccessObject));
    }

    /**
     * Setup the login Input boundary and return the Login interactor.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param loginFactory pass the login factory in order to get the login view model
     * @param userDataAccessObject pass the DAO from the app builder holding the user data
     * @return the Signup interactor
     */
    private SignupInputBoundary getSignupInputBoundary(ViewManagerModel viewManagerModel, LoginFactory loginFactory,
                                                       DBUserDataAccessObject userDataAccessObject) {

        return new SignupInteractor(
                userDataAccessObject,
                getSignupOutputBoundary(viewManagerModel, loginFactory),
                userDataAccessObject.getUserFactory()
        );
    }

    /**
     * Return the signup output boundary.
     * @param viewManagerModel pass the viewManagerModel from the app builder
     * @param loginFactory this is needed to pass to the presenter
     * @return the signup presenter
     */
    private SignupOutputBoundary getSignupOutputBoundary(ViewManagerModel viewManagerModel, LoginFactory loginFactory) {
        return new SignupPresenter(
                viewManagerModel,
                signupViewModel,
                loginFactory.getLoginViewModel()
        );
    }

    public SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    public SignupView getSignupView() {
        return signupView;
    }

    public SignupController getSignupController() {
        return signupController;
    }
}

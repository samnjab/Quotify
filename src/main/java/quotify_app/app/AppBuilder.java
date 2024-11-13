package quotify_app.app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.login.LoginController;
import quotify_app.adapters.login.LoginPresenter;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.signup.SignupController;
import quotify_app.adapters.signup.SignupPresenter;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.entities.CommonUserFactory;
import quotify_app.entities.UserFactory;
import quotify_app.ui.LoginView;
import quotify_app.ui.SignupView;
import quotify_app.ui.ViewManager;
import quotify_app.usecases.login.LoginInputBoundary;
import quotify_app.usecases.login.LoginInteractor;
import quotify_app.usecases.login.LoginOutputBoundary;
import quotify_app.usecases.signup.SignupInputBoundary;
import quotify_app.usecases.signup.SignupInteractor;
import quotify_app.usecases.signup.SignupOutputBoundary;

/**
 * The AppBuilder class is responsible for setting up and wiring together the components of the application.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginView loginView;
    private LoginViewModel loginViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        // Setup presenter and interactor with necessary dependencies
        final SignupOutputBoundary signupPresenter = new SignupPresenter(
                viewManagerModel,
                signupViewModel,
                loginViewModel
        );
        final SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject,
                signupPresenter,
                userFactory
        );

        // Controller connects the view to the interactor
        final SignupController signupController = new SignupController(signupInteractor);
        signupView.setSignupController(signupController);

        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        // Setup Presenter and Interactor for Login with necessary dependencies
        final LoginOutputBoundary loginPresenter = new LoginPresenter(
                viewManagerModel,
                loginViewModel,
                signupViewModel
        );
        final LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);

        return this;
    }

    /**
     * Sets up the JFrame for the application and establishes the initial view state.
     * @return the application JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame("Quotify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Adding the card panel which holds all views
        application.add(cardPanel);

        // Setting the initial view to SignupView
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

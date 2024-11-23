package quotify_app.app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.app.factories.CurrentPriceFactory;
import quotify_app.app.factories.FuturePriceFactory;
import quotify_app.app.factories.LoginFactory;
import quotify_app.app.factories.SignupFactory;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.entities.CommonUserFactory;
import quotify_app.ui.ViewManager;

/**
 * The AppBuilder class is responsible for setting up and wiring together the components of the application.
 * It follows the Clean Architecture principles to establish each layer's dependencies and constructs the UI flow.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final SignupFactory signupFactory = new SignupFactory();
    private final LoginFactory loginFactory = new LoginFactory();
    private final CurrentPriceFactory currentPriceFactory = new CurrentPriceFactory();
    private final FuturePriceFactory futurePriceFactory = new FuturePriceFactory();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(new CommonUserFactory());
        loginFactory.setUpController(signupFactory, viewManagerModel, userDataAccessObject);
        signupFactory.setUpController(loginFactory, viewManagerModel, userDataAccessObject);
        currentPriceFactory.setUpController(viewManagerModel);
        futurePriceFactory.setUpController(viewManagerModel);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        cardPanel.add(signupFactory.getSignupView(), signupFactory.getSignupView().getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        signupFactory.getSignupView().setSignupController(signupFactory.getSignupController());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        cardPanel.add(loginFactory.getLoginView(), loginFactory.getLoginView().getViewName());
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        loginFactory.getLoginView().setLoginController(loginFactory.getLoginController());
        return this;
    }

    /**
     * Adds the CurrentPrice View to the application.
     * @return this builder
     */
    public AppBuilder addCurrentPriceView() {
        cardPanel
                .add(currentPriceFactory.getCurrentPriceView(),
                        currentPriceFactory.getCurrentPriceView().getViewName());
        return this;
    }

    /**
     * Adds the CurrentPrice Use Case to the application.
     * @return this builder
     */
    public AppBuilder addCurrentPriceUseCase() {
        currentPriceFactory.getCurrentPriceView()
                .setCurrentPriceController(currentPriceFactory.getCurrentPriceController());
        return this;
    }

    /**
     * Adds the FuturePrice View to the application.
     * @return this builder
     */
    public AppBuilder addFuturePriceView() {
        cardPanel
                .add(futurePriceFactory.getFuturePriceView(),
                        futurePriceFactory.getFuturePriceView().getViewName());
        return this;
    }

    /**
     * Adds the FuturePrice Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFuturePriceUseCase() {
        futurePriceFactory.getFuturePriceView()
                .setFuturePriceController(futurePriceFactory.getFuturePriceController());
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
        viewManagerModel.setState(signupFactory.getSignupView().getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

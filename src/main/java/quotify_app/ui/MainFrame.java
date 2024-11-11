package quotify_app.ui;

import quotify_app.adapters.UserController;
import quotify_app.adapters.UserRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final UserController userController;
    private final RegisterPanel registerPanel;  // Store a reference to RegisterPanel
    private final LoginPanel loginPanel;        // Store a reference to LoginPanel
    private final LoggedInPanel loggedInPanel;
    private final UserProfilePanel userProfilePanel;

    public MainFrame() {
        userController = new UserController(new UserRepositoryImpl());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        registerPanel = new RegisterPanel(this, userController);
        loginPanel = new LoginPanel(this, userController);
        loggedInPanel = new LoggedInPanel(this, userController);
        userProfilePanel = new UserProfilePanel(this, userController);

        mainPanel.add(registerPanel, "Register");
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(loggedInPanel, "LoggedIn");
        mainPanel.add(userProfilePanel, "UserProfile");

        add(mainPanel);
        setTitle("Quotify");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void showPanel(String name) {
        if ("Register".equals(name)) {
            registerPanel.clearFields();  // Clear fields when showing Register panel
        } else if ("Login".equals(name)) {
            loginPanel.clearFields();     // Clear fields when showing Login panel
        } else if ("LoggedIn".equals(name)) {
            loggedInPanel.updateWelcomeMessage();  // Refresh welcome message on LoggedInPanel
        }
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}

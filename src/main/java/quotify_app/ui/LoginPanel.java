package quotify_app.ui;

import quotify_app.adapters.UserController;
import quotify_app.adapters.UserPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final UserController userController;
    private final UserPresenter userPresenter;
    private final MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame, UserController userController) {
        this.mainFrame = mainFrame;
        this.userController = userController;
        this.userPresenter = new UserPresenter();

        setLayout(new BorderLayout());

        add(new HeaderPanel(), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 1));
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton switchToRegisterButton = new JButton("Switch to Register");
        JButton guestButton = new JButton("Continue as Guest");

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(switchToRegisterButton);
        formPanel.add(guestButton);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Check if any field is empty
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Both username and password must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userController.loginUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, userPresenter.getSuccessMessage());
                    mainFrame.showPanel("LoggedIn");
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, userPresenter.getLoginErrorMessage());
                }
            }
        });

        switchToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Register");
                clearFields();
            }
        });

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userController.continueAsGuest()) {
                    mainFrame.showPanel("LoggedIn");
                }
            }
        });
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}

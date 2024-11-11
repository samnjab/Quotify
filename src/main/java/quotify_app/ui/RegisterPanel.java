package quotify_app.ui;

import quotify_app.adapters.UserController;
import quotify_app.adapters.UserPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private final JTextField emailField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final UserController userController;
    private final UserPresenter userPresenter;
    private final MainFrame mainFrame;

    public RegisterPanel(MainFrame mainFrame, UserController userController) {
        this.mainFrame = mainFrame;
        this.userController = userController;
        this.userPresenter = new UserPresenter();

        setLayout(new BorderLayout());

        add(new HeaderPanel(), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 1));
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton switchToLoginButton = new JButton("Switch to Login");
        JButton guestButton = new JButton("Continue as Guest");

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(registerButton);
        formPanel.add(switchToLoginButton);
        formPanel.add(guestButton);

        add(formPanel, BorderLayout.CENTER);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Check if any field is empty
                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Register and check for specific error messages
                String result = userController.registerUser(email, username, password);
                if (result == null) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, userPresenter.getSuccessMessage());
                    mainFrame.showPanel("Login");
                } else {
                    JOptionPane.showMessageDialog(RegisterPanel.this, result);  // Show specific error message
                }
            }
        });

        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Login");
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
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
    }
}

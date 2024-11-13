package quotify_app.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import quotify_app.adapters.login.LoginController;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.login.LoginState;
import quotify_app.adapters.signup.SignupState;

/**
 * The view for logging in.
 */
public class LoginView extends JPanel implements PropertyChangeListener {
    private final String viewName = "log in";
    private LoginController loginController;
    private final LoginViewModel loginViewModel;

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JLabel errorLabel = new JLabel();

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel(LoginViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_LABEL);
        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel(LoginViewModel.PASSWORD_LABEL);
        add(passwordLabel);
        add(passwordField);

        errorLabel.setForeground(Color.RED);

        JButton loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(loginButton)) {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        JButton goToSignupButton = new JButton(LoginViewModel.TO_SIGNUP_BUTTON_LABEL);
        goToSignupButton.addActionListener(e -> loginController.goToSignup());

        addUsernameListener();
        addPasswordListener();

        add(loginButton);
        add(goToSignupButton);
        add(errorLabel);
    }

    private void addUsernameListener() {
        usernameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addPasswordListener() {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = loginViewModel.getState();
        errorLabel.setText(state.getLoginError());
    }
}

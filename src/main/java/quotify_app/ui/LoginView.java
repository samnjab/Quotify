package quotify_app.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import quotify_app.adapters.login.LoginController;
import quotify_app.adapters.login.LoginState;
import quotify_app.adapters.login.LoginViewModel;

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
        final JLabel titleLabel = new JLabel(LoginViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        final JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_LABEL);
        add(usernameLabel);
        add(usernameField);

        final JLabel passwordLabel = new JLabel(LoginViewModel.PASSWORD_LABEL);
        add(passwordLabel);
        add(passwordField);

        errorLabel.setForeground(Color.RED);

        final JButton loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
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

        final JButton goToSignupButton = new JButton(LoginViewModel.TO_SIGNUP_BUTTON_LABEL);
        goToSignupButton.addActionListener(evt -> loginController.goToSignup());

        addUsernameListener();
        addPasswordListener();

        add(loginButton);
        add(goToSignupButton);
        add(errorLabel);
    }

    /**
     * Adds an event listener for the username input field.
     */
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

    /**
     * Adds an event listener for the password input field.
     */
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

    /**
     * Initializes the LoginController.
     * @param loginController takes a LoginController as an input.
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Getter for the LoginView viewname.
     * @return a viewname string
     */
    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = loginViewModel.getState();
        errorLabel.setText(state.getLoginError());
    }
}

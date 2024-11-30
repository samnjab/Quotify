package quotify_app.ui;

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

import quotify_app.adapters.signup.SignupController;
import quotify_app.adapters.signup.SignupState;
import quotify_app.adapters.signup.SignupViewModel;

/**
 * The View for when the user is signing up.
 */
public class SignupView extends JPanel implements PropertyChangeListener {
    private final String viewName = "sign up";
    private final SignupViewModel signupViewModel;
    private SignupController signupController;

    private final JTextField emailField = new JTextField(15);
    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JLabel signupErrorLabel = new JLabel();

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel(SignupViewModel.TITLE_LABEL));
        add(new JLabel(SignupViewModel.EMAIL_LABEL));
        add(emailField);
        add(new JLabel(SignupViewModel.USERNAME_LABEL));
        add(usernameField);
        add(new JLabel(SignupViewModel.PASSWORD_LABEL));
        add(passwordField);

        final JButton signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        final JButton toLoginButton = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);

        // triggers the signup usecase upon button click
        signupButton.addActionListener(
            new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signupButton)) {
                            final SignupState currentState = signupViewModel.getState();
                            // debug: before signupcontroller exec
                            signupController.execute(
                                   currentState.getUsername(),
                                   currentState.getEmail(),
                                   currentState.getPassword()
                            );
                        }
                    }
            }
        );

        toLoginButton.addActionListener(evt -> signupController.goToLogin());

        addEmailListener();
        addUsernameListener();
        addPasswordListener();

        add(toLoginButton);
        add(signupButton);
        add(signupErrorLabel);
    }

    /**
     * Adds an event listener for the email input field.
     */
    private void addEmailListener() {
        emailField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setEmail(emailField.getText());
                signupViewModel.setState(currentState);
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
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                signupViewModel.setState(currentState);
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
     * Adds an event listener for the username input field.
     */
    private void addUsernameListener() {
        usernameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameField.getText());
                signupViewModel.setState(currentState);
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
     * Initializes the controller for the signupview.
     * @param signupController pass the signupController
     */
    public void setSignupController(SignupController signupController) {
        this.signupController = signupController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = signupViewModel.getState();
        signupErrorLabel.setText(state.getSignupError());
        emailField.setText(state.getEmail());
        usernameField.setText(state.getUsername());
        passwordField.setText(state.getPassword());
    }

    /**
     * Getter function for the signupview viewname.
     * @return a string representing the viewname of the signup view.
     */
    public String getViewName() {
        return signupViewModel.getViewName();
    }
}

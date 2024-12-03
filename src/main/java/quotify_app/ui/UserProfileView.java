package quotify_app.ui;

import quotify_app.adapters.userprofile.UserProfileController;
import quotify_app.adapters.userprofile.UserProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A class for the JPanel view of the User Profile.
 */
public class UserProfileView extends JPanel implements PropertyChangeListener {
    private final String viewName = "user profile";
    private UserProfileController userProfileController;
    private final UserProfileViewModel userProfileViewModel;

    // UI Components
    private final JLabel userProfileTitle = new JLabel();
    private final JLabel usernameLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel createdAtLabel = new JLabel();
    private final JButton landingPageButton = new JButton();

    public UserProfileView(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        initializeUserInterface();
    }

    /**
     * Sets the userProfileController for this View.
     *
     * @param userProfileController the Controller to be set.
     */
    public void setUserProfileController(UserProfileController userProfileController) {
        this.userProfileController = userProfileController;
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initializeUserInterface() {
        // Set layout manager
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        userProfileTitle.setText(UserProfileViewModel.TITLE_LABEL);
        userProfileTitle.setFont(new Font("Arial", Font.BOLD, 18));
        userProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(userProfileTitle, BorderLayout.NORTH);

        // Center panel for user information
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        usernameLabel.setText(UserProfileViewModel.ACCOUNT_USERNAME_INFO + "Loading...");
        emailLabel.setText(UserProfileViewModel.ACCOUNT_EMAIL_INFO + "Loading...");
        createdAtLabel.setText(UserProfileViewModel.ACCOUNT_CREATION_INFO + "Loading...");
        centerPanel.add(usernameLabel);
        centerPanel.add(emailLabel);
        centerPanel.add(createdAtLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with button
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        landingPageButton.setText(UserProfileViewModel.LANDING_PAGE_BUTTON_LABEL);
        bottomPanel.add(landingPageButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Register action listeners
        addActionListeners();

        // Initialize the view with current state
        updateView();
    }

    /**
     * Updates the view based on the current state of the ViewModel.
     */
    private void updateView() {
        usernameLabel.setText(UserProfileViewModel.ACCOUNT_USERNAME_INFO + userProfileViewModel.getState().getCurrentUser());
        emailLabel.setText(UserProfileViewModel.ACCOUNT_EMAIL_INFO + userProfileViewModel.getState().getCurrentEmail());
        createdAtLabel.setText(UserProfileViewModel.ACCOUNT_CREATION_INFO + userProfileViewModel.getState().getCreatedAt());
    }

    /**
     * Handles property change events and updates the UI accordingly.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SwingUtilities.invokeLater(() -> {
            if ("state".equals(evt.getPropertyName())) {
                userProfileController.execute();
                updateView();
            }
        });
    }

    private void addActionListeners() {
        landingPageButton.addActionListener(e -> {
            if (userProfileController != null) {
                userProfileController.goToLanding();
            }
        });
    }

    public String getViewName() {
        return viewName;
    }
}

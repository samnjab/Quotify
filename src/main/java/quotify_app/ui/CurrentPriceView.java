package quotify_app.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import quotify_app.adapters.currentprice.CurrentPriceController;
import quotify_app.adapters.currentprice.CurrentPriceState;
import quotify_app.adapters.currentprice.CurrentPriceViewModel;

/**
 * The View for displaying the current price.
 * Constructs UI components and handles user interactions.
 */
public class CurrentPriceView extends JPanel implements PropertyChangeListener {
    private final String viewName;
    private CurrentPriceController currentPriceController;
    private final CurrentPriceViewModel currentPriceViewModel;

    private final JLabel currentPriceLabel = new JLabel();
    private final JButton futureButton = new JButton(CurrentPriceViewModel.FUTURE_BUTTON_LABEL);
    private final JButton comparePropertyButton = new JButton(CurrentPriceViewModel.COMPARE_PROPERTY_BUTTON_LABEL);
    private final JButton loginButton = new JButton(CurrentPriceViewModel.LOGIN_BUTTON_LABEL);
    private final JButton signupButton = new JButton(CurrentPriceViewModel.SIGNUP_BUTTON_LABEL);
    private final JButton userProfileButton = new JButton(CurrentPriceViewModel.USER_PROFILE_BUTTON_LABEL);

    // Store reference to topPanel
    private final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    /**
     * Initializes the CurrentPriceView with the given ViewModel.
     *
     * @param currentPriceViewModel the ViewModel for this View.
     */
    public CurrentPriceView(CurrentPriceViewModel currentPriceViewModel) {
        this.currentPriceViewModel = currentPriceViewModel;
        this.viewName = currentPriceViewModel.getViewName();
        this.currentPriceViewModel.addPropertyChangeListener(this);

        initializeUserInterface();
    }

    /**
     * Sets the CurrentPriceController for this View.
     *
     * @param currentPriceController the Controller to be set.
     */
    public void setCurrentPriceController(CurrentPriceController currentPriceController) {
        this.currentPriceController = currentPriceController;
        // After setting the controller, check the login status
        currentPriceController.checkLoginStatus();
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initializeUserInterface() {
        // Set layout manager
        setLayout(new BorderLayout());

        // Top panel with Login and Signup buttons or User Profile button
        add(topPanel, BorderLayout.NORTH);

        // Center panel with current price and future pricing
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        currentPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(currentPriceLabel);

        final JLabel futurePricingLabel = new JLabel(CurrentPriceViewModel.FUTURE_PRICING_LABEL);
        futurePricingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(futurePricingLabel);

        futureButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(futureButton);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with Compare Property button
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(comparePropertyButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Register action listeners
        addActionListeners();

        // Initialize the view with current state
        updateView();
    }

    /**
     * Registers action listeners for the buttons.
     * Each listener calls the appropriate method on the controller.
     */
    private void addActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToLogin();
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToSignup();
                }
            }
        });

        futureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToFuturePricingGuest();
                }
            }
        });

        comparePropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToComparatorGuest();
                }
            }
        });

        userProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToUserProfile();
                }
            }
        });
    }

    /**
     * Updates the view based on the current state in the ViewModel.
     */
    private void updateView() {
        final CurrentPriceState state = currentPriceViewModel.getState();
        currentPriceLabel.setText(CurrentPriceViewModel.TITLE_LABEL + state.getCurrentPrice());

        // Update topPanel based on login status
        topPanel.removeAll();
        if (state.isLoggedIn()) {
            // Show User Profile button
            topPanel.add(userProfileButton);
        }
        else {
            // Show Login and Signup buttons
            topPanel.add(loginButton);
            topPanel.add(signupButton);
        }
        topPanel.revalidate();
        topPanel.repaint();
    }

    /**
     * Gets the name of this view.
     *
     * @return a String representing the name of the view.
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Called when the ViewModel's state changes.
     * Updates the view to reflect the new state.
     *
     * @param evt the PropertyChangeEvent fired by the ViewModel.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateView();
    }
}

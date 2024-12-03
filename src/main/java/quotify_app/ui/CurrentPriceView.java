package quotify_app.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private final JButton showCurrentPriceButton = new JButton(CurrentPriceViewModel.SHOW_CURRENT_PRICE_BUTTON_LABEL);
    private final JButton futureButton = new JButton(CurrentPriceViewModel.FUTURE_BUTTON_LABEL);
    private final JButton comparePropertyButton = new JButton(CurrentPriceViewModel.COMPARE_PROPERTY_BUTTON_LABEL);
    private final JButton landingPageButton = new JButton(CurrentPriceViewModel.LANDING_PAGE_BUTTON_LABEL);
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
        // Removed fetchCurrentPrice() call to avoid calling it before property is set
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

        // Current Price Label
        currentPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(currentPriceLabel);

        final JLabel futurePricingLabel = new JLabel(CurrentPriceViewModel.FUTURE_PRICING_LABEL);
        futurePricingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(futurePricingLabel);

        add(centerPanel, BorderLayout.CENTER);

        showCurrentPriceButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bottom panel with Compare Property button
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(comparePropertyButton);
        bottomPanel.add(showCurrentPriceButton);
        bottomPanel.add(futureButton);
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
        landingPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToLandingPage();
                }
            }
        });

        futureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToFuturePricing();
                }
            }
        });

        comparePropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.goToComparator();
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

        showCurrentPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPriceController != null) {
                    currentPriceController.fetchCurrentPrice();
                }
            }
        });
    }

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
            // Show Landing Page button
            topPanel.add(landingPageButton);
        }
        topPanel.revalidate();
        topPanel.repaint();

        // Hide the Show Current Price button after the price has been fetched
        if (state.isPriceFetched()) {
            showCurrentPriceButton.setVisible(false);
        }
        else {
            showCurrentPriceButton.setVisible(true);
        }
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

    /**
     * Gets the Landing Page button (useful for testing or further customization).
     *
     * @return the landing page JButton.
     */
    public JButton getLandingPageButton() {
        return landingPageButton;
    }
}

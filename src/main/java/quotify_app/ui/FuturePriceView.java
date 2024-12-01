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

import quotify_app.adapters.future_price.FuturePriceController;
import quotify_app.adapters.future_price.FuturePriceState;
import quotify_app.adapters.future_price.FuturePriceViewModel;

/**
 * The View for displaying the future price.
 * Constructs UI components and handles user interactions.
 */
public class FuturePriceView extends JPanel implements PropertyChangeListener {
    private final String viewName;
    private FuturePriceController futurePriceController;
    private final FuturePriceViewModel futurePriceViewModel;

    private final JLabel futurePriceTitle = new JLabel();
    private final JLabel futurePriceGraph = new JLabel();

    private final JButton currentButton = new JButton(FuturePriceViewModel.CURRENT_PRICING_BUTTON_LABEL);
    private final JButton comparePropertyButton = new JButton(FuturePriceViewModel.COMPARE_PROPERTY_BUTTON_LABEL);
    private final JButton landingPageButton = new JButton(FuturePriceViewModel.LANDING_PAGE_BUTTOM_LABEL);
    private final JButton userProfileButton = new JButton(FuturePriceViewModel.USER_PROFILE_BUTTON_LABEL);

    // Store reference to topPanel
    private final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    /**
     * Initializes the FuturePriceView with the given ViewModel.
     *
     * @param futurePriceViewModel the ViewModel for this View.
     */
    public FuturePriceView(FuturePriceViewModel futurePriceViewModel) {
        this.futurePriceViewModel = futurePriceViewModel;
        this.viewName = futurePriceViewModel.getViewName();
        this.futurePriceViewModel.addPropertyChangeListener(this);

        initializeUserInterface();
    }

    /**
     * Sets the FuturePriceController for this View.
     *
     * @param futurePriceController the Controller to be set.
     */
    public void setFuturePriceController(FuturePriceController futurePriceController) {
        this.futurePriceController = futurePriceController;
        // After setting the controller, check the login status
        futurePriceController.checkLoginStatus();
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

        futurePriceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        futurePriceGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(futurePriceTitle);
        centerPanel.add(futurePriceGraph);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with Compare Property button
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(comparePropertyButton);
        bottomPanel.add(currentButton);
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
                if (futurePriceController != null) {
                    futurePriceController.goToLandingPage();
                }
            }
        });

        currentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (futurePriceController != null) {
                    futurePriceController.goToCurrentPricing();
                }
            }
        });

        comparePropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (futurePriceController != null) {
                    futurePriceController.goToComparator();
                }
            }
        });

        userProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (futurePriceController != null) {
                    futurePriceController.goToUserProfile();
                }
            }
        });
    }

    /**
     * Updates the view based on the current state in the ViewModel.
     */
    private void updateView() {
        final FuturePriceState state = futurePriceViewModel.getState();
        futurePriceTitle.setText(FuturePriceViewModel.TITLE_LABEL);
        futurePriceGraph.setText("graph go here");

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

    public JButton getLandingPageButton() {
        return landingPageButton;
    }
}

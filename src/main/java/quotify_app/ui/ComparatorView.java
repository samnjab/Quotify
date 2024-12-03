package quotify_app.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import quotify_app.adapters.comparator.ComparatorController;
import quotify_app.adapters.comparator.ComparatorState;
import quotify_app.adapters.comparator.ComparatorViewModel;

/**
 * The view for comparing properties, allows user or guest to compare properties and estimate current price.
 */
public class ComparatorView extends JPanel implements PropertyChangeListener {
    private static final int CENTER_PANEL_ROWS = 3;
    private static final int CENTER_PANEL_COLUMNS = 1;
    private static final int CENTER_PANEL_GAP = 10;
    private static final float TITLE_FONT_SIZE = 20f;

    private final String viewName;
    private ComparatorController comparatorController;
    private final ComparatorViewModel comparatorViewModel;

    private final JLabel property1Label = new JLabel(ComparatorViewModel.HOUSE1_LABEL);
    private final JLabel property2Label = new JLabel(ComparatorViewModel.HOUSE2_LABEL);
    private final JLabel property3Label = new JLabel(ComparatorViewModel.HOUSE3_LABEL);
    private final JButton generatePriceButton = new JButton(ComparatorViewModel.ESTIMATE_PRICE_BUTTON_LABEL);
    private final JButton newHouseButton = new JButton(ComparatorViewModel.NEW_HOUSE_BUTTON_LABEL);
    private final JButton userProfileButton = new JButton(ComparatorViewModel.USER_PROFILE_BUTTON_LABEL);
    private final JButton comapreButton = new JButton(ComparatorViewModel.COMPARE_BUTTON_LABEL);

    // Top panel to store title label and user profile button
    private final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Error panel to display error message when comparables can't be retrieved
    private JPanel errorPanel;

    // Wrapper panel for the error and bottom panels
    private final JPanel bottomWrapperPanel = new JPanel(new BorderLayout());

    /**
     * Initializes the ComparatorView with the given ViewModel.
     *
     * @param comparatorViewModel the ViewModel for this View.
     */
    public ComparatorView(ComparatorViewModel comparatorViewModel) {
        this.comparatorViewModel = comparatorViewModel;
        this.viewName = comparatorViewModel.getViewName();
        this.comparatorViewModel.addPropertyChangeListener(this);

        initializeUserInterface();
    }

    /**
     * Sets the ComparatorController for this View.
     *
     * @param comparatorController the Controller to be set.
     */
    public void setComparatorController(ComparatorController comparatorController) {
        this.comparatorController = comparatorController;
        // After setting the controller, check the login status
        comparatorController.checkLoginStatus();
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initializeUserInterface() {
        // Set layout manager
        setLayout(new BorderLayout());

        // Top panel with title and user profile button
        final JLabel titleLabel = new JLabel(ComparatorViewModel.TITLE_LABEL);
        titleLabel.setFont(titleLabel.getFont().deriveFont(TITLE_FONT_SIZE));
        topPanel.add(titleLabel);
        topPanel.add(userProfileButton);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with house input fields
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(
                CENTER_PANEL_ROWS, CENTER_PANEL_COLUMNS, CENTER_PANEL_GAP, CENTER_PANEL_GAP));

        property1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(property1Label);

        property2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(property2Label);

        property3Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(property3Label);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with buttons
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(generatePriceButton);
        bottomPanel.add(newHouseButton);
        bottomPanel.add(comapreButton);

        // Error panel initially hidden
        errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Red BG for error panel
        errorPanel.setBackground(java.awt.Color.RED);
        final JLabel errorLabel = new JLabel("Error message placeholder");
        errorLabel.setForeground(java.awt.Color.WHITE);
        errorPanel.add(errorLabel);

        // panel is initially hiden
        errorPanel.setVisible(false);

        // add(bottomPanel, BorderLayout.SOUTH);
        // Wrap error and bottom panels
        bottomWrapperPanel.add(errorPanel, BorderLayout.NORTH);
        bottomWrapperPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(bottomWrapperPanel, BorderLayout.SOUTH);

        // Register action listeners
        addActionListeners();
    }

    /**
     * Shows the error panel with the given message.
     *
     * @param errorMessage The error message to display.
     */
    private void showErrorPanel(String errorMessage) {
        // Update error message
        ((JLabel) errorPanel.getComponent(0)).setText(errorMessage);
        // Show error panel
        errorPanel.setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Hides the error panel.
     */
    private void hideErrorPanel() {
        // Hide error panel
        errorPanel.setVisible(false);
        revalidate();
        repaint();
    }

    /**
     * Registers action listeners for the buttons.
     */
    private void addActionListeners() {
        generatePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comparatorController != null) {
                    comparatorController.goToCurrentPrice();
                }
            }
        });

        newHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comparatorController != null) {
                    comparatorController.goToInput();
                }
            }
        });

        userProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comparatorController != null) {
                    comparatorController.goToUserProfile();
                }
            }
        });

        comapreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comparatorController != null) {
                    comparatorController.getComparables();
                }
            }
        });
    }

    /**
     * Gets the name of this view.
     *
     * @return a String representing the name of the view.
     */
    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ComparatorState state = comparatorViewModel.getState();
        property1Label.setText(state.getProperty(0));
        property2Label.setText(state.getProperty(1));
        property3Label.setText(state.getProperty(2));

        // Update topPanel based on login status
        topPanel.removeAll();
        topPanel.remove(userProfileButton);
        if (state.isLoggedIn()) {
            // Show User Profile button
            topPanel.add(userProfileButton);
        }
        topPanel.revalidate();
        topPanel.repaint();

        // Show or hide the error panel based on isCompareFailed
        if (state.isCompareFailed()) {
            showErrorPanel("Comparison failed: Please try again.");
        }
        else {
            hideErrorPanel();
        }
    }

}

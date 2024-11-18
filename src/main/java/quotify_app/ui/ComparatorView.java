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

    private final JLabel house1Label = new JLabel(ComparatorViewModel.HOUSE1_LABEL);
    private final JLabel house2Label = new JLabel(ComparatorViewModel.HOUSE2_LABEL);
    private final JLabel house3Label = new JLabel(ComparatorViewModel.HOUSE3_LABEL);
    private final JButton generatePriceButton = new JButton(ComparatorViewModel.ESTIMATE_PRICE_BUTTON_LABEL);
    private final JButton newHouseButton = new JButton(ComparatorViewModel.NEW_HOUSE_BUTTON_LABEL);
    private final JButton userProfileButton = new JButton(ComparatorViewModel.USER_PROFILE_BUTTON_LABEL);

    // Top panel to store title label and user profile button
    private final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

        house1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(house1Label);

        house2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(house2Label);

        house3Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(house3Label);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with buttons
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(generatePriceButton);
        bottomPanel.add(newHouseButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Register action listeners
        addActionListeners();
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
        house1Label.setText(state.getList1());
        house2Label.setText(state.getList2());
        house3Label.setText(state.getList3());

        // Update topPanel based on login status
        topPanel.removeAll();
        topPanel.remove(userProfileButton);
        if (state.isLoggedIn()) {
            // Show User Profile button
            topPanel.add(userProfileButton);
        }
        topPanel.revalidate();
        topPanel.repaint();
    }

}

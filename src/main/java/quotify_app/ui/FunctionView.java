package quotify_app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import quotify_app.adapters.function.FunctionController;
import quotify_app.adapters.function.FunctionViewModel;

/**
 * The view for user or guest to choose want function they want to proceed.
 */
public class FunctionView extends JPanel {
    private static final int CENTER_PANEL_ROWS = 1;
    private static final int CENTER_PANEL_COLUMNS = 2;
    private static final int CENTER_PANEL_GAP = 20;
    private static final float TITLE_FONT_SIZE = 20f;

    private final String viewName;
    private FunctionController functionController;
    private final FunctionViewModel functionViewModel;

    private final JButton goToCurrentPriceButton = new JButton(FunctionViewModel.ESTIMATE_PRICE_LABEL);
    private final JButton goToComparatorButton = new JButton(FunctionViewModel.COMPARE_LABEL);

    // Top panel to store title label
    private final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * Initializes the FunctionView with the given ViewModel.
     *
     * @param functionViewModel the ViewModel for this View.
     */
    public FunctionView(FunctionViewModel functionViewModel) {
        this.functionViewModel = functionViewModel;
        this.viewName = functionViewModel.getViewName();

        initializeUserInterface();
    }

    /**
     * Sets the FunctionController for this View.
     *
     * @param functionController the Controller to be set.
     */
    public void setFunctionController(FunctionController functionController) {
        this.functionController = functionController;
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initializeUserInterface() {
        // Set layout manager
        setLayout(new BorderLayout());

        // Top panel with title
        final JLabel titleLabel = new JLabel("Choose!");
        titleLabel.setFont(titleLabel.getFont().deriveFont(TITLE_FONT_SIZE));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with buttons
        final JPanel centerPanel = new JPanel(
                new GridLayout(CENTER_PANEL_ROWS, CENTER_PANEL_COLUMNS, CENTER_PANEL_GAP, CENTER_PANEL_GAP));
        centerPanel.add(goToCurrentPriceButton);
        centerPanel.add(goToComparatorButton);
        add(centerPanel, BorderLayout.CENTER);

        // Register action listeners
        addActionListeners();
    }

    /**
     * Registers action listeners for the buttons.
     */
    private void addActionListeners() {
        goToCurrentPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (functionController != null) {
                    functionController.goToCurrentPrice();
                }
            }
        });

        goToComparatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (functionController != null) {
                    functionController.goToComparator();
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

}

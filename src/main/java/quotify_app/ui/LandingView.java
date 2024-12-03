package quotify_app.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import quotify_app.adapters.landing.LandingController;
import quotify_app.adapters.landing.LandingViewModel;
import quotify_app.usecases.landing.AreaDataTransferObj;

/**
 * Represents the Landing Page UI. Displays the address input form and dynamically updates
 * UI components based on the state in the LandingViewModel.
 */
public class LandingView extends JPanel implements PropertyChangeListener {
    private final String viewName = "landing";
    private LandingController landingController;
    private final LandingViewModel landingViewModel;

    // UI Components
    // UserPanel Components:
    private final JPanel userPanel = new JPanel();
    private final JButton loginButton = new JButton("Login");
    private final JButton signUpButton = new JButton("Sign Up");
    private final JButton userProfileButton = new JButton("User Profile");
    // Address Form Panel Components:
    private JPanel addressPanel = new JPanel();
    private JComboBox<AreaDataTransferObj> countryDropdown;
    private JComboBox<AreaDataTransferObj> stateDropdown;
    private JComboBox<AreaDataTransferObj> cityDropdown;
    private JComboBox<AreaDataTransferObj> zipCodeDropdown;
    private JTextField streetAddressField = new JTextField();
    private JButton findPropertyButton = new JButton("Find Property");
    // Property Details Panel:
    private PropertyDetailsPanel propPanel;
    private JButton propConfirm = new JButton("Confirm Property");
    // Error Message:
    private JLabel errorMessageLabel = new JLabel("", SwingConstants.CENTER);

    /**
     * Constructs a new LandingView and initializes UI components.
     * @param landingViewModel The ViewModel used to manage the state of the Landing Page.
     */
    public LandingView(LandingViewModel landingViewModel) {
        this.landingViewModel = landingViewModel;
        this.landingViewModel.addPropertyChangeListener(this);

        initializeUI();
        // Ensure updateView is called after initialization
        updateView();
    }

    /**
     * Initializes the LandingController.
     * @param landingController The controller managing Landing use cases.
     */
    public void setLandingController(LandingController landingController) {
        this.landingController = landingController;
        // After setting the controller, check the login status
        landingController.checkLoginStatus();
    }

    private void updateView() {
        userPanel.removeAll();
        final boolean isLoggedIn = landingViewModel.getState().isLoggedIn();
        if (isLoggedIn) {
            userPanel.add(userProfileButton);
        }
        else {
            userPanel.add(loginButton);
            userPanel.add(signUpButton);
        }
        userPanel.revalidate();
        userPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * Initializes the UI components for the Landing Page.
     */
    private void initializeUI() {
        // Adding User Panel:
        add(createUserPanel());
        // Adding Address Form Panel:
        add(createAddressInputPanel());
        // Added error message
        errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(errorMessageLabel);
    }

    /**
     * Creates the user panel with login and sign-up buttons.
     * @return a JPanel containing user actions.
     */
    private JPanel createUserPanel() {
        userPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        loginButton.addActionListener(evt -> landingController.goToLogin());
        userPanel.add(loginButton);

        signUpButton.addActionListener(evt -> landingController.goToSignup());
        userPanel.add(signUpButton);

        userProfileButton.addActionListener(evt -> landingController.goToUserProfile());

        return userPanel;
    }

    /**
     * Creates the address input panel with dropdowns and input fields.
     * @return a JPanel containing address inputs.
     */
    private JPanel createAddressInputPanel() {
        addressPanel.setLayout(new GridLayout(6, 2, 10, 10));
        addressPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        countryDropdown = createDropdown("Select Country:", addressPanel,
                this::handleCountrySelection,
                this::handleFetchCountries);
        stateDropdown = createDropdown("Select State:", addressPanel,
                this::handleStateSelection,
                () -> handleFetchAreas(landingViewModel.getState().getSelectedCountry()));
        cityDropdown = createDropdown("Select City:", addressPanel,
                this::handleCitySelection,
                () -> handleFetchAreas(landingViewModel.getState().getSelectedState()));
        zipCodeDropdown = createDropdown("Select Zip Code:", addressPanel,
                this::handleZipCodeSelection,
                () -> handleFetchAreas(landingViewModel.getState().getSelectedCity()));

        addressPanel.add(new JLabel("Enter Street Address:"));
        streetAddressField = new JTextField();
        addressPanel.add(streetAddressField);

        findPropertyButton = new JButton("Find Property");
        findPropertyButton.addActionListener(evt -> handleFindProperty());
        addressPanel.add(findPropertyButton);

        return addressPanel;
    }

    /**
     * Creates a dropdown with a label and adds it to the panel.
     * Ensures proper handling of selection and popup visibility actions.
     *
     * @param label The label for the dropdown.
     * @param panel The panel to which the dropdown is added.
     * @param selectionAction The action to perform on selection.
     * @param popupAction The action to perform when the dropdown menu becomes visible.
     * @return the created JComboBox.
     */
    private JComboBox<AreaDataTransferObj> createDropdown(String label, JPanel panel,
                                                          Runnable selectionAction,
                                                          Runnable popupAction) {
        panel.add(new JLabel(label));
        final JComboBox<AreaDataTransferObj> dropdown = new JComboBox<>();

        // PopupMenuListener to handle menu visibility actions
        dropdown.addPopupMenuListener(createPopupMenuListener(() -> {
            if (popupAction != null) {
                popupAction.run();
            }
        }));

        // ActionListener to handle valid selections
        dropdown.addActionListener(evt -> {
            if (dropdown.getSelectedIndex() != -1) {
                selectionAction.run();
            }
        });

        panel.add(dropdown);
        return dropdown;
    }

    /**
     * Updates dropdown options dynamically.
     * @param dropdown The JComboBox to update.
     * @param options  The list of options to populate.
     */
    private void updateDropdownOptions(JComboBox<AreaDataTransferObj> dropdown, List<AreaDataTransferObj> options) {
        dropdown.removeAllItems();
        if (options != null) {
            for (AreaDataTransferObj area : options) {
                dropdown.addItem(area);
            }
        }
    }

    /**
     * Creates a PopupMenuListener with a custom action for popupMenuWillBecomeVisible.
     * @param onVisibleAction The action to execute when the popup menu becomes visible.
     * @return An instance of PopupMenuListener with the provided visible action.
     */
    private PopupMenuListener createPopupMenuListener(Runnable onVisibleAction) {
        return new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if (onVisibleAction != null) {
                    onVisibleAction.run();
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // No action
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // No action
            }
        };
    }

    /**
     * Handles country selection from the dropdown.
     */
    private void handleCountrySelection() {
        final AreaDataTransferObj country = (AreaDataTransferObj) countryDropdown.getSelectedItem();
        if (country != null) {
            landingController.selectArea(country);
        }
    }

    /**
     * Handles state selection from the dropdown.
     */
    private void handleStateSelection() {
        final AreaDataTransferObj state = (AreaDataTransferObj) stateDropdown.getSelectedItem();
        if (state != null) {
            landingController.selectArea(state);
        }
    }

    /**
     * Handles city selection from the dropdown.
     */
    private void handleCitySelection() {
        final AreaDataTransferObj city = (AreaDataTransferObj) cityDropdown.getSelectedItem();
        if (city != null) {
            landingController.selectArea(city);
        }
    }

    /**
     * Handles zip code selection from the dropdown.
     */
    private void handleZipCodeSelection() {
        final AreaDataTransferObj zipCode = (AreaDataTransferObj) zipCodeDropdown.getSelectedItem();
        if (zipCode != null) {
            landingController.selectArea(zipCode);
        }
    }

    /**
     * Handles the "Find Property" button click.
     */
    private void handleFindProperty() {
        // Retrieve the entered street address
        landingViewModel.getState().setStreetAddress(streetAddressField.getText());
        final AreaDataTransferObj selectedCountry = landingViewModel.getState().getSelectedCountry();
        final AreaDataTransferObj selectedState = landingViewModel.getState().getSelectedState();
        final AreaDataTransferObj selectedCity = landingViewModel.getState().getSelectedCity();
        final AreaDataTransferObj selectedZipCode = landingViewModel.getState().getSelectedZipCode();
        final String streetAddress = landingViewModel.getState().getStreetAddress();
        // Check that all required inputs are provided
        if (selectedCountry == null || selectedState == null || selectedCity == null || selectedZipCode == null || streetAddress.isEmpty()) {
            errorMessageLabel.setText("Please complete all fields before finding the property.");
            return;
        }
        // Call the controller method with the selected data
        landingController.selectAddress(selectedCountry, selectedState, selectedCity, selectedZipCode, streetAddress);
    }

    /**
     * Listens for property changes in the LandingViewModel and updates the UI.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "isLoggedIn":
                updateView();
                break;
            case "selectedCountry":
                final AreaDataTransferObj country = landingViewModel.getState().getSelectedCountry();
                if (country != null) {
                    countryDropdown.setSelectedItem(country);
                }
                break;
            case "selectedState":
                // Set the selected item in the state dropdown
                final AreaDataTransferObj state = landingViewModel.getState().getSelectedState();
                if (state != null) {
                    stateDropdown.setSelectedItem(state);
                }
                break;
            case "selectedCity":
                // Set the selected item in the city dropdown
                final AreaDataTransferObj city = landingViewModel.getState().getSelectedCity();
                if (city != null) {
                    cityDropdown.setSelectedItem(city);
                }
                break;
            case "selectedZipCode":
                // Set the selected item in the zip code dropdown
                final AreaDataTransferObj zipCode = landingViewModel.getState().getSelectedZipCode();
                if (zipCode != null) {
                    zipCodeDropdown.setSelectedItem(zipCode);
                }
                break;
            case "availableCountries":
                updateDropdownOptions(countryDropdown, landingViewModel.getState().getAvailableCountries());
                break;
            case "availableStates":
                updateDropdownOptions(stateDropdown, landingViewModel.getState().getAvailableStates());
                break;
            case "availableCities":
                updateDropdownOptions(cityDropdown, landingViewModel.getState().getAvailableCities());
                break;
            case "availableZipCodes":
                updateDropdownOptions(zipCodeDropdown, landingViewModel.getState().getAvailableZipCodes());
                break;
            case "errorMessage":
                errorMessageLabel.setText(landingViewModel.getState().getErrorMessage());
                break;
            case "propertyFound":
                // Create and display the PropertyDetailsPanel
                displayPropertyDetailsPanel();
                break;
            default:
                break;
        }
    }

    /**
     * Fetches sub-areas for the selected parent area.
     */
    private void handleFetchCountries() {
        landingController.fetchCountries();
    }

    /**
     * Fetches sub-areas for the selected parent area.
     *
     * @param parentArea The parent area whose sub-areas are to be fetched.
     */
    private void handleFetchAreas(AreaDataTransferObj parentArea) {
        if (parentArea != null) {
            landingController.fetchAvailableSubAreas(parentArea);
        }
    }

    /**
     * Updates the PropertyDetailsPanel dynamically.
     */
    private void displayPropertyDetailsPanel() {
        final String propertyAddress = landingViewModel.getState().getPropertyAddress();
        final Map<String, String> propertyDetails = landingViewModel.getState().getPropertyDetails();

        if (propertyAddress != null && propertyDetails != null) {
            if (propPanel != null) {
                remove(propPanel);
            }
            propPanel = new PropertyDetailsPanel(propertyAddress, propertyDetails);
            add(propPanel, BorderLayout.CENTER);

            // removing add action listeners on propConfirm
            for (ActionListener al : propConfirm.getActionListeners()) {
                propConfirm.removeActionListener(al);
            }

            // adding action listener to confirm button:
            propConfirm.addActionListener(evt -> navigateToNextPage());
            add(propConfirm, BorderLayout.SOUTH);

            revalidate();
            repaint();
        }
        else {
            errorMessageLabel.setText("No property details available.");
        }
    }

    /**
     * Navigates to the next page.
     */
    public void navigateToNextPage() {
        landingController.goToNextPage();
    }

}

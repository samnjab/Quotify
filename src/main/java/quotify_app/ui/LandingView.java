package quotify_app.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import quotify_app.adapters.landing.LandingViewModel;
import quotify_app.entities.regionEntities.Area;

/**
 * Represents the Landing Page UI. Displays the address input form and dynamically updates
 * UI components based on the state in the LandingViewModel.
 */
public class LandingView extends JPanel implements PropertyChangeListener {

    private final LandingViewModel landingViewModel;

    // UI Components
    private JComboBox<Area> countryDropdown;
    private JComboBox<Area> stateDropdown;
    private JComboBox<Area> cityDropdown;
    private JComboBox<Area> zipCodeDropdown;
    private JTextField streetAddressField;
    private JButton findPropertyButton;
    private JLabel errorMessageLabel;

    /**
     * Constructs a new LandingView and initializes UI components.
     *
     * @param landingViewModel The ViewModel used to manage the state of the Landing Page.
     */
    public LandingView(LandingViewModel landingViewModel) {
        this.landingViewModel = landingViewModel;
        this.landingViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        initializeUI();
    }

    /**
     * Initializes the UI components for the Landing Page.
     */
    private void initializeUI() {
        final JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Country Dropdown
        formPanel.add(new JLabel("Select Country:"));
        countryDropdown = new JComboBox<>();
        countryDropdown.addActionListener(evt -> handleCountrySelection());
        formPanel.add(countryDropdown);

        // State Dropdown
        formPanel.add(new JLabel("Select State:"));
        stateDropdown = new JComboBox<>();
        stateDropdown.addActionListener(evt -> handleStateSelection());
        formPanel.add(stateDropdown);

        // City Dropdown
        formPanel.add(new JLabel("Select City:"));
        cityDropdown = new JComboBox<>();
        cityDropdown.addActionListener(evt -> handleCitySelection());
        formPanel.add(cityDropdown);

        // Zip Code Dropdown
        formPanel.add(new JLabel("Select Zip Code:"));
        zipCodeDropdown = new JComboBox<>();
        zipCodeDropdown.addActionListener(evt -> handleZipCodeSelection());
        formPanel.add(zipCodeDropdown);

        // Street Address Field
        formPanel.add(new JLabel("Enter Street Address:"));
        streetAddressField = new JTextField();
        formPanel.add(streetAddressField);

        // Find Property Button
        findPropertyButton = new JButton("Find Property");
        findPropertyButton.addActionListener(evt -> handleFindProperty());
        formPanel.add(findPropertyButton);

        // Error Message Label
        errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        errorMessageLabel.setForeground(Color.RED);

        add(formPanel, BorderLayout.CENTER);
        add(errorMessageLabel, BorderLayout.SOUTH);
    }

    /**
     * Handles country selection from the dropdown.
     */
    private void handleCountrySelection() {
        final Area selectedCountry = (Area) countryDropdown.getSelectedItem();
        if (selectedCountry != null) {
            landingViewModel.setSelectedCountry(selectedCountry);
        }
    }

    /**
     * Handles state selection from the dropdown.
     */
    private void handleStateSelection() {
        final Area selectedState = (Area) stateDropdown.getSelectedItem();
        if (selectedState != null) {
            landingViewModel.setSelectedState(selectedState);
        }
    }

    /**
     * Handles city selection from the dropdown.
     */
    private void handleCitySelection() {
        final Area selectedCity = (Area) cityDropdown.getSelectedItem();
        if (selectedCity != null) {
            landingViewModel.setSelectedCity(selectedCity);
        }
    }

    /**
     * Handles zip code selection from the dropdown.
     */
    private void handleZipCodeSelection() {
        final Area selectedZipCode = (Area) zipCodeDropdown.getSelectedItem();
        if (selectedZipCode != null) {
            landingViewModel.setSelectedZipCode(selectedZipCode.getName());
        }
    }

    /**
     * Handles the "Find Property" button click.
     */
    private void handleFindProperty() {
        final String streetAddress = streetAddressField.getText();
        landingViewModel.setStreetAddress(streetAddress);

        // Assuming the interactor is triggered here to find the property
    }

    /**
     * Updates the dropdown options dynamically.
     * @param dropdown The JComboBox to update.
     * @param options  The list of options to populate.
     */
    private void updateDropdownOptions(JComboBox<Area> dropdown, List<Area> options) {
        dropdown.removeAllItems();
        if (options != null) {
            for (Area area : options) {
                dropdown.addItem(area);
            }
        }
    }

    /**
     * Listens for property changes in the LandingViewModel and updates the UI.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
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
            default:
                break;
        }
    }
}

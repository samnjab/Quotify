package quotify_app.ui;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import quotify_app.adapters.landing.LandingController;
import quotify_app.adapters.landing.LandingViewModel;
import quotify_app.entities.regionEntities.Area;

/**
 * Represents the Landing Page UI. Displays the address input form and dynamically updates
 * UI components based on the state in the LandingViewModel.
 */
public class LandingView extends JPanel implements PropertyChangeListener {
    private final String viewName = "landing";
    private LandingController landingController;
    private final LandingViewModel landingViewModel;

    // UI Components
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
    private JComboBox<Area> countryDropdown = new JComboBox<>();
    private JComboBox<Area> stateDropdown = new JComboBox<>();
    private JComboBox<Area> cityDropdown = new JComboBox<>();
    private JComboBox<Area> zipCodeDropdown = new JComboBox<>();
    private JTextField streetAddressField = new JTextField();
    private JButton findPropertyButton = new JButton("Find Property");
    private JLabel errorMessageLabel = new JLabel("", SwingConstants.CENTER);
    private PropertyDetailsPanel propPanel;
    private JButton propConfirm = new JButton("Confirm Property");

    // Area Selections
    private Area selectedCountry;
    private Area selectedState;
    private Area selectedCity;
    private Area selectedZipCode;

    /**
     * Constructs a new LandingView and initializes UI components.
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
        // Create a main panel with BoxLayout to stack components vertically
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // User Panel:
        final JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Login and SignIn Buttons
        loginButton.addActionListener(evt -> handleLogin());
        userPanel.add(loginButton, BorderLayout.WEST);

        signUpButton.addActionListener(evt -> handleSignUp());
        userPanel.add(signUpButton, BorderLayout.EAST);

        // Form Panel
        final JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Country Dropdown
        formPanel.add(new JLabel("Select Country:"));
        countryDropdown.addActionListener(evt -> handleCountrySelection());
        countryDropdown.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                handleFetchCountries();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.println("Dropdown canceled.");
            }
        });
        formPanel.add(countryDropdown);

        // State Dropdown
        formPanel.add(new JLabel("Select State:"));
        stateDropdown.addActionListener(evt -> handleStateSelection());
        stateDropdown.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                System.out.println("Dropdown opened to view items.");
                handleFetchAreas(selectedCountry);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                System.out.println("Dropdown closed.");
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.println("Dropdown canceled.");
            }
        });
        formPanel.add(stateDropdown);

        // City Dropdown
        formPanel.add(new JLabel("Select City:"));
        cityDropdown.addActionListener(evt -> handleCitySelection());
        cityDropdown.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                System.out.println("Dropdown opened to view items.");
                handleFetchAreas(selectedState);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                System.out.println("Dropdown closed.");
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.println("Dropdown canceled.");
            }
        });
        formPanel.add(cityDropdown);

        // Zip Code Dropdown
        formPanel.add(new JLabel("Select Zip Code:"));
        zipCodeDropdown.addActionListener(evt -> handleZipCodeSelection());
        zipCodeDropdown.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                System.out.println("Dropdown opened to view items.");
                handleFetchAreas(selectedCity);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                System.out.println("Dropdown closed.");
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.println("Dropdown canceled.");
            }
        });
        formPanel.add(zipCodeDropdown);

        // Street Address Field
        formPanel.add(new JLabel("Enter Street Address:"));
        streetAddressField = new JTextField();
        formPanel.add(streetAddressField);

        // Find Property Button
        findPropertyButton.addActionListener(evt -> handleFindProperty());
        formPanel.add(findPropertyButton);

        // Error Message Label
        errorMessageLabel.setForeground(Color.RED);

        // Appending to main panel:
        add(userPanel);
        add(formPanel);
        add(errorMessageLabel);

    }

    private void handleLogin() {
        landingController.goToLogin();
    }

    private void handleSignUp() {
        landingController.goToSignup();
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
    private void handleFetchAreas(Area parentArea) {
        if (parentArea != null) {
            landingController.fetchAvailableSubAreas(parentArea);
        }
    }

    /**
     * Handles country selection from the dropdown.
     */
    private void handleCountrySelection() {
        final Area selectedCountry = (Area) countryDropdown.getSelectedItem();
        if (selectedCountry != null) {
            landingController.selectArea(selectedCountry);
            this.selectedCountry = selectedCountry;
        }
    }

    /**
     * Handles state selection from the dropdown.
     */
    private void handleStateSelection() {
        final Area selectedState = (Area) stateDropdown.getSelectedItem();
        if (selectedState != null) {
            landingController.selectArea(selectedState);
            this.selectedState = selectedState;
        }
    }

    /**
     * Handles city selection from the dropdown.
     */
    private void handleCitySelection() {
        final Area selectedCity = (Area) cityDropdown.getSelectedItem();
        if (selectedCity != null) {
            landingController.selectArea(selectedCity);
            this.selectedCity = selectedCity;
        }
    }

    /**
     * Handles zip code selection from the dropdown.
     */
    private void handleZipCodeSelection() {
        final Area selectedZipCode = (Area) zipCodeDropdown.getSelectedItem();
        if (selectedZipCode != null) {
            landingController.selectArea(selectedZipCode);
            this.selectedZipCode = selectedZipCode;
        }
    }

    /**
     * Handles the "Find Property" button click.
     */
    private void handleFindProperty() {
        // Retrieve the selected items from the dropdowns
        final Area selectedCountry = (Area) countryDropdown.getSelectedItem();
        final Area selectedState = (Area) stateDropdown.getSelectedItem();
        final Area selectedCity = (Area) cityDropdown.getSelectedItem();
        final Area selectedZipCode = (Area) zipCodeDropdown.getSelectedItem();

        // Retrieve the entered street address
        final String streetAddress = streetAddressField.getText();

        // Check that all required inputs are provided
        if (selectedCountry == null || selectedState == null || selectedCity == null || selectedZipCode == null || streetAddress.isEmpty()) {
            errorMessageLabel.setText("Please complete all fields before finding the property.");
            return;
        }
        // Call the controller method with the selected data
        landingController.selectAddress(selectedCountry, selectedState, selectedCity, selectedZipCode, streetAddress);

        // Create and display the PropertyDetailsPanel
        displayPropertyDetailsPanel();
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
            add(propPanel);
            // adding action listener to confirm button:
            propConfirm.addActionListener(evt -> navigateToNextPage());
            add(propConfirm);

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
     * Initializes the LandingController.
     * @param landingController The controller managing Landing use cases.
     */
    public void setLandingController(LandingController landingController) {
        this.landingController = landingController;
    }

    /**
     * Listens for property changes in the LandingViewModel and updates the UI.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "propertyConfirmed":
                navigateToNextPage();
                break;
            case "propertyFound":
                displayPropertyDetailsPanel();
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
            default:
                break;
        }
    }

    public String getViewName() {
        return viewName;
    }
}

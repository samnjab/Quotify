package quotify_app.adapters.landing;

import java.util.List;
import java.util.Map;

import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.landing.AreaDataTransferObj;

/**
 * Represents the state of the Landing Page, including the current user profile,
 * address input form selections, and dynamic dropdowns for country, state, city, and zip codes.
 */
public class LandingState {

    // User profile-related state
    private boolean isLoggedIn;
    private String currentUser;

    // Area Browser
    private List<AreaDataTransferObj> availableCountries;
    private List<AreaDataTransferObj> availableStates;
    private List<AreaDataTransferObj> availableCities;
    private List<AreaDataTransferObj> availableZipCodes;

    // Selected areas
    private AreaDataTransferObj selectedCountry;
    private AreaDataTransferObj selectedState;
    private AreaDataTransferObj selectedCity;
    private AreaDataTransferObj selectedZipCode;
    private String streetAddress;

    // Property-related state
    private boolean isPropertyFound;
    private String propertyAddress;
    private Map<String, String> propertyDetails;

    // Confirmed property state:
    private Property currentProperty;
    private boolean propertyConfirmed;

    // Error state
    private String errorMessage;

    // Getters and Setters
    // Log in status:
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // User profile status
    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    // Property found boolean status:
    public boolean isPropertyFound() {
        return isPropertyFound;
    }

    public void setPropertyFound(boolean propertyFound) {
        isPropertyFound = propertyFound;
    }

    // Property Confirmed boolean status:
    public boolean isPropertyConfirmed() {
        return propertyConfirmed;
    }

    public void setPropertyConfirmed(boolean confirmed) {
        this.propertyConfirmed = confirmed;
    }

    // Confirmed property:
    public void setCurrentProperty(Property property) {
        this.currentProperty = property;
    }

    public Property getCurrentProperty() {
        return currentProperty;
    }

    // Available Areas:

    public List<AreaDataTransferObj> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(List<AreaDataTransferObj> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public List<AreaDataTransferObj> getAvailableStates() {
        return availableStates;
    }

    public void setAvailableStates(List<AreaDataTransferObj> availableStates) {
        this.availableStates = availableStates;
    }

    public List<AreaDataTransferObj> getAvailableCities() {
        return availableCities;
    }

    public void setAvailableCities(List<AreaDataTransferObj> availableCities) {
        this.availableCities = availableCities;
    }

    public List<AreaDataTransferObj> getAvailableZipCodes() {
        return availableZipCodes;
    }

    public void setAvailableZipCodes(List<AreaDataTransferObj> availableZipCodes) {
        this.availableZipCodes = availableZipCodes;
    }

    public AreaDataTransferObj getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(AreaDataTransferObj selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public AreaDataTransferObj getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(AreaDataTransferObj selectedState) {
        this.selectedState = selectedState;
    }

    public AreaDataTransferObj getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(AreaDataTransferObj selectedCity) {
        this.selectedCity = selectedCity;
    }

    public AreaDataTransferObj getSelectedZipCode() {
        return selectedZipCode;
    }

    public void setSelectedZipCode(AreaDataTransferObj selectedZipCode) {
        this.selectedZipCode = selectedZipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // Property address and details:
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public Map<String, String> getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(Map<String, String> propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

    // Error message:
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

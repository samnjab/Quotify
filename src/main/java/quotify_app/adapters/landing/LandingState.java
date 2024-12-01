package quotify_app.adapters.landing;

import java.util.List;
import java.util.Map;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;

/**
 * Represents the state of the Landing Page, including the current user profile,
 * address input form selections, and dynamic dropdowns for country, state, city, and zip codes.
 */
public class LandingState {

    // User profile-related state
    private boolean isLoggedIn;
    private String currentUser;

    // Address input form state
    private List<Area> availableCountries;
    private List<Area> availableStates;
    private List<Area> availableCities;
    private List<Area> availableZipCodes;

    private Area selectedCountry;
    private Area selectedState;
    private Area selectedCity;
    private String selectedZipCode;
    private String streetAddress;

    // Property-related state
    private boolean isPropertyFound;
    private Address propertyAddress;
    private Map<String, String> propertyDetails;

    // Error state
    private String errorMessage;

    // Getters and Setters
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public List<Area> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(List<Area> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public List<Area> getAvailableStates() {
        return availableStates;
    }

    public void setAvailableStates(List<Area> availableStates) {
        this.availableStates = availableStates;
    }

    public List<Area> getAvailableCities() {
        return availableCities;
    }

    public void setAvailableCities(List<Area> availableCities) {
        this.availableCities = availableCities;
    }

    public List<Area> getAvailableZipCodes() {
        return availableZipCodes;
    }

    public void setAvailableZipCodes(List<Area> availableZipCodes) {
        this.availableZipCodes = availableZipCodes;
    }

    public Area getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Area selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public Area getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(Area selectedState) {
        this.selectedState = selectedState;
    }

    public Area getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(Area selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getSelectedZipCode() {
        return selectedZipCode;
    }

    public void setSelectedZipCode(String selectedZipCode) {
        this.selectedZipCode = selectedZipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public boolean isPropertyFound() {
        return isPropertyFound;
    }

    public void setPropertyFound(boolean propertyFound) {
        isPropertyFound = propertyFound;
    }

    public Address getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public Map<String, String> getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(Map<String, String> propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

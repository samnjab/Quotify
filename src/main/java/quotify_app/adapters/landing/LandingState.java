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
        System.out.println("LandingState: isLoggedIn: " + isLoggedIn);
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getCurrentUser() {
        System.out.println("LandingState: getting current user: " + currentUser);
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        System.out.println("LandingState: setting current user: " + currentUser);
        this.currentUser = currentUser;
    }

    public List<Area> getAvailableCountries() {
        System.out.println("LandingState: getting available countries: " + availableCountries);
        return availableCountries;
    }

    public void setAvailableCountries(List<Area> availableCountries) {
        System.out.println("LandingState: setting available countries: " + availableCountries);
        this.availableCountries = availableCountries;
    }

    public List<Area> getAvailableStates() {
        System.out.println("LandingState: getting available states: " + availableStates);
        return availableStates;
    }

    public void setAvailableStates(List<Area> availableStates) {
        System.out.println("LandingState: setting available states: " + availableStates);
        this.availableStates = availableStates;
    }

    public List<Area> getAvailableCities() {
        System.out.println("LandingState: getting available cities: " + availableCities);
        return availableCities;
    }

    public void setAvailableCities(List<Area> availableCities) {
        System.out.println("LandingState: setting available cities: " + availableCities);
        this.availableCities = availableCities;
    }

    public List<Area> getAvailableZipCodes() {
        System.out.println("LandingState: getting available zip codes: " + availableZipCodes);
        return availableZipCodes;
    }

    public void setAvailableZipCodes(List<Area> availableZipCodes) {
        System.out.println("LandingState: setting available zip codes: " + availableZipCodes);
        this.availableZipCodes = availableZipCodes;
    }

    public Area getSelectedCountry() {
        System.out.println("LandingState: getting selected country: " + selectedCountry);
        return selectedCountry;
    }

    public void setSelectedCountry(Area selectedCountry) {
        System.out.println("LandingState: setting selected country: " + selectedCountry);
        this.selectedCountry = selectedCountry;
    }

    public Area getSelectedState() {
        System.out.println("LandingState: getting selected state: " + selectedState);
        return selectedState;
    }

    public void setSelectedState(Area selectedState) {
        System.out.println("LandingState: setting selected state: " + selectedState);
        this.selectedState = selectedState;
    }

    public Area getSelectedCity() {
        System.out.println("LandingState: getting selected city: " + selectedCity);
        return selectedCity;
    }

    public void setSelectedCity(Area selectedCity) {
        System.out.println("LandingState: setting selected city: " + selectedCity);
        this.selectedCity = selectedCity;
    }

    public String getSelectedZipCode() {
        System.out.println("LandingState: getting selected zip code: " + selectedZipCode);
        return selectedZipCode;
    }

    public void setSelectedZipCode(String selectedZipCode) {
        System.out.println("LandingState: setting selected zip code: " + selectedZipCode);
        this.selectedZipCode = selectedZipCode;
    }

    public String getStreetAddress() {
        System.out.println("LandingState: getting street address: " + streetAddress);
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        System.out.println("LandingState: setting street address: " + streetAddress);
        this.streetAddress = streetAddress;
    }

    public boolean isPropertyFound() {
        System.out.println("LandingState: isPropertyFound: " + isPropertyFound);
        return isPropertyFound;
    }

    public void setPropertyFound(boolean propertyFound) {
        System.out.println("LandingState: setting property found: " + propertyFound);
        isPropertyFound = propertyFound;
    }

    public Address getPropertyAddress() {
        System.out.println("LandingState: getting property address: " + propertyAddress);
        return propertyAddress;
    }

    public void setPropertyAddress(Address propertyAddress) {
        System.out.println("LandingState: setting property address: " + propertyAddress);
        this.propertyAddress = propertyAddress;
    }

    public Map<String, String> getPropertyDetails() {
        System.out.println("LandingState: getting property details: " + propertyDetails);
        return propertyDetails;
    }

    public void setPropertyDetails(Map<String, String> propertyDetails) {
        System.out.println("LandingState: setting property details: " + propertyDetails);
        this.propertyDetails = propertyDetails;
    }

    public String getErrorMessage() {
        System.out.println("LandingState: getting error message: " + errorMessage);
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        System.out.println("LandingState: setting error message: " + errorMessage);
        this.errorMessage = errorMessage;
    }
}

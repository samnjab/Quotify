package quotify_app.adapters.landing;

import quotify_app.entities.regionEntities.Area;

/**
 * The state for the Landing View Model.
 */
public class LandingState {

    private Area selectedCountry;
    private Area selectedState;
    private Area selectedCity;
    private String streetAddress;
    private boolean isLoggedIn;
    private String errorMessage;

    public LandingState() {
        this.selectedCountry = null;
        this.selectedState = null;
        this.selectedCity = null;
        this.streetAddress = "";
        this.isLoggedIn = false;
        this.errorMessage = "";
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Resets the state of the landing page to its default.
     */
    public void resetState() {
        this.selectedCountry = null;
        this.selectedState = null;
        this.selectedCity = null;
        this.streetAddress = "";
        this.isLoggedIn = false;
        this.errorMessage = "";
    }
}

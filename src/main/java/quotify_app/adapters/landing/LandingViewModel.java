package quotify_app.adapters.landing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;

/**
 * The ViewModel for the Landing Page. Encapsulates the state of the page and provides
 * property change support for dynamic updates to the UI.
 */
public class LandingViewModel {

    private final PropertyChangeSupport support;
    private final LandingState state;

    /**
     * Constructs a new LandingViewModel with an initial LandingState.
     */
    public LandingViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new LandingState();
    }

    /**
     * Adds a listener for property change events.
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for property change events.
     * @param listener The listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the Landing Page.
     * @return The current LandingState.
     */
    public LandingState getState() {
        return state;
    }

    /**
     * Updates the list of available countries and notifies listeners.
     * @param availableCountries The updated list of available countries.
     */
    public void setAvailableCountries(List<Area> availableCountries) {
        System.out.println("LandingViewModel: setting available states: " + availableCountries);
        final List<Area> oldValue = state.getAvailableCountries();
        state.setAvailableCountries(availableCountries);
        support.firePropertyChange("availableCountries", oldValue, availableCountries);
    }

    /**
     * Updates the list of available states and notifies listeners.
     * @param availableStates The updated list of available states.
     */
    public void setAvailableStates(List<Area> availableStates) {
        System.out.println("LandingViewModel: setting available states: " + availableStates);
        final List<Area> oldValue = state.getAvailableStates();
        state.setAvailableStates(availableStates);
        support.firePropertyChange("availableStates", oldValue, availableStates);
    }

    /**
     * Updates the list of available cities and notifies listeners.
     * @param availableCities The updated list of available cities.
     */
    public void setAvailableCities(List<Area> availableCities) {
        System.out.println("LandingViewModel: setting available states: " + availableCities);
        final List<Area> oldValue = state.getAvailableCities();
        state.setAvailableCities(availableCities);
        support.firePropertyChange("availableCities", oldValue, availableCities);
    }

    /**
     * Updates the list of available zip codes and notifies listeners.
     * @param availableZipCodes The updated list of available zip codes.
     */
    public void setAvailableZipCodes(List<Area> availableZipCodes) {
        System.out.println("LandingViewModel: setting available states: " + availableZipCodes);
        final List<Area> oldValue = state.getAvailableZipCodes();
        state.setAvailableZipCodes(availableZipCodes);
        support.firePropertyChange("availableZipCodes", oldValue, availableZipCodes);
    }

    /**
     * Updates the selected country and notifies listeners.
     * @param selectedCountry The updated selected country.
     */
    public void setSelectedCountry(Area selectedCountry) {
        System.out.println("LandingViewModel: setting selected country: " + selectedCountry);
        final Area oldValue = state.getSelectedCountry();
        state.setSelectedCountry(selectedCountry);
        support.firePropertyChange("selectedCountry", oldValue, selectedCountry);
    }

    /**
     * Updates the selected state and notifies listeners.
     *
     * @param selectedState The updated selected state.
     */
    public void setSelectedState(Area selectedState) {
        System.out.println("LandingViewModel: setting selected state: " + selectedState);
        final Area oldValue = state.getSelectedState();
        state.setSelectedState(selectedState);
        support.firePropertyChange("selectedState", oldValue, selectedState);
    }

    /**
     * Updates the selected city and notifies listeners.
     * @param selectedCity The updated selected city.
     */
    public void setSelectedCity(Area selectedCity) {
        System.out.println("LandingViewModel: setting selected city: " + selectedCity);
        final Area oldValue = state.getSelectedCity();
        state.setSelectedCity(selectedCity);
        support.firePropertyChange("selectedCity", oldValue, selectedCity);
    }

    /**
     * Updates the selected zip code and notifies listeners.
     * @param selectedZipCode The updated selected zip code.
     */
    public void setSelectedZipCode(String selectedZipCode) {
        System.out.println("LandingViewModel: setting selected zip code: " + selectedZipCode);
        final String oldValue = state.getSelectedZipCode();
        state.setSelectedZipCode(selectedZipCode);
        support.firePropertyChange("selectedZipCode", oldValue, selectedZipCode);
    }

    /**
     * Updates the street address and notifies listeners.
     * @param streetAddress The updated street address.
     */
    public void setStreetAddress(String streetAddress) {
        System.out.println("LandingViewModel: setting street address: " + streetAddress);
        final String oldValue = state.getStreetAddress();
        state.setStreetAddress(streetAddress);
        support.firePropertyChange("streetAddress", oldValue, streetAddress);
    }

    /**
     * Updates the property address and notifies listeners.
     * @param propertyAddress The updated property address.
     */
    public void setPropertyAddress(Address propertyAddress) {
        System.out.println("LandingViewModel: setting property address: " + propertyAddress);
        final Address oldValue = state.getPropertyAddress();
        state.setPropertyAddress(propertyAddress);
        support.firePropertyChange("propertyAddress", oldValue, propertyAddress);
    }

    /**
     * Updates the error message and notifies listeners.
     * @param errorMessage The updated error message.
     */
    public void setErrorMessage(String errorMessage) {
        System.out.println("LandingViewModel: setting error message: " + errorMessage);
        final String oldValue = state.getErrorMessage();
        state.setErrorMessage(errorMessage);
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
    }

    /**
     * Updates the property found status and notifies listeners.
     * @param propertyFound The updated property found status.
     */
    public void setPropertyFound(boolean propertyFound) {
        System.out.println("LandingViewModel: setting property found: " + propertyFound);
        final boolean oldValue = state.isPropertyFound();
        state.setPropertyFound(propertyFound);
        support.firePropertyChange("propertyFound", oldValue, propertyFound);
    }
}

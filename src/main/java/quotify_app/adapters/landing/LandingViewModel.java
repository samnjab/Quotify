package quotify_app.adapters.landing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;

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
        final List<Area> oldValue = state.getAvailableCountries();
        state.setAvailableCountries(availableCountries);
        support.firePropertyChange("availableCountries", oldValue, availableCountries);
    }

    /**
     * Updates the list of available states and notifies listeners.
     * @param availableStates The updated list of available states.
     */
    public void setAvailableStates(List<Area> availableStates) {
        final List<Area> oldValue = state.getAvailableStates();
        state.setAvailableStates(availableStates);
        support.firePropertyChange("availableStates", oldValue, availableStates);
    }

    /**
     * Updates the list of available cities and notifies listeners.
     * @param availableCities The updated list of available cities.
     */
    public void setAvailableCities(List<Area> availableCities) {
        final List<Area> oldValue = state.getAvailableCities();
        state.setAvailableCities(availableCities);
        support.firePropertyChange("availableCities", oldValue, availableCities);
    }

    /**
     * Updates the list of available zip codes and notifies listeners.
     * @param availableZipCodes The updated list of available zip codes.
     */
    public void setAvailableZipCodes(List<Area> availableZipCodes) {
        final List<Area> oldValue = state.getAvailableZipCodes();
        state.setAvailableZipCodes(availableZipCodes);
        support.firePropertyChange("availableZipCodes", oldValue, availableZipCodes);
    }

    /**
     * Updates the selected country and notifies listeners.
     * @param selectedCountry The updated selected country.
     */
    public void setSelectedCountry(Area selectedCountry) {
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
        final Area oldValue = state.getSelectedState();
        state.setSelectedState(selectedState);
        support.firePropertyChange("selectedState", oldValue, selectedState);
    }

    /**
     * Updates the selected city and notifies listeners.
     * @param selectedCity The updated selected city.
     */
    public void setSelectedCity(Area selectedCity) {
        final Area oldValue = state.getSelectedCity();
        state.setSelectedCity(selectedCity);
        support.firePropertyChange("selectedCity", oldValue, selectedCity);
    }

    /**
     * Updates the selected zip code and notifies listeners.
     * @param selectedZipCode The updated selected zip code.
     */
    public void setSelectedZipCode(String selectedZipCode) {
        final String oldValue = state.getSelectedZipCode();
        state.setSelectedZipCode(selectedZipCode);
        support.firePropertyChange("selectedZipCode", oldValue, selectedZipCode);
    }

    /**
     * Updates the street address and notifies listeners.
     * @param streetAddress The updated street address.
     */
    public void setStreetAddress(String streetAddress) {
        final String oldValue = state.getStreetAddress();
        state.setStreetAddress(streetAddress);
        support.firePropertyChange("streetAddress", oldValue, streetAddress);
    }

    /**
     * Updates the error message and notifies listeners.
     * @param errorMessage The updated error message.
     */
    public void setErrorMessage(String errorMessage) {
        final String oldValue = state.getErrorMessage();
        state.setErrorMessage(errorMessage);
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
    }

    /**
     * Updates the property found status and notifies listeners.
     * @param propertyFound The updated property found status.
     */
    public void setPropertyFound(boolean propertyFound) {
        final boolean oldValue = state.isPropertyFound();
        state.setPropertyFound(propertyFound);
        support.firePropertyChange("propertyFound", oldValue, propertyFound);
    }

    /**
     * Updates the confirmed property status and notifies listeners.
     * @param propertyConfirmed The confirmed property found status.
     */
    public void setPropertyConfirmed(boolean propertyConfirmed) {
        final boolean oldValue = state.isPropertyConfirmed();
        state.setPropertyConfirmed(propertyConfirmed);
        support.firePropertyChange("propertyConfirmed", oldValue, propertyConfirmed);
    }
}

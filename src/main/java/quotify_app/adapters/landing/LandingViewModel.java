package quotify_app.adapters.landing;

import java.util.ArrayList;
import java.util.List;

import quotify_app.adapters.ViewModel;
import quotify_app.entities.regionEntities.Area;

/**
 * The ViewModel for the Landing View.
 */
public class LandingViewModel extends ViewModel<LandingState> {

    public static final String TITLE_LABEL = "Quotify";
    public static final String SIGNUP_BUTTON_LABEL = "SignUp";
    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String COUNTRY_LABEL = "Select Country";
    public static final String STATE_LABEL = "Select State";
    public static final String CITY_LABEL = "Select City";
    public static final String STREET_LABEL = "Enter Street Address";
    public static final String FIND_PROPERTY_BUTTON_LABEL = "Find Property";

    private List<Area> availableCountries = new ArrayList<>();
    private List<Area> availableStates = new ArrayList<>();
    private List<Area> availableCities = new ArrayList<>();
    private List<String> addressSuggestions = new ArrayList<>();

    public LandingViewModel() {
        super("landing");
        setState(new LandingState());
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

    public List<String> getAddressSuggestions() {
        return addressSuggestions;
    }

    public void setAddressSuggestions(List<String> addressSuggestions) {
        this.addressSuggestions = addressSuggestions;
    }
}

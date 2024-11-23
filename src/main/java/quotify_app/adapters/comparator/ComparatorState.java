package quotify_app.adapters.comparator;

import java.util.ArrayList;
import java.util.List;

import quotify_app.entities.Property;

/**
 * The state for the Comparator View Model.
 *  The private attribute properties stores the data for each property in separate maps.
 */
public class ComparatorState {
    private boolean isLoggedIn;
    private List<Property> properties;

    /**
     * Initializes the ComparatorState with default values.
     */
    public ComparatorState() {
        // Default to not logged in
        this.isLoggedIn = false;
        this.properties = new ArrayList<>();
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // Getter and setter for the lists
    /**
     * Retrieves the list of properties.
     *
     * @return the list of properties.
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * Updates the property list.
     *
     * @param properties a list of properties to set. If null, no changes are made.
     */
    public void setProperties(List<Property> properties) {
        if (properties != null) {
            this.properties = new ArrayList<>(properties);
        }
    }

    /**
     * Adds a property to the list.
     *
     * @param property the property to add. If null, the method does nothing.
     */
    public void addProperty(Property property) {
        if (property != null) {
            this.properties.add(property);
        }
    }

    /**
     * Retrieves a property based on its position in the list.
     *
     * @param index the position of the property in the list.
     * @return the property value as a string, or {@code null} if the index is invalid.
     */
    public String getProperty(int index) {
        String result = null;
        if (index >= 0 && index < properties.size()) {
            result = properties.get(index).toString();
        }
        return result;
    }

}

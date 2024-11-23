package quotify_app.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of property data.
 */
public class Property {
    private List<String> propertyData;

    /**
     * Constructs an empty Property object.
     */
    public Property() {
        this.propertyData = new ArrayList<>();
    }

    /**
     * Adds or updates a property in the map.
     *
     * @param lists   the key of the property.
     */
    public void setPropertyData(List<String> lists) {
        propertyData = lists;
    }

    public List<String> getPropertyData() {
        return propertyData;
    }
}

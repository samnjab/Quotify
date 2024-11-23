package quotify_app.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of properties as key-value pairs.
 */
public class Property {
    private final Map<String, String> propertyData;

    /**
     * Constructs an empty Property object.
     */
    public Property() {
        this.propertyData = new HashMap<>();
    }

    /**
     * Adds or updates a property in the map.
     *
     * @param key   the key of the property.
     * @param value the value of the property.
     */
    public void setProperty(String key, String value) {
        propertyData.put(key, value);
    }
}

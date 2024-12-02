package quotify_app.usecases.landing;

import java.util.Map;

/**
 * Represents the output data for a property retrieval use case.
 * Contains all the information needed to display property details in the view.
 */
public class PropertyOutputData {

    private final String propertyAddress;
    private final Map<String, String> propertyDetails;

    /**
     * Constructs a new PropertyOutputData object with the specified property address and details.
     * @param propertyAddress  The confirmed property address.
     * @param propertyDetails  Additional property metadata to be displayed.
     */
    public PropertyOutputData(String propertyAddress, Map<String, String> propertyDetails) {
        this.propertyAddress = propertyAddress;
        this.propertyDetails = propertyDetails;
    }

    /**
     * Gets the confirmed property address.
     * @return The property address.
     */
    public String getPropertyAddress() {
        return propertyAddress;
    }

    /**
     * Gets additional details about the property.
     * @return A map containing property details.
     */
    public Map<String, String> getPropertyDetails() {
        return propertyDetails;
    }
}

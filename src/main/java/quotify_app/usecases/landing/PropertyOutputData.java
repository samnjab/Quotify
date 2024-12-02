package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the output data for a property retrieval use case.
 * Contains all the information needed to display property details in the view.
 */
public class PropertyOutputData {

    private final String propertyAddress;
    private final Map<String, String> propertyDetails;

    /**
     * Constructs a new PropertyOutputData object with the specified property address and property object.
     * @param address  The confirmed property address.
     * @param property  the property object.
     */
    public PropertyOutputData(Address address, Property property) {
        this.propertyAddress = address.fetchAddress1() + address.fetchAddress2();
        this.propertyDetails = constructPropDetails(property.getSummary());
    }

    // Helper methods:
    private Map<String, String> constructPropDetails(Summary summary) {
        final Map<String, String> propDetails = new HashMap<>();
        propDetails.put("Property Type", summary.getPropTypeString());
        propDetails.put("Condition", summary.getConditionString());
        propDetails.put("Bedrooms", summary.getBedsString());
        propDetails.put("Bathrooms", summary.getBathsString());
        propDetails.put("Year Built", summary.getYearBuiltString());
        propDetails.put("Size (sqft)", summary.getSizeString());
        propDetails.put("Number of levels", summary.getLevelsString());

        return propDetails;
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

package quotify_app.data_access;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.landing.PropertyDataAccessInterface;
import quotify_app.usecases.landing.exceptions.AddressNotFound;

public class PropertyDataAccessObject implements PropertyDataAccessInterface {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Property propertyCache;

    public PropertyDataAccessObject() {
        this.propertyCache = new Property();
    }

    public void setCurrentProperty(Property property) {
        this.propertyCache = property;
    }

    // Helper methods:
    private String safeGetString(JsonNode node, String key) {
        return (node != null && node.has(key) && !node.get(key).isNull()) ? node.get(key).asText() : "Not found";
    }

    // Helper method to safely extract integer values
    int safeGetInt(JsonNode node, String key) {
        return (node != null && node.has(key) && !node.get(key).isNull()) ? node.get(key).asInt() : -1;
    }

    /**
     * Extracts a Summary object from the detailed property JSON node.
     * @param propertyNode The detailed property JSON node.
     * @return A Summary object containing property details.
     */
    private Summary extractPropertySummary(JsonNode propertyNode) {
        // Extracting individual fields safely, avoiding Null.get()
        final String propType = safeGetString(propertyNode.get("summary"), "proptype");
        final int beds = safeGetInt(propertyNode.get("building").get("rooms"), "beds");
        final int baths = safeGetInt(propertyNode.get("building").get("rooms"), "bathstotal");
        final String condition = safeGetString(propertyNode.get("building").get("construction"), "condition");
        final int levels = safeGetInt(propertyNode.get("building").get("summary"), "levels");
        final int size = safeGetInt(propertyNode.get("building").get("size"), "bldgsize");
        final int yearBuilt = safeGetInt(propertyNode.get("summary"), "yearbuilt");

        // Constructing and returning the Summary object
        return new Summary(propType, beds, baths, condition, levels, size, yearBuilt);
    }

    /**
     * Extracts an Identifier object from the detailed property JSON node.
     * @param propertyNode The detailed property JSON node.
     * @return an Identifier object containing property ids.
     */
    private Identifier extractPropertyIdentifier(JsonNode propertyNode) {
        final String attomId = propertyNode.get("identifier").get("attomId").asText();
        final Map<String, String> geoIdV4 = MAPPER.convertValue(propertyNode.get("location").get("geoIdV4"), Map.class);
        return new Identifier(attomId, geoIdV4);
    }

    /**
     * Finds the attomId of the property that matches the given address.
     * @param properties JSON list of properties.
     * @param address    The address to search for.
     * @return The attomId of the matching property, or an empty string if no match is found.
     * @throws AddressNotFound Exception if the entered address does not match the zipcode.
     */
    private String findPropertyAttomId(JsonNode properties, Address address) throws AddressNotFound {
        for (JsonNode propertyNode : properties) {
            final JsonNode addressNode = propertyNode.get("address");
            if (addressNode != null && address.fetchAddress1().equals(addressNode.get("line1").asText())) {
                final JsonNode identifierNode = propertyNode.get("identifier");
                return identifierNode.get("attomId").asText();
            }
        }
        throw new AddressNotFound("address not found in zipcode", new RuntimeException());
    }

    // Override medthods:
    /**
     * Fetches the property details for a given address.
     * @param address The address to fetch property details for.
     * @return A Property object containing property details.
     * @throws ApiRequestException If 1) properties at zipcode cannot be fetched. 2) property details cannot be fetched.
     * @throws ClientRequestException I/O or Interrupted exception thrown by AttomClient.
     * @throws AddressNotFound Exception if the entered address does not match the zipcode.
     */
    @Override
    public Property getPropertyAtAddress(Address address)
            throws ApiRequestException, AddressNotFound, ClientRequestException {
        final JsonNode properties = AttomClient.fetchPropertiesByZipcode(address.getPostalCode());
        final String attomId = findPropertyAttomId(properties, address);
        final JsonNode propertyNode = AttomClient.fetchPropertyDetails(attomId).get(0);
        final Summary summary = extractPropertySummary(propertyNode);
        final Identifier identifier = extractPropertyIdentifier(propertyNode);
        return new Property(identifier, address, summary);
    }

    public Property getCurrentProperty() {
        return propertyCache;
    }
}

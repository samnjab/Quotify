package quotify_app.data_access;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.currentprice.CurrentPropertyDataAccessInterface;
import quotify_app.usecases.future_pricing.FuturePropertyDataAccessInterface;
import quotify_app.usecases.landing.PropertyDataAccessInterface;
import quotify_app.usecases.landing.exceptions.AddressNotFound;

public class PropertyDataAccessObject implements PropertyDataAccessInterface, CurrentPropertyDataAccessInterface,
        FuturePropertyDataAccessInterface {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Property propertyCache;

    public PropertyDataAccessObject() {
        this.propertyCache = new Property();
    }

    public void setCurrentProperty(Property property) {
        this.propertyCache = property;
    }

    // Helper methods:
    /**
     * Extracts a Summary object from the detailed property JSON node.
     * @param propertyNode The detailed property JSON node.
     * @return A Summary object containing property details.
     */
    private Summary extractPropertySummary(JsonNode propertyNode) {
        final JsonNode summaryNode = propertyNode.get("summary");
        final JsonNode buildingSummaryNode = propertyNode.get("building").get("summary");
        final JsonNode roomsNode = propertyNode.get("building").get("rooms");
        final JsonNode constructionNode = propertyNode.get("building").get("construction");

        return new Summary(
                summaryNode.get("proptype").asText(),
                roomsNode.get("beds").asInt(),
                roomsNode.get("bathstotal").asInt(),
                constructionNode.get("condition").asText(),
                buildingSummaryNode.get("levels").asInt(),
                propertyNode.get("building").get("size").get("bldgsize").asInt(),
                summaryNode.get("yearbuilt").asInt()
        );
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
        final JsonNode propertyNode = AttomClient.fetchPropertyDetails(attomId).get("property").get(0);
        final Summary summary = extractPropertySummary(propertyNode);
        final Identifier identifier = extractPropertyIdentifier(propertyNode);
        return new Property(identifier, address, summary);
    }

    public Property getCurrentProperty() {
        return propertyCache;
    }
}

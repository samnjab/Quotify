package quotify_app.data_access;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.comparator.ComparatorDataAccessInterface;
import quotify_app.usecases.landing.exceptions.AddressNotFound;

/**
 * The ComparatorDataAccessObject is responsible for providing data access operation related to property comparison.
 */
public class ComparatorDataAccessObject implements ComparatorDataAccessInterface {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Property propertyCache;

    public void setCurrentProperty(Property property) {
        this.propertyCache = property;
    }

    // Helper Methods (Existing Ones from PropertyDataAccessObject)
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

    private Identifier extractPropertyIdentifier(JsonNode propertyNode) {
        final String attomId = propertyNode.get("identifier").get("attomId").asText();
        final Map<String, String> geoIdV4 = MAPPER.convertValue(propertyNode.get("location").get("geoIdV4"), Map.class);
        return new Identifier(attomId, geoIdV4);
    }

    private String findPropertyAttomId(JsonNode properties, Address address) throws AddressNotFound {
        for (JsonNode propertyNode : properties) {
            final JsonNode addressNode = propertyNode.get("address");
            if (addressNode != null && address.fetchAddress1().equals(addressNode.get("line1").asText())) {
                final JsonNode identifierNode = propertyNode.get("identifier");
                return identifierNode.get("attomId").asText();
            }
        }
        throw new AddressNotFound("Address not found in zipcode", new RuntimeException());
    }

    public Property getCurrentProperty() {
        return propertyCache;
    }

    /**
     * Calculates a similarity score between the property in the cache and the given property.
     *
     * @param property The property to evaluate.
     * @return A similarity score as an integer.
     */
    private int calculateSimilarityScore(Property property) {
        final Summary cachedSummary = propertyCache.getSummary();
        final Summary summary = property.getSummary();

        return calculateBedsScore(cachedSummary, summary)
                + calculateBathsScore(cachedSummary, summary)
                + calculateSizeScore(cachedSummary, summary)
                + calculateYearBuiltScore(cachedSummary, summary)
                + calculateLevelsScore(cachedSummary, summary);
    }

    private int calculateBedsScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getBeds(), summary.getBeds(), 10);
    }

    private int calculateBathsScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getBaths(), summary.getBaths(), 10);
    }

    private int calculateSizeScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getSize(), summary.getSize(), 20, 100);
    }

    private int calculateYearBuiltScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getYearBuilt(), summary.getYearBuilt(), 5, 10);
    }

    private int calculateLevelsScore(Summary cachedSummary, Summary summary) {
        int result = 0;
        if (cachedSummary.getLevels() == summary.getLevels() && cachedSummary.getLevels() != -1) {
            result = 5;
        }
        return result;
    }

    // helper for calculating the similarity score for each attribute:
    private int calculateAttributeScore(int cachedValue, int currentValue, int maxPoints) {
        int result = 0;
        if (cachedValue != -1 && currentValue != -1) {
            result = maxPoints - Math.abs(cachedValue - currentValue);
        }
        return result;
    }

    private int calculateAttributeScore(int cachedValue, int currentValue, int maxPoints, int normalizationFactor) {
        int result = 0;
        if (cachedValue != -1 && currentValue != -1) {
            result = maxPoints - Math.abs(cachedValue - currentValue) / normalizationFactor;
        }
        return result;
    }

    /**
     * Fetches comparable properties and returns the top 3 most similar properties to the cached property.
     *
     * @return A list of the top 3 most similar Property objects.
     * @throws ApiRequestException If properties cannot be fetched.
     */
    @Override
    public List<Property> getSaleComparables(Area zipCode) throws ApiRequestException {
        final JsonNode propertiesJson = AttomClient.fetchPropertiesByZipcode(zipCode.getName());
        final List<Property> comparedProperties = new ArrayList<>();

        // Extract properties from JSON
        if (propertiesJson != null && propertiesJson.isArray()) {
            for (JsonNode propertyNode : propertiesJson) {
                try {
                    final Summary summary = extractPropertySummary(propertyNode);
                    final Identifier identifier = extractPropertyIdentifier(propertyNode);

                    final JsonNode addressNode = propertyNode.get("address");
                    if (addressNode != null) {
                        final String line1 = addressNode.has("line1") ? addressNode.get("line1").asText() : "";
                        final String city = addressNode.has("city") ? addressNode.get("city").asText() : "";
                        final String state = addressNode.has("state") ? addressNode.get("state").asText() : "";
                        final String fetchedPostalCode = addressNode.has("postalCode") ? addressNode.get("postalCode").asText() : "";

                        if (!line1.isEmpty() && !city.isEmpty() && !state.isEmpty() && !fetchedPostalCode.isEmpty()) {
                            final Address address = new Address("", state, city, line1, "", fetchedPostalCode);
                            comparedProperties.add(new Property(identifier, address, summary));
                        }
                    }
                } catch (Exception e) {
                    // Log the exception for debugging but continue processing other nodes
                    System.err.println("Error parsing propertyNode: " + e.getMessage());
                }
            }
        }

        // Calculate similarity scores and sort properties by score in descending order
        comparedProperties.sort(Comparator.comparingInt(this::calculateSimilarityScore).reversed());

        return comparedProperties;
    }
}

}


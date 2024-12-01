package quotify_app.data_access;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.comparator.ComparatorDataAccessInterface;

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
    public List<Property> getSaleComparables(Area zipCode) throws ApiRequestException, ClientRequestException {
        final JsonNode propertiesJson = AttomClient.fetchPropertiesByZipcode(zipCode.getName());
        final List<Property> comparedProperties = new ArrayList<>();

        // Extract properties from JSON
        if (propertiesJson != null && propertiesJson.isArray()) {
            for (JsonNode propertyNode : propertiesJson) {
                final Summary summary = extractPropertySummary(propertyNode);
                final Identifier identifier = extractPropertyIdentifier(propertyNode);

                final JsonNode addressNode = propertyNode.get("address");
                if (addressNode != null) {
                    String line1 = "";
                    if (addressNode.has("line1")) {
                        line1 = addressNode.get("line1").asText();
                    }

                    String city = "";
                    if (addressNode.has("city")) {
                        city = addressNode.get("city").asText();
                    }

                    String state = "";
                    if (addressNode.has("state")) {
                        state = addressNode.get("state").asText();
                    }

                    String fetchedPostalCode = "";
                    if (addressNode.has("postalCode")) {
                        fetchedPostalCode = addressNode.get("postalCode").asText();
                    }

                    if (!line1.isEmpty() && !city.isEmpty() && !state.isEmpty() && !fetchedPostalCode.isEmpty()) {
                        final Address address = new Address("", state, city, line1, "", fetchedPostalCode);
                        comparedProperties.add(new Property(identifier, address, summary));
                    }
                }
            }
        }

        // Calculate similarity scores and sort properties by score in descending order
        comparedProperties.sort(Comparator.comparingInt(this::calculateSimilarityScore).reversed());
        // limit to top 3
        return comparedProperties.stream().limit(3).collect(Collectors.toList());
    }
}


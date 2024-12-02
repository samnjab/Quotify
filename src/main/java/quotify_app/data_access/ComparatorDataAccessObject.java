package quotify_app.data_access;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.data_access.exceptions.ComparatorClientException;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.comparator.ComparatorDataAccessInterface;


/**
 * The ComparatorDataAccessObject is responsible for providing data access operation related to property comparison.
 */
public class ComparatorDataAccessObject implements ComparatorDataAccessInterface {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Property propertyCache;
    private final int ten = 10;
    private final int five = 5;
    private final int three = 3;
    private final int hundred = 100;
    private final int twenty = 20;

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

    public void setCurrentProperty(Property property) {
        this.propertyCache = property;
    }

    // main logic of getting the top 3 relevent properties.
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
        return calculateAttributeScore(cachedSummary.getBeds(), summary.getBeds(), ten);
    }

    private int calculateBathsScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getBaths(), summary.getBaths(), ten);
    }

    private int calculateSizeScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getSize(), summary.getSize(), twenty, hundred);
    }

    private int calculateYearBuiltScore(Summary cachedSummary, Summary summary) {
        return calculateAttributeScore(cachedSummary.getYearBuilt(), summary.getYearBuilt(), five, ten);
    }

    private int calculateLevelsScore(Summary cachedSummary, Summary summary) {
        int result = 0;
        if (cachedSummary.getLevels() == summary.getLevels() && cachedSummary.getLevels() != -1) {
            result = five;
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
     * @param zipCode The area to search properties for.
     * @return A list of the top 3 most similar Property objects.
     * @throws ApiRequestException If properties cannot be fetched.
     * @throws ClientRequestException If there is a client request error.
     */
    @Override
    public List<Property> getSaleComparables(Area zipCode) throws ApiRequestException {
        final List<Property> comparedProperties = new ArrayList<>();

        try {
            // Fetch JSON response from API
            final ComparatorClient client = new ComparatorClient();
            final String responseJson = client.fetchComparablesByPropertyId(propertyCache.getIdentifier().getAttomId());

            // Parse the JSON response to extract comparables
            final JsonNode rootNode = MAPPER.readTree(responseJson);

            // Assuming the response contains a "PROPERTY_INFORMATION_RESPONSE_ext" with "COMPARABLE_PROPERTY_ext"
            final JsonNode responseNode = rootNode.path("RESPONSE_GROUP").path("RESPONSE").path("RESPONSE_DATA").path("PROPERTY_INFORMATION_RESPONSE_ext");
            final JsonNode comparablesNode = responseNode.path("COMPARABLE_PROPERTY_ext");

            // Iterate over the list and process each comparable property
            for (JsonNode comparableNode : comparablesNode) {
                // Extract summary details
                final String propertyType = comparableNode.path("@StandardUseDescription_ext").asText();
                final int beds = comparableNode.path("STRUCTURE").path("@TotalBedroomCount").asInt();
                final int baths = comparableNode.path("STRUCTURE").path("@TotalBathroomCount").asInt();
                final int size = comparableNode.path("STRUCTURE").path("@GrossLivingAreaSquareFeetCount").asInt();
                final int yearBuilt = comparableNode.path("STRUCTURE").path("STRUCTURE_ANALYSIS").path("@PropertyStructureBuiltYear").asInt();
                final int levels = comparableNode.path("STRUCTURE").path("@StoriesCount").asInt();
                final String condition = comparableNode.path("STRUCTURE").path("CONSTRUCTION").path("@Condition").asText();

                // Extract address details
                final String street = comparableNode.path("@_StreetAddress").asText();
                final String city = comparableNode.path("@_City").asText();
                final String state = comparableNode.path("@_State").asText();
                final String postalCode = comparableNode.path("@_PostalCode").asText();
                final String countryCode = comparableNode.path("@_CountryCode").asText();
                final String streetNumber = comparableNode.path("@_StreetNumber").asText();

                // Build Address and Summary objects
                final Address address = new Address(streetNumber, state, city, street, countryCode, postalCode);
                final Summary summary = new Summary(propertyType, beds, baths, condition, levels, size, yearBuilt);
                final Property property = new Property(new Identifier(comparableNode.path("_IDENTIFICATION").path("@RTPropertyID_ext").asText(), null), address, summary);
                comparedProperties.add(property);
            }

            // Return sorted properties
            comparedProperties.sort(Comparator.comparingInt(this::calculateSimilarityScore).reversed());
            return comparedProperties.stream().limit(3).collect(Collectors.toList());

        } catch (Exception e) {
            throw new ApiRequestException("Failed to fetch or process comparables.", e);
        }
    }
}


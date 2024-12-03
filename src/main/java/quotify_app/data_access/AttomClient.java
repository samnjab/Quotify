package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;

/**
 * API client for interacting with the Attom API.
 */
public class AttomClient {

    // Replace with your actual API key
    private static final String API_KEY = "bb0a69f6de4bafb18f6d0dde08c81aa8";
    private static final String BASE_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/";
    private static final String AREA_BASE_URL = "https://api.gateway.attomdata.com/v4/area/";

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // parser
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // helper methods:

    /**
     * Helper method to send an API request and parse the JSON response.
     * @param url The API endpoint URL.
     * @return A JsonNode containing the parsed response.
     * @throws ApiRequestException for non-200 HTTP responses.
     * @throws ClientRequestException for I/O and interruption errors.
     */
    private static JsonNode sendApiRequest(String url) throws ApiRequestException, ClientRequestException {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("apikey", API_KEY)
                    .GET()
                    .build();

            final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ApiRequestException("API request failed with HTTP status: " + response.statusCode());
            }

            return MAPPER.readTree(response.body());
        }
        catch (IOException | InterruptedException exception) {
            throw new ClientRequestException("Error occurred while sending the request", exception);
        }
    }

    /**
     * Helper method to parse a response node.
     * @param rootNode The root JSON node of the response.
     * @param path Path to the desired array node.
     * @return The array node as a JsonNode.
     * @throws ApiRequestException if the node is not a valid array.
     * @throws ClientRequestException if any Json parsing error occurs.
     */
    private static JsonNode parseResponseNode(JsonNode rootNode, String... path)
            throws ApiRequestException, ClientRequestException {
        JsonNode node = rootNode;
        for (String key : path) {
            node = node.path(key);
        }
        if (node.isTextual()) {
            try {
                node = objectMapper.readTree(node.asText());
            }
            catch (JsonProcessingException e) {
                throw new ClientRequestException("Failed to parse JSON string to array: " + node.asText(), e);
            }
        }
        if (!node.isArray()) {
            throw new ApiRequestException("Response does not contain a valid array at " + String.join(".", path));
        }
        return node;
    }

    /**
     * Fetches all available properties at zipcode.
     * @param zipcode valid zipcode of the area.
     * @return a JsonNode containing all the properties.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchPropertiesByZipcode(String zipcode)
            throws ApiRequestException, ClientRequestException {
        final String url = BASE_URL + "address?postalcode=" + zipcode;
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "property");
    }

    /**
     * Fetches all details of property with attomId.
     * @param attomId valid attomId of the property.
     * @return a JsonNode containing the API response.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchPropertyDetails(String attomId)
            throws ApiRequestException, ClientRequestException {
        final String url = BASE_URL + "detail?attomid=" + attomId;
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "property");
    }

    /**
     * Fetches all available properties at zipcode with size in the minSize-maxSize range.
     * @param zipCode valid zipcode of the area.
     * @param minSize valid lower bound for the property size.
     * @param maxSize valid upper bound for the property size.
     * @return a JsonNode containing all the properties.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchSnapshot(String zipCode, String minSize, String maxSize)
            throws ApiRequestException, ClientRequestException {
        final String url = BASE_URL + "snapshot?postalcode=" + zipCode + "&minUniversalSize=" + minSize + "&maxUniversalSize=" + maxSize;
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "property");
    }

    /**
     * Fetches all available subareas of Type type of a parent area specified by geoIdV4.
     * @param geoIdV4 valid geoIdV4 of the parent area.
     * @param type valid type of the subareas.
     * @return a JsonNode containing all the subareas.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchSubAreas(String geoIdV4, String type)
            throws ApiRequestException, ClientRequestException {
        final String url = AREA_BASE_URL + "geoid/lookup/?geoIdV4=" + geoIdV4 + "&GeoType=" + type;
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "response", "result", "package", "item");
    }

    /**
     * Fetches all available states of the United States of America.
     * @return a JsonNode containing all the states.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchStates()
            throws ApiRequestException, ClientRequestException {
        final String url = AREA_BASE_URL + "state/lookup";
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "response", "result", "package", "item");
    }

    /**
     * Fetches all comparable property sales to a reference property specified by a propertyId.
     * @param propertyId valid attomId of the refrence property.
     * @param searchType the method of search, can either be "Raduis" or "zipcode".
     * @param miles the radius of search in miles.
     * @param bedroomsRange the difference in the # of bedrooms of the reference vs the comparable property.
     * @param sqFeetRange the difference in the size in sqFeet of reference vs the comparable property.
     * @param yearBuiltRange the difference in the year-built of the reference vs the comparable property.
     * @return a JsonNode containing all the comparable property sales.
     * @throws ApiRequestException if API response code != 200.
     * @throws ClientRequestException if there are network failures, I/O problems,
     * or interruptions that occur during the execution of an HTTP request.
     */
    public static JsonNode fetchSalesComparables(String propertyId, String searchType, Integer miles,
                                                 String bedroomsRange, String sqFeetRange, String yearBuiltRange)
            throws ApiRequestException, ClientRequestException {
        final StringBuilder urlBuilder = new StringBuilder(
                "https://api.gateway.attomdata.com/property/v2/salescomparables/propid/")
                .append(propertyId)
                .append("?searchType=").append(searchType);

        if ("Radius".equalsIgnoreCase(searchType)) {
            urlBuilder.append("&miles=").append(miles);
        }

        urlBuilder.append("&bedroomsRange=").append(bedroomsRange)
                .append("&sqFeetRange=").append(sqFeetRange)
                .append("&yearBuiltRange=").append(yearBuiltRange);

        final String url = urlBuilder.toString();
        final JsonNode rootNode = sendApiRequest(url);
        return parseResponseNode(rootNode, "RESPONSE_DATA", "PROPERTY");
    }
}

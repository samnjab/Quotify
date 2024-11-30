package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ApiRequestException;

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

    /**
     * Fetches property data by zipcode.
     * @param zipcode The zipcode to search for.
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if the request is failed with status !=200.
     */
    public static JsonNode fetchPropertiesByZipcode(String zipcode) throws IOException, InterruptedException, ApiRequestException {
        // Construct the API URL
        final String url = BASE_URL + "address?postalcode=" + zipcode;

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch properties: HTTP " + response.statusCode(), new RuntimeException());
        }

        // Parse the response body as JsonNode
        final JsonNode rootNode = MAPPER.readTree(response.body());

        // Navigate to the `.property` array
        final JsonNode propertyNode = rootNode.path("property");
        if (!propertyNode.isArray()) {
            throw new ApiRequestException("Response does not contain a valid `item` array", new RuntimeException());
        }
        return propertyNode;
    }

    /**
     * Fetches property details by attomId.
     * @param attomId The attomId of the property to search for.
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if the request is failed with status !=200.
     */
    public static JsonNode fetchPropertyDetails(String attomId)
            throws IOException, InterruptedException, ApiRequestException {
        // Construct the API URL
        final String url = BASE_URL + "detail?attomid=" + attomId;

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch property details: HTTP " + response.statusCode(), new RuntimeException());
        }

        // Parse the response body as JsonNode
        final JsonNode rootNode = MAPPER.readTree(response.body());

        // Navigate to the `.property` array
        final JsonNode propertyNode = rootNode.path("property");
        if (!propertyNode.isArray()) {
            throw new ApiRequestException("Response does not contain a valid `item` array", new RuntimeException());
        }
        return propertyNode;
    }

    /**
     * Fetches properties within a size range in a zipcode location.
     *
     * @param zipCode The zipcode to search for.
     * @param minSize The lower bound of size in square feet.
     * @param maxSize The upper bound of size in square feet.
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if the request is failed with status !=200.
     */
    public static JsonNode fetchSnapshot(String zipCode, String minSize, String maxSize)
            throws IOException, InterruptedException, ApiRequestException {
        // Construct the API URL
        final String url = BASE_URL + "snapshot?postalcode=" + zipCode + "&minUniversalSize="
                + minSize + "&maxUniversalSize=" + maxSize;

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch snapshot: HTTP "
                    + response.statusCode(), new RuntimeException());
        }

        // Parse the response body as JsonNode
        final JsonNode rootNode = MAPPER.readTree(response.body());

        // Navigate to the `.property` array
        final JsonNode propertyNode = rootNode.path("property");
        if (!propertyNode.isArray()) {
            throw new ApiRequestException("Response does not contain a valid `item` array", new RuntimeException());
        }
        return propertyNode;
    }

    /**
     * Fetches subareas of an area identified by geoIdV4 of a specified type.
     * Extracts and returns the `item` array from the response body.
     *
     * @param geoIdV4 The geoIdV4 of the area to search for.
     * @param type The type of the area (e.g., "state", "city").
     * @return A JsonNode representing the array of items in the response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if response.statusCode() != 200
     */
    public static JsonNode fetchSubAreas(String geoIdV4, String type)
            throws IOException, InterruptedException, ApiRequestException {
        // Construct the API URL
        final String url = AREA_BASE_URL + "geoid/lookup/?geoIdV4=" + geoIdV4 + "&GeoType=" + type;

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch subareas for geoIdV4: " + geoIdV4 + ", type: " + type + response.statusCode(), new RuntimeException());
        }

        // Parse the response body as JsonNode
        final JsonNode rootNode = MAPPER.readTree(response.body());

        // Navigate to the `result.item` array
        final JsonNode itemsNode = rootNode.path("result").path("item");

        // Check if the `item` node exists and is an array
        if (!itemsNode.isArray()) {
            throw new ApiRequestException("Response does not contain a valid `item` array", new RuntimeException());
        }
        return itemsNode;
    }

    public static JsonNode fetchStates()
            throws IOException, InterruptedException, ApiRequestException {
        // Construct the API URL
        final String url = AREA_BASE_URL + "state/lookup";

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch states " + response.statusCode(), new RuntimeException());
        }

        // Parse the response body as JsonNode
        final JsonNode rootNode = MAPPER.readTree(response.body());

        // Navigate to the `result.item` array
        final JsonNode itemsNode = rootNode.path("result").path("item");

        // Check if the `item` node exists and is an array
        if (!itemsNode.isArray()) {
            throw new ApiRequestException("Response does not contain a valid `item` array", new RuntimeException());
        }
        return itemsNode;
    }

    /**
     * Fetches sales comparables for a property identified by propertyId using either radius or zipcode search.
     * @param propertyId The attomId of the property to fetch sales comparables for.
     * @param searchType The type of search (either "Radius" or "Zipcode").
     * @param miles The radius in miles for the search (applicable only if searchType is "Radius").
     * @param bedroomsRange Range of bedrooms (e.g., "2").
     * @param sqFeetRange Square feet range (e.g., "600").
     * @param yearBuiltRange Range of years built (e.g., "10").
     * @return A JsonNode containing the sales comparables response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if the request fails with a non-200 status.
     */
    public static JsonNode fetchSalesComparables(String propertyId, String searchType, Integer miles,
                                                 String bedroomsRange, String sqFeetRange, String yearBuiltRange)
            throws IOException, InterruptedException, ApiRequestException {

        final String baseUrl = "https://api.gateway.attomdata.com/property/v2/salescomparables/propid/" + propertyId;

        final StringBuilder queryParams = new StringBuilder("?searchType=").append(searchType);

        if ("Radius".equalsIgnoreCase(searchType)) {
            if (miles != null) {
                queryParams.append("&miles=").append(miles);
            }
            else {
                throw new IllegalArgumentException("Miles parameter is required for Radius search.");
            }
        }

        queryParams.append("&bedroomsRange=").append(bedroomsRange);
        queryParams.append("&sqFeetRange=").append(sqFeetRange);
        queryParams.append("&yearBuiltRange=").append(yearBuiltRange);

        final String url = baseUrl + queryParams.toString();

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new ApiRequestException("Failed to fetch sales comparables: HTTP " + response.statusCode(),
                    new RuntimeException());
        }
        // returning the whole response.body() for now:
        return MAPPER.readTree(response.body());
    }
}

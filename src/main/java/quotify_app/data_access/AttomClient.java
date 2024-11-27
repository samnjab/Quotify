package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
     *
     * @param zipcode The zipcode to search for.
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public static JsonNode fetchPropertiesByZipcode(String zipcode) throws IOException, InterruptedException {
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
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch properties: HTTP " + response.statusCode());
        }

        // Parse and return the response body as JsonNode
        return MAPPER.readTree(response.body());
    }

    /**
     * Fetches property details by attomId.
     * @param attomId The attomId of the property to search for.
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public static JsonNode fetchPropertyDetails(String attomId) throws IOException, InterruptedException {
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
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch property details: HTTP " + response.statusCode());
        }

        // Parse and return the response body as JsonNode
        return MAPPER.readTree(response.body());
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
     */
    public static JsonNode fetchSnapshot(String zipCode, String minSize, String maxSize)
            throws IOException, InterruptedException {
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
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch snapshot: HTTP " + response.statusCode());
        }

        // Parse and return the response body as JsonNode
        return MAPPER.readTree(response.body());
    }

    /**
     * Fetches subareas of Area identified by geoIdV4 of Type type.
     * @param geoIdV4 The geoIdV4 of the area to search for.
     * @param type The type of the area (e.g., "state", "city").
     * @return A JsonNode containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public static JsonNode fetchSubAreas(String geoIdV4, String type) throws IOException, InterruptedException {
        // Construct the API URL
        final String url = AREA_BASE_URL + geoIdV4 + "&GeoType=" + type;

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
            throw new RuntimeException("Failed to fetch subareas: HTTP " + response.statusCode());
        }

        // Parse and return the response body as JsonNode
        return MAPPER.readTree(response.body());
    }

}

package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Fuck you.
 */
public class AttomClient {

    // Replace with your actual API key
    private static final String API_KEY = "bb0a69f6de4bafb18f6d0dde08c81aa8";
    private static final String BASE_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/";

    /**
     * Fetches property data by zipcode.
     *
     * @param zipcode The zipcode to search for.
     * @return A string containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException .
     */
    public static String fetchPropertiesByZipcode(String zipcode) throws IOException, InterruptedException {
        // Construct the API URL
        final String url = BASE_URL + "address?postalcode=" + zipcode;

        // Create the HTTP client
        final HttpClient client = HttpClient.newHttpClient();

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch properties: HTTP " + response.statusCode());
        }

        // Parse the response body
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.body());

        // Process or return the JSON response
        return jsonResponse.toPrettyString();
    }

    /**
     * Fetches property data by zipcode.
     *
     * @param AttomId The zipcode to search for.
     * @return A string containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws RuntimeException .
     */
    public static String fetchPropertyDetails(String AttomId) throws IOException, InterruptedException {
        // Construct the API URL
        final String url = BASE_URL + "detail?attomid=" + AttomId;

        // Create the HTTP client
        final HttpClient client = HttpClient.newHttpClient();

        // Create the HTTP request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("apikey", API_KEY)
                .GET()
                .build();

        // Send the request and get the response
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the HTTP response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch properties: HTTP " + response.statusCode());
        }

        // Parse the response body
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.body());

        // Process or return the JSON response
        return jsonResponse.toPrettyString();
    }

    public static void main(String[] args) {
        try {
            // Example usage with a sample zipcode
            String zipcode = "1027505"; // Replace with a desired zipcode
            String result = fetchPropertyDetails(zipcode);
            System.out.println("API Response: ");
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}

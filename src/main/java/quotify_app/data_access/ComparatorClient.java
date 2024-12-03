
package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import quotify_app.data_access.exceptions.ComparatorClientException;

public class ComparatorClient {
    private static final String BASE_URL = "https://api.gateway.attomdata.com/property/v2/salescomparables/propid/";
    private final HttpClientWrapper client;
    private final int okCode = 200;

    // Replace with your API key
    private static final String API_KEY = "bb0a69f6de4bafb18f6d0dde08c81aa8";

    public ComparatorClient() {
        this.client = new HttpClientWrapper();
    }

    /**
     * Fetch comparable properties by property ID with specific query parameters.
     *
     * @param propId The property ID for which to fetch comparables.
     * @return The response from the API as a JSON string.
     * @throws ComparatorClientException If there is an error during the request or response processing.
     */
    public String fetchComparablesByPropertyId(String propId) throws ComparatorClientException {
        try {
            // Construct the complete API URL with query parameters
            String url = BASE_URL + propId
                    + "?searchType=ZipCode"
                    + "&minComps=1"
                    + "&maxComps=10"
                    + "&miles=5"
                    + "&bedroomsRange=2"
                    + "&bathroomRange=2"
                    + "&sqFeetRange=600"
                    + "&lotSizeRange=2000"
                    + "&saleDateRange=6"
                    + "&yearBuiltRange=10"
                    + "&ownerOccupied=Both"
                    + "&distressed=IncludeDistressed";

            // Print URL for debugging purposes
            System.out.println("Request URL: " + url);

            // Create HTTP GET request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("apikey", API_KEY) // Add API key
                    .GET()
                    .build();

            // Send HTTP request and get the response
            HttpResponse<String> response = client.send(httpRequest);

            // Check HTTP status code and handle errors
            if (response.statusCode() != okCode) {
                throw new ComparatorClientException(
                        "Failed to fetch comparables. HTTP status: " + response.statusCode() + ". Response: " + response.body()
                );
            }

            return response.body();
        } catch (IOException | InterruptedException err) {
            throw new ComparatorClientException("An error occurred while fetching comparable properties: " + err.getMessage(), err);
        }
    }
}

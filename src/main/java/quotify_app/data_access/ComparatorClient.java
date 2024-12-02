package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import quotify_app.data_access.exceptions.ApiRequestException;

/**
 * A client for interacting with the Property Comparison API.
 */
public class ComparatorClient {
    private static final String COMPARABLES_ENDPOINT = "https://property-comparator-api.com/comparables";
    private final HttpClientWrapper client;
    private final int okCode = 200;

    public ComparatorClient() {
        this.client = new HttpClientWrapper();
    }

    /**
     * Sends a request to fetch comparable properties.
     *
     * @param zipCode The postal code for which to fetch comparables.
     * @return The response from the API as a JSON string.
     * @throws ApiRequestException If there is an error during the request or response processing.
     */
    public String fetchComparables(String zipCode) throws ApiRequestException {
        try {
            // Create HTTP GET request
            final HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(COMPARABLES_ENDPOINT + "?postalcode=" + zipCode))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            // Send HTTP request and get the response
            final HttpResponse<String> response = client.send(httpRequest);

            // Check HTTP status code and handle errors
            if (response.statusCode() != okCode) {
                throw new ApiRequestException(
                        "Failed to fetch comparables. HTTP status: " + response.statusCode() + ". Response: " + response.body()
                );
            }

            return response.body();
        } catch (IOException | InterruptedException err) {
            throw new ApiRequestException("An error occurred while fetching comparable properties: " + err.getMessage(), err);
        }
    }
}

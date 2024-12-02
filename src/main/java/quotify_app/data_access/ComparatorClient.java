package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.ComparatorClientException;
import quotify_app.entities.ComparatorRequest;

/**
 * A client for interacting with the Property Comparison API.
 */
public class ComparatorClient {
    private static final String COMPARABLES_ENDPOINT = "https://api.developer.attomdata.com/docs#!/Sale32Comparables/5894d5d392c0ee0d4cc83a40";
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
     * @throws ComparatorClientException If there is an error during the request or response processing.
     */
    public String fetchComparables(String zipCode) throws ComparatorClientException {
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

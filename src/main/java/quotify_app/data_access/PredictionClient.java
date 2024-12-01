package quotify_app.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.PredictionRequest;

/**
 * A client for interacting with the Housing Price Prediction API.
 */
public class PredictionClient {
    private static final String PREDICT_ENDPOINT = "https://housing-prediction-api-62e3f0c37c7d.herokuapp.com/predict";
    private final HttpClientWrapper client;
    private final ObjectMapper objectMapper;
    private final int okCode = 200;

    public PredictionClient() {
        this.client = new HttpClientWrapper();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Sends a prediction request to the housing price prediction API.
     *
     * @param request The {@link PredictionRequest} object containing input data.
     * @return The prediction response as a JSON string.
     * @throws PredictionClientException If there is an error during the request or response processing.
     */
    public String predict(final PredictionRequest request) throws PredictionClientException {
        try {
            // Convert PredictionRequest to JSON
            final String jsonRequest = objectMapper.writeValueAsString(request);

            // Create HTTP POST request
            final HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(PREDICT_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            // Send HTTP request and get the response
            final HttpResponse<String> response = client.send(httpRequest);

            // Check HTTP status code and handle errors
            if (response.statusCode() != okCode) {
                throw new PredictionClientException(
                        "Failed to predict. HTTP status: " + response.statusCode() + ". Response: " + response.body()
                );
            }

            return response.body();
        }
        catch (PredictionClientException | JsonProcessingException err) {
            throw new PredictionClientException("An error occurred while making the prediction request.", err);
        }
        catch (IOException | InterruptedException err) {
            throw new PredictionClientException(err.getMessage());
        }
    }
}

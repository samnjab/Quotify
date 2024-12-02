package quotify_app.data_access;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.PredictionRequest;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;
import quotify_app.usecases.currentprice.PredictionDataAccessInterface;
import quotify_app.usecases.future_pricing.FuturePredictionDataAccessInterface;

/**
 * A Data Access Object for handling predictions via PredictionClient and managing prediction data.
 */
public class PredictionDataAccessObject implements PredictionDataAccessInterface, FuturePredictionDataAccessInterface {
    private final int currYear = 2022;
    private final int currMonth = 12;
    private final int numFuturePredictions = 6;
    private final PredictionClient predictionClient;
    private double currentPricePrediction;
    private final double[] futurePredictions = new double[numFuturePredictions];

    /**
     * Constructs a PredictionDataAccessObject with the given PredictionClient.
     *
     * @param predictionClient The client used to make prediction requests.
     */
    public PredictionDataAccessObject(PredictionClient predictionClient) {
        this.predictionClient = predictionClient;
    }

    /**
     * Makes a prediction for the current price of the given property.
     *
     * @param property The property for which to predict the current price.
     * @return The predicted current price.
     * @throws PredictionClientException If there is an issue with the prediction request or response.
     */
    @Override
    public double getCurrentPricePrediction(final Property property) throws PredictionClientException {
        final PredictionRequest request = buildPredictionRequest(property, 0);
        final String predictionResponse = predictionClient.predict(request);

        try {
            // Parse the prediction value from the JSON response
            final Map<String, Double> responseMap = new ObjectMapper().readValue(predictionResponse, Map.class);
            final double prediction = responseMap.get("prediction");

            // Store the prediction for logging or future use
            this.currentPricePrediction = prediction;

            return prediction;
        }
        catch (JsonProcessingException err) {
            throw new PredictionClientException("Failed to parse prediction response.", err);
        }
    }

    /**
     * Makes future predictions (0-5 months offset) for the given property.
     *
     * @param property The property for which to predict future prices.
     * @return An array of future predictions (length 6).
     * @throws PredictionClientException If there is an issue with the prediction requests or responses.
     */
    public double[] getFuturePricePredictions(final Property property) throws PredictionClientException {
        for (int offset = 0; offset < numFuturePredictions; offset++) {
            final PredictionRequest request = buildPredictionRequest(property, offset);
            final String predictionResponse = predictionClient.predict(request);

            try {
                // Parse the prediction value from the JSON response
                final Map<String, Double> responseMap = new ObjectMapper().readValue(predictionResponse, Map.class);
                futurePredictions[offset] = responseMap.get("prediction");
            }
            catch (JsonProcessingException err) {
                throw new PredictionClientException("Failed to parse prediction response for offset " + offset, err);
            }
        }
        return futurePredictions;
    }

    /**
     * Builds a {@link PredictionRequest} for a given property and month offset.
     *
     * @param property    The property for which to make a prediction.
     * @param monthOffset The month offset for the prediction.
     * @return A {@link PredictionRequest} object ready to send to the API.
     * @throws IllegalArgumentException -- yep.
     */
    private PredictionRequest buildPredictionRequest(final Property property, final int monthOffset) {

        final String geoidv4 = property.getIdentifier().getGeoIdV4().get("ZI");
        final Summary summary = property.getSummary();

        final int propertyAge = currYear - summary.getYearBuilt();

        return PredictionRequest.requestBuilder()
                .beds(summary.getBeds())
                .baths(summary.getBaths())
                .universalsize(summary.getSize())
                .geoidv4(geoidv4)
                .propclass(summary.getPropType())
                .yearbuilt(summary.getYearBuilt())
                .monthOffset(monthOffset)
                .propertyage(propertyAge)
                .year(currYear)
                .month(currMonth)
                .build();
    }

    /**
     * Returns a value representing the predicted price for the property at the current time.
     * @return the double value representing the prediction.
     */
    public double getCurrentPricePredictionString() {
        return currentPricePrediction;
    }
}

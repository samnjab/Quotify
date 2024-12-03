package quotify_app.usecases.currentprice;

import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.regionEntities.Property;

/**
 * Interface for accessing prediction data.
 * Defines methods for retrieving current price predictions.
 */
public interface PredictionDataAccessInterface {

    /**
     * Gets the current price prediction for the given property.
     *
     * @param property The property for which to get the prediction.
     * @return The predicted current price.
     * @throws PredictionClientException If there is an issue with the prediction.
     */
    double getCurrentPricePrediction(Property property) throws PredictionClientException;
}

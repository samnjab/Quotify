package quotify_app.usecases.future_pricing;

import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.regionEntities.Property;

/**
 * Do stuff.
 */
public interface FuturePredictionDataAccessInterface {

    /**
     * Return the array of the future predicted price from 0-5 months in the future.
     * @param property current property to get price predictions for.
     * @return the double array returned, where arr[0] is the current prediction, arr[1] is 1 month prediction, etc.
     * @throws PredictionClientException when there is an error predicting future prices.
     */
    double[] getFuturePricePredictions(Property property) throws PredictionClientException;

}

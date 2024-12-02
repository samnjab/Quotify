package quotify_app.adapters.future_price;

import java.util.ArrayList;
import java.util.List;

/**
 * The state class for the FuturePrice ViewModel.
 * Holds the data needed for the FuturePriceView.
 */
public class FuturePriceState {
    private boolean isLoggedIn;
    private boolean isPredictionError;
    private double[] futurePrices;
    private String predictionErrorMsg;

    /**
     * Initializes the FuturePriceState with default values.
     */
    public FuturePriceState() {
        // Default to not logged in
        this.isLoggedIn = false;
        this.isPredictionError = false;
        this.futurePrices = new double[0];
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setFuturePrices(double[] futurePricesArray) {
        this.futurePrices = futurePricesArray;
    }

    public void setPredictionErrorMsg(String predictionErrorMsg) {
        this.predictionErrorMsg = predictionErrorMsg;
    }

    public boolean isPredictionError() {
        return isPredictionError;
    }

    public String getPredictionErrorMsg() {
        return predictionErrorMsg;
    }

    public void setIsPredictionError() {
        this.isPredictionError = true;
    }

    public double[] getFuturePrices() {
        return futurePrices;
    }
}

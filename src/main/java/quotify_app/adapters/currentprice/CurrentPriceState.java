package quotify_app.adapters.currentprice;

/**
 * The state class for the CurrentPrice ViewModel.
 * Holds the data needed for the CurrentPriceView.
 */
public class CurrentPriceState {
    private String currentPrice;
    private boolean isLoggedIn;
    // New field to track if price has been fetched
    private boolean priceFetched;

    /**
     * Initializes the CurrentPriceState with default values.
     */
    public CurrentPriceState() {
        // Initialize currentPrice to "Loading..." or an empty string
        this.currentPrice = "Click 'Show Current Price' to fetch the price.";
        // Default to not logged in
        this.isLoggedIn = false;
        // Initially, price has not been fetched
        this.priceFetched = false;
    }

    // Getter and setter for currentPrice
    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
        // When currentPrice is set, mark price as fetched
        this.priceFetched = true;
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // Getter and setter for priceFetched
    public boolean isPriceFetched() {
        return priceFetched;
    }

    public void setPriceFetched(boolean priceFetched) {
        this.priceFetched = priceFetched;
    }
}

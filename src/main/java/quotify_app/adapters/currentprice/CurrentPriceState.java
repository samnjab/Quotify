package quotify_app.adapters.currentprice;

/**
 * The state class for the CurrentPrice ViewModel.
 * Holds the data needed for the CurrentPriceView.
 */
public class CurrentPriceState {
    private String currentPrice;
    private boolean isLoggedIn;

    /**
     * Initializes the CurrentPriceState with default values.
     */
    public CurrentPriceState() {
        // this placeholder will show the generated estimate price of the house.
        this.currentPrice = "(loading... )";
        // Default to not logged in
        this.isLoggedIn = false;
    }

    // Getter and setter for currentPrice
    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}

package quotify_app.adapters.future_price;

/**
 * The state class for the FuturePrice ViewModel.
 * Holds the data needed for the FuturePriceView.
 */
public class FuturePriceState {
    private boolean isLoggedIn;

    /**
     * Initializes the FuturePriceState with default values.
     */
    public FuturePriceState() {
        // Default to not logged in
        this.isLoggedIn = false;
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}

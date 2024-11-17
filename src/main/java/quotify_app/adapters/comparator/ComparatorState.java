package quotify_app.adapters.comparator;

/**
 * The state for the Comparator View Model.
 */
public class ComparatorState {
    private boolean isLoggedIn;

    /**
     * Initializes the ComparatorState with default values.
     */
    public ComparatorState() {
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

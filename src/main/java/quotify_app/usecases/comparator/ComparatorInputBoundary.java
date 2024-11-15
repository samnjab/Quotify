package quotify_app.usecases.comparator;

/**
 * Input Boundary for actions related to logging in.
 */
public interface ComparatorInputBoundary {

    /**
     * Switches to the current price view.
     */
    void goToCurrentPrice();

    /**
     * Switches to the input view.
     */
    void goToInput();
}

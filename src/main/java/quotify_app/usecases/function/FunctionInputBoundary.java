package quotify_app.usecases.function;

/**
 * Input Boundary for actions which are related to choosing functionality.
 */
public interface FunctionInputBoundary {

    /**
     * Switches to the CurrentPrice view.
     */
    void goToCurrentPrice();

    /**
     * Switches to the Comparator view.
     */
    void goToComparator();

}

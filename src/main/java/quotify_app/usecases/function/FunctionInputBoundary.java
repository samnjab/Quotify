package quotify_app.usecases.function;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface FunctionInputBoundary {
    /**
     * Executes the function use case.
     */
    void execute();

    /**
     * Switches to the CurrentPrice view.
     */
    void goToCurrentPrice();

    /**
     * Switches to the Comparator view.
     */
    void goToComparator();

}

package quotify_app.usecases.function;

/**
 * Output Boundary for the Function Use Case.
 */
public interface FunctionOutputBoundary {
    /**
     * Interface for the presenter to execute the switch to currentPrice usecase.
     */
    void goToCurrentPrice();
    
    /**
     * Interface for the presenter to execute the switch to comparator usecase.
     */
    void goToComparator();

}

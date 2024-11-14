package quotify_app.usecases.function;

/**
 * Output Boundary for the Function Use Case.
 */
public interface FunctionOutputBoundary {
    /**
     * Interface for the presenter to execute the switch to currentPrice usecase.
     */
    void goToCurrentPriceGuest();

    /**
     * Interface for the presenter to execute the switch to currentPriceLoggedIn usecase.
     */
    void goToCurrentPriceUser();

    /**
     * Interface for the presenter to execute the switch to comparator usecase.
     */
    void goToComparatorGuest();

    /**
     * Interface for the presenter to execute the switch to comparatorLoggedIn usecase.
     */
    void goToComparatorUser();
}

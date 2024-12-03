package quotify_app.usecases.comparator;

import quotify_app.entities.regionEntities.Property;

import java.util.List;

/**
 * Output Boundary for presenting the result of the Comparator Use Case.
 */
public interface ComparatorOutputBoundary {

    /**
     *  Interface for executing the switch to current price screen usecase.
     */
    void goToCurrentPrice();

    /**
     *  Interface for executing the switch to input screen usecase.
     */
    void goToInput();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void updateLoginStatus();

    /**
     * Interface for executing the switch to user profile screen usecase.
     */
    void goToUserProfile();

    void updateProperties(List<Property> comparables);
}

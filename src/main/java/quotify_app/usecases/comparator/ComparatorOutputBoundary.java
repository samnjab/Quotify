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
    void goToLanding();

    /**
     * Prepares the response if the user is logged in or not.
     */
    void updateLoginStatus();

    /**
     * Interface for executing the switch to user profile screen usecase.
     */
    void goToUserProfile();

    /**
     * Interface for updating the view with the fetched comparable properties.
     * @param comparables A list of the comparable properties to be presented.
     */
    void updateProperties(List<Property> comparables);

    /**
     * Interface for when the comparaables could not be retrieved for a given property.
     */
    void presentCompareFailed();
}

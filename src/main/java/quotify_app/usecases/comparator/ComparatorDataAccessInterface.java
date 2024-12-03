package quotify_app.usecases.comparator;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;

import java.util.List;

/**
 * Data Access Interface for retrieving Property data in the Select Property and
 * Compare Properties Use Cases.
 */
public interface ComparatorDataAccessInterface {

    /**
     * Fetches and returns a list of properties by zipcode.
     * @return properties a list of Properties at zipCode.
     * @throws ApiRequestException if the Properties at zipCode cannot be fetched.
     * @throws ClientRequestException if the ClientRequest is not properly formatted.
     */
    List<Property> getSaleComparables() throws ApiRequestException, ClientRequestException;

}

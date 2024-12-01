package quotify_app.usecases.comparator;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;

import java.util.List;

/**
 * Data Access Interface for retrieving Property data in the Select Property and
 * Compare Properties Use Cases.
 */
public interface ComparatorDataAccessInterface {
    /**
     * Returns the cached property.
     * @return Property current property.
     */
    Property getCurrentProperty();

    /**
     * Caches the passed property for access throughout a session.
     * @param property the selected property.
     */
    void setCurrentProperty(Property property);

    /**
     * Fetches and returns a list of properties by zipcode.
     * @param zipCode the Area object containing zipCode of the properties to be fetched.
     * @return properties a list of Properties at zipCode.
     * @throws ApiRequestException if the Properties at zipCode cannot be fetched.
     */
    List<Property> getSaleComparables(Area zipCode) throws ApiRequestException, ClientRequestException;

}

package quotify_app.usecases.landing;

import java.util.List;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;

/**
 * Data Access Interface for retrieving Property data in the Select Property and
 * Compare Properties Use Cases.
 */
public interface PropertyDataAccessInterface {
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
     *  Fetches and returns a property by address.
     * @param address the Address object containing address of the property to be fetched.
     * @return property wrapped in Property.
     * @throws ApiRequestException if the Property at Address cannot be fetched.
     */
    Property getPropertyAtAddress(Address address) throws ApiRequestException;
}

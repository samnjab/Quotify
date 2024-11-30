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
     *  Fetches and returns a property by address.
     * @param address the Address object containing address of the property to be fetched.
     * @return property wrapped in Property.
     * @throws ApiRequestException if the Property at Address cannot be fetched.
     */
    Property getPropertyAtAddress(Address address) throws ApiRequestException;

    /**
     * Fetches and returns a list of properties by zipcode.
     * @param zipCode the Area object containing zipCode of the properties to be fetched.
     * @return properties a list of Properties at zipCode.
     * @throws ApiRequestException if the Properties at zipCode cannot be fetched.
     */
    List<Property> getSaleComparables(Area zipCode) throws ApiRequestException;
}

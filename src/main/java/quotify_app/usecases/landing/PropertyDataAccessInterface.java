package quotify_app.usecases.landing;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.landing.exceptions.AddressNotFound;

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
     * @throws ApiRequestException If 1) properties at zipcode cannot be fetched. 2) property details cannot be fetched.
     * @throws ClientRequestException I/O or Interrupted exception thrown by AttomClient.
     * @throws AddressNotFound Exception if the entered address does not match the zipcode.
     */
    Property getPropertyAtAddress(Address address) throws ApiRequestException, ClientRequestException, AddressNotFound;

    /**
     * Fetches and returns a list of properties by zipcode.
     * @param zipCode the Area object containing zipCode of the properties to be fetched.
     * @return properties a list of Properties at zipCode.
     * @throws ApiRequestException if the Properties at zipCode cannot be fetched.
     */
//    List<Property> getSaleComparables(Area zipCode) throws ApiRequestException;
}

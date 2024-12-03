package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Address;

/**
 * Input Data for the Select Address Use Case, containing information about the selected address.
 */
public class AddressDataTransferObj {
    private final AreaDataTransferObj country;
    private final AreaDataTransferObj state;
    private final AreaDataTransferObj city;
    private final AreaDataTransferObj zipCode;
    private final String address;

    public AddressDataTransferObj(AreaDataTransferObj country, AreaDataTransferObj state, AreaDataTransferObj city, AreaDataTransferObj zipCode, String address) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    /**
     * Getter function for the selected country.
     * @return area object of the selected country.
     */
    public AreaDataTransferObj getCountry() {
        return country;
    }

    /**
     * Getter function for the selected state.
     * @return area object of the selected state.
     */
    public AreaDataTransferObj getState() {
        return state;
    }

    /**
     * Getter function for the selected city.
     * @return area object of the selected city.
     */
    public AreaDataTransferObj getCity() {
        return city;
    }

    /**
     * Getter function for zipCode.
     * @return a string representation of the selected zipcode.
     */
    public AreaDataTransferObj getZipCode() {
        return zipCode;
    }

    /**
     * Splits street address into street number and street name.
     * @param addressVal String representation of street address.
     * @return an array of strings of length 2, with street number at index 0 and street name at index 1.
     * @throws IllegalArgumentException when address is null or an empty string, or when format is inalid.
     */

    private String[] addressSplitter(String addressVal) throws IllegalArgumentException {
        if (addressVal == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }

        final String[] parts = addressVal.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid address format. Expected: '<number> <street name>'");
        }

        final String streetNumber = parts[0];
        final String streetName = parts[1];

        return new String[]{streetNumber, streetName};
    }

    /**
     * Returns an Address object with the inputted information.
     * @return a Address object of the property address.
     */
    public Address constructAddress() {
        final String countryCode = country.getAreaCode();
        final String stateCode = state.getAreaCode();
        final String cityName = city.getAreaName();
        final String streetNumber = addressSplitter(address)[0];
        final String streetName = addressSplitter(address)[1];
        final String postalCode = zipCode.getAreaName();
        return new Address(countryCode, stateCode, cityName, streetName, streetNumber, postalCode);
    }
}

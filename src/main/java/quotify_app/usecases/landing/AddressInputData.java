package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;

/**
 * Input Data for the Select Address Use Case, containing information about the selected address.
 */
public class AddressInputData {
    private final Area country;
    private final Area state;
    private final Area city;
    private final Area zipCode;
    private final String address;

    public AddressInputData(Area country, Area state, Area city, Area zipCode, String address) {
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
    public Area getCountry() {
        return country;
    }

    /**
     * Getter function for the selected state.
     * @return area object of the selected state.
     */
    public Area getState() {
        return state;
    }

    /**
     * Getter function for the selected city.
     * @return area object of the selected city.
     */
    public Area getCity() {
        return city;
    }

    /**
     * Getter function for zipCode.
     * @return a string representation of the selected zipcode.
     */
    public Area getZipCode() {
        return zipCode;
    }

    /**
     * Splits street address into street number and street name.
     * @param address String representation of street address.
     * @return an array of strings of length 2, with street number at index 0 and street name at index 1.
     * @throws IllegalArgumentException when address is null or an empty string, or when format is inalid.
     */

    private String[] addressSplitter(String address) throws IllegalArgumentException {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }

        final String[] parts = address.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid address format. Expected: '<number> <street name>'");
        }

        final String streetNumber = parts[0];
        final String streetName = parts[1];

        // Validate that the first part is a valid number
        if (!streetNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid address format. Street number should be numeric.");
        }
        return new String[]{streetNumber, streetName};
    }

    /**
     * Returns an Address object with the inputted information.
     * @return a Address object of the property address.
     */
    public Address constructAddress() {
        final String countryCode = country.getNameCode();
        final String stateCode = state.getNameCode();
        final String cityName = city.getName();
        final String streetNumber = addressSplitter(address)[0];
        final String streetName = addressSplitter(address)[1];
        return new Address(countryCode, stateCode, cityName, streetName, streetNumber);
    }
}

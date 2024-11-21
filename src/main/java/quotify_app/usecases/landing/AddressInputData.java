package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Address;

/**
 * Input Data for the Select Region Use Case, containing information about the selected region.
 */
public class AddressInputData {
    private final Area country;
    private final Area state;
    private final Area city;
    private final String address;

    public AddressInputData(Area country, Area state, Area city, String address) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    /**
     * Getter function for the selected region name.
     * @return a string representation of the region's country.
     */
    public Area getCountry() {
        return country;
    }

    /**
     * Getter function for the region id.
     * @return a string representation of the selected region's state.
     */
    public Area getState() {
        return state;
    }

    /**
     * Getter function for the region id.
     * @return a string representation of the selected region's city.
     */
    public Area getCity() {
        return city;
    }

    private String[] addressSplitter(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }

        String[] parts = address.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid address format. Expected: '<number> <street name>'");
        }

        String streetNumber = parts[0];
        String streetName = parts[1];

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
    public Address getAddress() {
        String countryCode = country.getNameCode();
        String stateCode = state.getNameCode();
        String cityName = city.getName();
        String streetNumber = addressSplitter(address)[0];
        String streetName =  addressSplitter(address)[1];
        return Address(countryCode, stateCode, cityName, streetName, streetNumber);

    }


}

package quotify_app.entities.regionEntities;

/**
 * The representation of an address in our program.
 */
public class Address {
    private final String countryCode;
    private final String stateCode;
    private final String city;
    private final String street;
    private final String streetNumber;
    private final String postalCode;

    public Address(String countryCode, String stateCode, String city, String street, String streetNumber, String postalCode) {
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public Address() {
        this.countryCode = "";
        this.stateCode = "";
        this.city = "";
        this.street = "";
        this.streetNumber = "";
        this.postalCode = "";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the string representation of the first line of address in our program.
     * @return first line of address.
     */
    public String fetchAddress1() {
        return streetNumber + " " + street;
    }

    /**
     * Returns the string representation of the second line of address in our program.
     * @return second line of address.
     */
    public String fetchAddress2() {
        return city + ", " + stateCode;
    }

    @Override
    public String toString() {
        return fetchAddress1() + "\n" + fetchAddress2();
    }

}

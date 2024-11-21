package quotify_app.entities.regionEntities;

import java.util.Objects;

/**
 * The representation of an address in our program.
 */
public class Address {
    private final String countryCode;
    private final String stateCode;
    private final String city;
    private final String street;
    private final String streetNumber;

    public Address(String countryCode, String stateCode, String city, String street, String streetNumber) {
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Address address = (Address) obj;
        return Objects.equals(countryCode, address.countryCode)
                && Objects.equals(stateCode, address.stateCode)
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street)
                && Objects.equals(streetNumber, address.streetNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, stateCode, city, street, streetNumber);
    }
}

package quotify_app.entities.regionEntities;

/**
 * The representation of a region in our program.
 */
public class Region {
    private final Area country;
    private final Area state;
    private final Area city;
    private final Area zipCode;

    public Region(Area country, Area state, Area city, Area zipCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Area getCountry() {
        return country;
    }

    public Area getState() {
        return state;
    }

    public Area getCity() {
        return city;
    }

    public Area getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Region{" + "country=" + country + ", state=" + state
                + ", city=" + city + ", zipCode=" + zipCode + '}';
    }
}

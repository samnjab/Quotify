package quotify_app.entities.regionEntities;

/**
 * The representation of a ZipCode region in our program.
 */

public class ZipCode extends Region {
    private final Country country;
    private final State state;
    private final City city;
    private final County county;

    public ZipCode(String name, String regionId, String code, Country country, State state, City city, County county) {
        super(name, "City", regionId, code);
        this.country = country;
        this.state = state;
        this.city = city;
        this.county = county;
    }
}

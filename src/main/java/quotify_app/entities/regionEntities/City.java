package quotify_app.entities.regionEntities;

/**
 * The abstract representation of a City in our program.
 */

public class City extends AbstractRegion {
    private final Country country;
    private final State state;

    public City(String name, String code, String regionId, Country country, State state) {
        super(name, "City", regionId, code);
        this.country = country;
        this.state = state;
    }
}

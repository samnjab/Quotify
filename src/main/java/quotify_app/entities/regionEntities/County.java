package quotify_app.entities.regionEntities;

/**
 * The abstract representation of a County in our program.
 */

public class County extends AbstractRegion {
    private final Country country;
    private final State state;
    private final City city;

    public County(String name, String code, String regionId, Country country, State state, City city) {
        super(name, "City", regionId, code);
        this.country = country;
        this.state = state;
        this.city = city;
    }
}

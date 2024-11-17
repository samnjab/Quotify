package quotify_app.entities.regionEntities;

/**
 * The abstract representation of a Country in our program.
 */

public class Country extends AbstractRegion {
    public Country(String name, String regionId, String code) {
        super(name, "Country", regionId, code);
    }
}

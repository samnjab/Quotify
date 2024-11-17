package quotify_app.entities.regionEntities;

/**
 * The representation of a Country in our program.
 */

public class Country extends Region {
    public Country(String name, String regionId, String code) {
        super(name, "Country", regionId, code);
    }
}

package quotify_app.entities.regionEntities;

/**
 * The representation of a State in our program.
 */

public class State extends Region {
    private final Country country;

    public State(String name, String regionId, String code, Country country) {
        super(name, "State", regionId, code);
        this.country = country;

    }

    public Country getCountry() {
        return country;
    }
}

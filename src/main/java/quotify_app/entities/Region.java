package quotify_app.entities;

/**
 * The representation of a region in our program.
 */
public interface Region {

    /**
     * Returns the name of the region.
     * @return the name of the region.
     */
    String getRegionName();

    /**
     * Returns the id of the region.
     * @return the id of the region.
     */
    String getRegionId();

    /**
     * Returns the type of the region.
     * @return the type of the region.
     */
    String getType();

    /**
     * Returns the code of the region composed of letters.
     * @return the code of the region.
     */
    String getCode();

    /**
     * Returns the country the region resides in .
     * @return the Country object of the region.
     */
    Country getCountry();

    /**
     * Returns the state the region resides in .
     * @return the State object of the region.
     */
    State getState();

    /**
     * Returns the city the region resides in .
     * @return the City object of the region.
     */
    City getCity();

    /**
     * Returns the county the region resides in.
     * @return the County object of the region.
     */
    County getCounty();

}

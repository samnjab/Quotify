package quotify_app.entities.regionEntities;

/**
 * The method collection blueprint of a region in our program.
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

}

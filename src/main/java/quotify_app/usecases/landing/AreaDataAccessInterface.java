package quotify_app.usecases.landing;

import java.util.List;

import quotify_app.entities.regionEntities.Area;

/**
 * Data Access Interface for retrieving Area data in the Select Area Use Case.
 */
public interface AreaDataAccessInterface {

    /**
     * Checks if an Area with the given geoIdV4 exists.
     * @param geoIdV4 the geoIdv4 to check
     * @return true if the Area exists, false otherwise.
     */
    boolean existsByGeoIdV4(String geoIdV4);

    /**
     * Retrieves an Area by its geoIdV4.
     * @param geoIdV4 the geoIdV4 of the Area to retrieve.
     * @return the Area object corresponding to the geoIdV4.
     */
    Area getArea(String geoIdV4);

    /**
     * Retrieves all the sub-Areas of the Area with geoIdV4.
     * @param geoIdV4 the geoIdV4 of the super Area.
     * @param type the type of subare to the fetched.
     * @return a List of the sub Area objects corresponding to the geoIdV4.
     * @throws IllegalAccessError if the geoIdV4 does not exist in the databas.
     */
    List<Area> getSubAreas(String geoIdV4, String type);

    /**
     * Retrieves all countries accessible by the app.
     * @return a List of countries available in the database.
     */
    List<Area> getCountries();

    /**
     * Retrieves a list of Areas matching the type and partial name.
     * @param partialName the partial string of Area.name.
     * @param type string matching Area.type.
     * @return a List of Areas available in the database matching the criteria.
     */
    List<Area> findAreasByNameAndType(String partialName, String type);
}

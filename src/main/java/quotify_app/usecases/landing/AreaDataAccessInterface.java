package quotify_app.usecases.landing;

import java.io.IOException;
import java.util.List;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.entities.regionEntities.Area;

/**
 * Data Access Interface for retrieving Area data in the Select Area Use Case.
 */
public interface AreaDataAccessInterface {

    /**
     * Retrieves all countries accessible by the app.
     * @return a List of countries available in the database.
     */
    List<Area> getCountries();

    /**
     * Stores the area selection.
     * @param area the selected area to be stored.
     */
    void selectArea(Area area);

    /**
     * Retrieves all the sub-Areas of the Area with geoIdV4.
     * @param geoIdV4 the geoIdV4 of the super Area.
     * @param type the type of subare to the fetched.
     * @return a List of the sub Area objects corresponding to the geoIdV4.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     * @throws ApiRequestException if the request is failed with status !=200.
     */
    List<Area> getSubAreas(String geoIdV4, String type)
            throws ApiRequestException, IOException, InterruptedException;

    /**
     * Stores a list of areas of Type type into cache.
     * @param areas the areas to be stored.
     * @param type the type of areas to be stored.
     */

    void cacheAreas(List<Area> areas, String type);

    /**
     * Retrieves a list of Areas matching the type and partial name.
     * @param partialName the partial string of Area.name.
     * @param type string matching Area.type.
     * @return a List of Areas available in the database matching the criteria.
     */
    List<Area> findAreasByNameAndType(String partialName, String type);
}

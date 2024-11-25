package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;

/**
 * Data Access Interface for retrieving region data in the Select Region Use Case.
 */
public interface AreaDataAccessInterface {

    /**
     * Checks if an Area with the given geoIdV4 exists.
     * @param geoIdV4 the username to check
     * @return true if the Area exists, false otherwise.
     */
    boolean existsByGeoIdV4(String geoIdV4);

    /**
     * Retrieves a region by its regionId.
     * @param geoIdV4 the geoIdV4 of the region to retrieve.
     * @return the Area object corresponding to the geoIdV4.
     */
    Area get(String geoIdV4);
}

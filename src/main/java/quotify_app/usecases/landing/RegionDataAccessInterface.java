package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Region;

/**
 * Data Access Interface for retrieving region data in the Select Region Use Case.
 */
public interface RegionDataAccessInterface {

    /**
     * Checks if a region with the given regionId exists.
     * @param regionId the username to check
     * @return true if the region exists, false otherwise
     */
    boolean existsByRegionId(String regionId);

    /**
     * Retrieves a region by its regionId.
     * @param regionId the regionId of the region to retrieve.
     * @return the Region object corresponding to the regionId.
     */
    Region get(String regionId);
}

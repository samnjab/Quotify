package quotify_app.data_access;

import java.util.List;

import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaDataAccessInterface;

public class AreaDataAccessObject implements AreaDataAccessInterface {

    private final AreaStore areaCache;

    public AreaDataAccessObject(AreaStore areaCache) {
        this.areaCache = areaCache;
    }

    /**
     * Fetches all subareas for a given parent geoIdV4.
     * @param geoIdV4 The parent geoIdV4.
     * @param type the Type of the subarea to return.
     * @return List of subareas.
     */
    public List<Area> getSubAreas(String geoIdV4, String type) {
        AttomClient.fetchSubAreas(geoIdV4, type);
    }

    /**
     * Finds areas by partial name and type.
     * @param partialName The partial name to search for.
     * @param type The type of area (e.g., "ST" for state, "CS" for city).
     * @return List of matching areas.
     */
    @Override
    public List<Area> findAreasByNameAndType(String partialName, String type) {
        final List<Area> allAreas = areaCache.fetchAllAreasByType(type);
        return allAreas.stream()
                .filter(area -> area.getName().toLowerCase().contains(partialName.toLowerCase()))
                .toList();
    }
}

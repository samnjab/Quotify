package quotify_app.data_access;

import java.util.ArrayList;
import java.util.List;

import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaListOutputData;

/**
 * Area cache that stores a list of available subareas in the form of AreaListOutputData,
 * with associated methods to search through the data.
 */

public class AreaStore {

    private final List<AreaListOutputData> areaCache = new ArrayList<>();

    /**
     * Stores areas for a specific type.
     * @param areaType The type of the areas (e.g., "ST" for state, "CS" for city).
     * @param areas The list of areas to store.
     */
    public void storeAreas(List<Area> areas, String areaType) {
        areaCache.removeIf(data -> data.getAreaType().equalsIgnoreCase(areaType));
        areaCache.add(new AreaListOutputData(areas, areaType, false));
    }

    /**
     * Fetches all areas of a specific type.
     * @param areaType The type of the areas (e.g., "ST" for state, "CS" for city).
     * @return List of areas for the given type.
     */
    public List<Area> fetchAllAreasByType(String areaType) {
        return areaCache.stream()
                .filter(data -> data.getAreaType().equalsIgnoreCase(areaType))
                .flatMap(data -> data.getAreas().stream())
                .toList();
    }

    /**
     * Clears all cached areas (useful for session reset).
     */
    public void clear() {
        areaCache.clear();
    }
}

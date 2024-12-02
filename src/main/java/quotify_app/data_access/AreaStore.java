package quotify_app.data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quotify_app.data_access.exceptions.IllegalTypeException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaListOutputData;
import quotify_app.usecases.landing.AreaOutputData;

/**
 * Area cache that stores a list of available subareas given higher level area selections
 * in the form of AreaListOutputData,
 * with associated methods to search through the data.
 */

public class AreaStore {
    private final List<AreaListOutputData> areaCache;
    private final Map<String, AreaOutputData> areaMap = new HashMap<>();

    public AreaStore() {
        areaCache = new ArrayList<>();
        // Setting up empty selections for each area type:
        areaCache.add(new AreaListOutputData(new ArrayList<>(), "CN", false));
        areaCache.add(new AreaListOutputData(new ArrayList<>(), "ST", false));
        areaCache.add(new AreaListOutputData(new ArrayList<>(), "CS", false));
        areaCache.add(new AreaListOutputData(new ArrayList<>(), "ZI", false));
        // Setting up empty available areas list for each area type:
        areaMap.put("CN", new AreaOutputData(new Area("CN"), false));
        areaMap.put("ST", new AreaOutputData(new Area("ST"), false));
        areaMap.put("CS", new AreaOutputData(new Area("CS"), false));
        areaMap.put("ZI", new AreaOutputData(new Area("ZI"), false));
    }

    // Getter functions:

    /**
     * Returns the cached list of subareas of Type type.
     * @param type the type of cached subareas.
     * @return areaList a list of cached areas of Type type.
     * @throws IllegalTypeException when Type type does not exist.
     */

    public AreaListOutputData getSubAreaList(String type) {
        for (AreaListOutputData areaList : areaCache) {
            if (areaList.getAreaType().equals(type)) {
                return areaList;
            }
        }
        throw new IllegalTypeException("Area type " + type + " not found");
    }

    /**
     * Returns the cached selected area of Type type.
     * @param type the type of cached selected area.
     * @return area cached selected area of Type type.
     * @throws IllegalTypeException when Type type does not exist.
     */
    public AreaOutputData getSelectedArea(String type) {
        for (String key : areaMap.keySet()) {
            if (key.equals(type)) {
                return areaMap.get(key);
            }
        }
        throw new IllegalTypeException("Area type " + type + " not found");
    }

    // Setter functions:
    /**
     * Updates areMap, the selected areas map, wit the selected area in areaMap.
     * @param area the area to be stored.
     * @param areaType the type of the selected area to be stored.
     * @throws IllegalTypeException if the area is not of legal type.
     */
    public void storeArea(Area area, String areaType) {
        if (!areaMap.containsKey(areaType)) {
            throw new IllegalTypeException("Area type " + areaType + " cannot be stored");
        }
        else {
            areaMap.remove(areaType);
            final AreaOutputData areaOutputData = new AreaOutputData(area, false);
            areaMap.put(areaType, areaOutputData);
        }
    }

    /**
     * Stores areas of a specific type.
     * @param areaType The type of the areas (e.g., "ST" for state, "CS" for city).
     * @param areas The list of areas to store.
     * @throws IllegalTypeException if areaType is illegal.
     */
    public void storeAreas(List<Area> areas, String areaType) {
        boolean legalStore = false;
        final List<AreaListOutputData> copy = new ArrayList<>(areaCache);
        for (AreaListOutputData areaList : copy) {
            if (areaList.getAreaType().equals(areaType)) {
                areaCache.remove(areaList);
                legalStore = true;
                break;
            }
        }
        // Add the new AreaListOutputData
        areaCache.add(new AreaListOutputData(areas, areaType, false));
        if (!legalStore) {
            throw new IllegalTypeException("Area type " + areaType + " not found");
        }
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

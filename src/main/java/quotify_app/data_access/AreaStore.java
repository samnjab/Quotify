package quotify_app.data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quotify_app.data_access.exceptions.DataAccessErrorException;
import quotify_app.data_access.exceptions.IllegalTypeException;
import quotify_app.entities.regionEntities.Area;

/**
 * Area cache that stores a list of available subareas given higher level area selections
 * in the form of AreaListOutputData,
 * with associated methods to search through the data.
 */

public class AreaStore {
    private final Map<String, List<Area>> areaCache;
    private final Map<String, Area> areaMap;

    public AreaStore() {
        areaCache = new HashMap<>();
        // Setting up empty selections for each area type:
        areaCache.put("CN", new ArrayList<>());
        areaCache.put("CN", new ArrayList<>());
        areaCache.put("ST", new ArrayList<>());
        areaCache.put("CS", new ArrayList<>());
        areaCache.put("ZI", new ArrayList<>());
        // Setting up empty available areas for each area type:
        areaMap = new HashMap<>();
        areaMap.put("CN", new Area("CN"));
        areaMap.put("ST", new Area("ST"));
        areaMap.put("CS", new Area("CS"));
        areaMap.put("ZI", new Area("ZI"));
    }

    // Getter functions:
    /**
     * Returns the cached list of subareas of Type type.
     * @param type the type of cached subareas.
     * @return areaList a list of cached areas of Type type.
     * @throws IllegalTypeException when Type type does not exist.
     */

    public List<Area> getSubAreaList(String type) {
        if (areaCache.containsKey(type)) {
            return areaCache.get(type);
        }
        else {
            throw new IllegalTypeException("Area Selection of type " + type + " not found");
        }
    }

    /**
     * Returns the cached selected area of Type type.
     * @param type the type of cached selected area.
     * @return area cached selected area of Type type.
     * @throws IllegalTypeException when Type type does not exist.
     */
    public Area getSelectedArea(String type) {
        if (areaMap.containsKey(type)) {
            return areaMap.get(type);
        }
        else {
            throw new IllegalTypeException("Area type " + type + " not found");
        }
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
            areaMap.put(areaType, area);
        }
    }

    /**
     * Stores areas of a specific type.
     * @param areaType The type of the areas.
     * @param areas The list of areas to store.
     * @throws IllegalTypeException if areaType is illegal.
     */
    public void storeAreas(List<Area> areas, String areaType) {
        if (!areaCache.containsKey(areaType)) {
            throw new IllegalTypeException("List of Areas of type " + areaType + " cannot be stored");
        }
        else {
            areaCache.remove(areaType);
            areaCache.put(areaType, areas);
        }
    }

    /**
     * Fetches all areas of a specific type from areaCache.
     * @param areaType The type of the areas.
     * @return List of areas for the given type.
     */
    public List<Area> fetchAllAreasByType(String areaType) {
        return areaCache.get(areaType);
    }

    /**
     * Fetches the area matching areaType and geoIdV4 in areaCache.
     * @param areaType The type of the areas.
     * @param geoIdV4 the geoIdV4 of the area to search for.
     * @return area matching type and geoIdV4.
     * @throws DataAccessErrorException if a matching area cannot be found.
     */
    public Area findArea(String areaType, String geoIdV4) {
        for (Area area : areaCache.get(areaType)) {
            if (area.getGeoIdV4().equals(geoIdV4)) {
                return area;
            }
        }
        throw new DataAccessErrorException("area not found in AreaAccess");
    }

    /**
     * Clears all cached areas (useful for session reset).
     */
    public void clear() {
        areaCache.clear();
    }

}

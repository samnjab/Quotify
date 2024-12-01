package quotify_app.data_access;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaDataAccessInterface;

/**
 * The Data Access Object providing all the data pertaining to the areas, interacting with
 * AttomClient.
 */

public class AreaDataAccessObject implements AreaDataAccessInterface {

    private final AreaStore areaCache;

    public AreaDataAccessObject(AreaStore areaCache) {
        this.areaCache = areaCache;
    }

    // Helper methods:

    /**
     * Constructs a list of Area objects from JsonNode fetched from the API.
     * @param areas the list of subareas returned from the API in the API information format.
     * @return subAreas a list of Area objects in the format Area to be used by
     * the rest of the program.
     * */
    private List<Area> constructAreaList(JsonNode areas) {
        final List<Area> subAreas = new ArrayList<>();
        for (JsonNode areaNode : areas) {
            final String name = areaNode.has("name") ? areaNode.get("name").asText() : null;
            final String abbreviation = areaNode.has("abbreviation") ? areaNode.get("abbreviation").asText() : null;
            final Area areaObj = new Area(
                    areaNode.get("type").asText(),
                    areaNode.get("geoIdV4").asText(),
                    areaNode.get("geoId").asText(),
                    name,
                    abbreviation
            );
            subAreas.add(areaObj);
        }
        return subAreas;
    }

    // Override methods:
    /**
     * Retrieves all countries accessible by the app.
     * @return a List of countries available in the database.
     */
    @Override
    public List<Area> getCountries() {
        // replace with countries returned from ModelDataAccessObject, update the exceptions thrown accordingly.
        final Area us = new Area("CN", "CN1", "CN1", "The United States of America", "US");
        final List<Area> countries = new ArrayList<>();
        countries.add(us);
        return countries;
    }

    /**
     * Stores the area selection in cache.
     * @param area the selected area to be stored.
     */
    @Override
    public void selectArea(Area area) {
        areaCache.storeArea(area, area.getType());
    }

    /**
     * Fetches all subareas for a given parent geoIdV4.
     * @param geoIdV4 The parent geoIdV4.
     * @param type    the Type of the subarea to return.
     * @return List of subareas.
     * @throws ApiRequestException for non-200 HTTP responses.
     * @throws ClientRequestException for I/O and interruption errors.
     */
    public List<Area> getSubAreas(String geoIdV4, String type)
            throws ClientRequestException, ApiRequestException {
        final JsonNode areas = "CN1".equals(geoIdV4) && "ST".equals(type)
                ? AttomClient.fetchStates() : AttomClient.fetchSubAreas(geoIdV4, type);
        return constructAreaList(areas);
    }

    @Override
    public void cacheAreas(List<Area> areas, String type) {
        areaCache.storeAreas(areas, type);
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

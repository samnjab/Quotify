package quotify_app.usecases.landing;

import java.util.List;

import quotify_app.entities.regionEntities.Area;

/**
 * Output Data for the fetch list of areas Use Case.
 */
public class AreaListOutputData {
    private final List<Area> areas;
    private final String areaType;
    private final boolean selectionFailed;

    public AreaListOutputData(List<Area> areas, String areaType, boolean selectionFailed) {
        this.areas = areas;
        this.areaType = areaType;
        this.selectionFailed = selectionFailed;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public boolean isSelectionFailed() {
        return selectionFailed;
    }

    public String getAreaType() {
        return areaType;
    }
}

package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;

/**
 * Output Data for the Select Area usecase, holding the result of a selected area.
 */
public class AreaOutputData {
    private final Area area;
    private final boolean selectionFailed;

    public AreaOutputData(Area area, boolean selectionFailed) {
        this.area = area;
        this.selectionFailed = selectionFailed;
    }

    /**
     * Getter function for the selected area.
     * @return a Area representation of the selected area.
     */
    public Area getArea() {
        return area;
    }

    /**
     * Check function for selection status.
     * @return the selection status.
     */
    public boolean isSelectionFailed() {
        return selectionFailed;
    }
}

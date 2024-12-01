package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Region;

/**
 * Output Data for the Select Region Use Case, holding the result of a selected region.
 */
public class RegionOutputData {
    private final Region region;
    private final boolean selectionFailed;

    public RegionOutputData(Region region, boolean selectionFailed) {
        this.region = region;
        this.selectionFailed = selectionFailed;
    }

    /**
     * Getter function for the region.
     * @return a Region representation of the selected region.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Check function for selection status.
     * @return the selection status.
     */
    public boolean isSelectionFailed() {
        return selectionFailed;
    }
}

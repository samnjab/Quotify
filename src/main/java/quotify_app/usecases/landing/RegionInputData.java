package quotify_app.usecases.landing;

/**
 * Input Data for the Select Region Use Case, containing information about the selected region.
 */
public class RegionInputData {
    private final String regionName;
    private final String regionId;

    public RegionInputData(String name, String id) {
        this.regionName = name;
        this.regionId = id;
    }

    /**
     * Getter function for the selected region name.
     * @return a string representation of the region name.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Getter function for the region id.
     * @return a string representation of the selected region id.
     */
    public String getRegionId() {
        return regionId;
    }
}

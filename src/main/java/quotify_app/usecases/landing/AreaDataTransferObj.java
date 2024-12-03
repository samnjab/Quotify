package quotify_app.usecases.landing;

import quotify_app.entities.regionEntities.Area;

/**
 * Output Data for the Select Area usecase, holding fields that are to be presented.
 */
public class AreaDataTransferObj {
    private final String areaName;
    private final String areaCode;
    private final String geoIdV4;
    private final String type;
    private final boolean selectionFailed;

    public AreaDataTransferObj(Area area, boolean selectionFailed) {
        this.areaName = area.getName();
        this.areaCode = area.getNameCode();
        this.geoIdV4 = area.getGeoIdV4();
        this.type = area.getType();
        this.selectionFailed = selectionFailed;
    }

    /**
     * Getter function for the areaDTO name.
     * @return areaDTO name.
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Getter function for the areaDTO code.
     * @return areaDTO code.
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Getter function for the geoIdV4.
     * @return geoIdV4.
     */
    public String getGeoIdV4() {
        return geoIdV4;
    }

    /**
     * Getter function for the area type.
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Check function for selection status.
     * @return the selection status.
     */
    public boolean isSelectionFailed() {
        return selectionFailed;
    }

    /**
     * Produces string representation of areaDTO.
     * @return string representation of DTO.
     */
    public String displayDto() {
        String displayName = getAreaName();
        if (!"ZI".equals(type)) {
            displayName += ", " + getAreaCode();
        }
        return displayName;
    }

    /**
     * Produces a string representation of areaDTO.
     * @return a string representation of DTO.
     */
    public String toString() {
        return displayDto();
    }

}

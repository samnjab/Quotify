package quotify_app.usecases.landing;

import java.util.ArrayList;
import java.util.List;

import quotify_app.entities.regionEntities.Area;

/**
 * Output Data for the fetch available areas usecase, holding fields that are needed by the presenter.
 */
public class AreaListDataTransferObj {
    private final List<AreaDataTransferObj> areaDtoList;
    private final String areaType;
    private final boolean selectionFailed;

    public AreaListDataTransferObj(List<Area> areas, String areaType, boolean selectionFailed) {
        this.areaDtoList = constructAreaOutputList(areas);
        this.areaType = areaType;
        this.selectionFailed = selectionFailed;
    }

    // Helper methods:
    private List<AreaDataTransferObj> constructAreaOutputList(List<Area> areas) {
        final List<AreaDataTransferObj> areaOutputList = new ArrayList<>();
        for (Area area : areas) {
            areaOutputList.add(new AreaDataTransferObj(area, false));
        }
        return areaOutputList;
    }

    public List<AreaDataTransferObj> getAreaDtoList() {
        return areaDtoList;
    }

    public boolean isSelectionFailed() {
        return selectionFailed;
    }

    public String getAreaType() {
        return areaType;
    }
}

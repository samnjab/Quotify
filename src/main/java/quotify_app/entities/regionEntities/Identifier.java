package quotify_app.entities.regionEntities;

import java.util.Map;

/**
 * The representation of a property identifier in our program.
 */

public class Identifier {
    private final String attomId;
    private final Map<String, String> geoId;
    private final Map<String, String> geoIdV4;


    public Identifier(String attomId, Map<String, String> geoId, Map<String, String> geoIdV4) {
        this.attomId = attomId;
        this.geoId = geoId;
        this.geoIdV4 = geoIdV4;
    }

    public String getAttomId() {
        return attomId;
    }

    public Map<String, String> getGeoId() {
        return geoId;
    }

    public Map<String, String> getGeoIdV4() {
        return geoIdV4;
    }

    @Override
    public String toString() {
        return "attomId:" + attomId + geoId.toString() + geoIdV4.toString();
    }
}

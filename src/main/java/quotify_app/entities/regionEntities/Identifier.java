package quotify_app.entities.regionEntities;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The representation of a property identifier in our program.
 */

public class Identifier {
    private static final Set<String> REQUIRED_KEYS = Set.of("ST", "CS", "CO", "ZI", "N2");

    private final String attomId;
    private final Map<String, String> geoId;
    private final Map<String, String> geoIdV4;

    public Identifier(String attomId, Map<String, String> geoId, Map<String, String> geoIdV4) {
        this.attomId = Objects.requireNonNull(attomId, "attomId cannot be null");
        this.geoId = Objects.requireNonNull(geoId, "geoId cannot be null");
        this.geoIdV4 = validateGeoIdV4(geoIdV4);
    }

    private Map<String, String> validateGeoIdV4(Map<String, String> geoIdV4) {
        if (geoIdV4 == null) {
            throw new IllegalArgumentException("geoIdV4 cannot be null");
        }

        for (String requiredKey : REQUIRED_KEYS) {
            if (!geoIdV4.containsKey(requiredKey)) {
                throw new IllegalArgumentException("Missing required key in geoIdV4: " + requiredKey);
            }
        }
        return geoIdV4;
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

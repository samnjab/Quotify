package quotify_app.entities.regionEntities;

import java.util.Objects;

/**
 * The representation of an area in our program.
 */
public class Area {
    private final String type;
    private final String geoIdV4;
    private final String geoId;
    private final String geoKey;
    private final String name;

    // Constructor
    public Area(String type, String geoIdV4, String geoId, String geoKey, String name) {
        this.type = type;
        this.geoIdV4 = geoIdV4;
        this.geoId = geoId;
        this.geoKey = geoKey;
        this.name = name;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getGeoIdV4() {
        return geoIdV4;
    }

    public String getGeoId() {
        return geoId;
    }

    public String getGeoKey() {
        return geoKey;
    }

    public String getName() {
        return name;
    }

    // Overridden Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Reference equality
        if (o == null || getClass() != o.getClass()) return false; // Null or type mismatch
        Area area = (Area) o;
        return Objects.equals(type, area.type) &&
                Objects.equals(geoIdV4, area.geoIdV4) &&
                Objects.equals(geoId, area.geoId) &&
                Objects.equals(geoKey, area.geoKey) &&
                Objects.equals(name, area.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, geoIdV4, geoId, geoKey, name);
    }

    @Override
    public String toString() {
        return "Area{" +
                "type='" + type + '\'' +
                ", geoIdV4='" + geoIdV4 + '\'' +
                ", geoId='" + geoId + '\'' +
                ", geoKey='" + geoKey + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

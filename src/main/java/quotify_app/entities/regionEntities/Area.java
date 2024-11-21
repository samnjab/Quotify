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
    private final String nameCode;

    public Area(String type, String geoIdV4, String geoId, String geoKey, String name, String nameCode) {
        this.type = type;
        this.geoIdV4 = geoIdV4;
        this.geoId = geoId;
        this.geoKey = geoKey;
        this.name = name;
        this.nameCode = nameCode;
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

    public String getNameCode() {
        return nameCode;
    }

    // Overridden Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(geoIdV4, area.geoIdV4);

    }


    @Override
    public String toString() {
        return "Area{" +
                "type='" + type + '\'' +
                ", geoIdV4='" + geoIdV4 + '\'' +
                ", geoId='" + geoId + '\'' +
                ", geoKey='" + geoKey + '\'' +
                ", name='" + name + '\'' +
                ", nameCode='" + nameCode + '\'' +
                '}';
    }
}

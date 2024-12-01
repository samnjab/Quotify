package quotify_app.entities.regionEntities;

/**
 * The representation of an area in our program.
 */
public class Area {
    private final String type;
    private final String geoIdV4;
    private final String geoId;
    private final String name;
    private final String nameCode;

    public Area(String type, String geoIdV4, String geoId, String name, String nameCode) {
        this.type = type;
        this.geoIdV4 = geoIdV4;
        this.geoId = geoId;
        this.name = name != null ? name : "";
        this.nameCode = nameCode != null ? nameCode : "";
    }

    public Area(String type) {
        this.type = type;
        this.geoIdV4 = "";
        this.geoId = "";
        this.name = "Select a" + type;
        this.nameCode = "";
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

    public String getName() {
        return name;
    }

    public String getNameCode() {
        return nameCode;
    }

    @Override
    public String toString() {
        return "Area{" + "type='" + type + '\'' + ", geoIdV4='" + geoIdV4 + '\''
                + ", geoId='" + geoId + '\'' + ", geoKey='" + '\''
                + ", name='" + name + '\''
                + ", nameCode='" + nameCode + '\'' + '}';
    }
}

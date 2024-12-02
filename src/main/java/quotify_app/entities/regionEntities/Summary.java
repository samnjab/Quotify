package quotify_app.entities.regionEntities;

/**
 * A representation of property summary details with both string and integer values for
 * vectorization.
 */
public class Summary {

    private int propType;
    private String propTypeString;
    private int condition;
    private String conditionString;
    private int beds;
    private int baths;
    private int levels;
    private int size;
    private int yearBuilt;

    // Constructor with all fields
    public Summary(String propTypeString, int beds, int baths, String conditionString, int levels, int size, int yearBuilt) {
        setPropType(propTypeString);
        setCondition(conditionString);
        this.beds = normalizeInt(beds);
        this.baths = normalizeInt(baths);
        this.levels = normalizeInt(levels);
        this.size = normalizeInt(size);
        this.yearBuilt = normalizeInt(yearBuilt);
    }

    // Empty Summary constructor:
    public Summary() {
        this("Not found", -1, -1, "Not found", -1, -1, -1);
    }

    // Getters and Setters for propType
    public int getPropType() {
        return propType;
    }

    public String getPropTypeString() {
        return propTypeString;
    }

    public void setPropType(String propTypeStringVal) {
        this.propTypeString = normalizeString(propTypeStringVal);
        this.propType = mapPropTypeToInt(propTypeStringVal);
    }

    public void setPropType(int propTypeVal) {
        this.propType = propTypeVal;
        this.propTypeString = mapPropTypeToString(propTypeVal);
    }

    // Getters and Setters for condition
    public int getCondition() {
        return condition;
    }

    public String getConditionString() {
        return conditionString;
    }

    public void setCondition(String conditionStringVal) {
        this.conditionString = normalizeString(conditionStringVal);
        this.condition = mapConditionToInt(conditionStringVal);
    }

    public void setCondition(int conditionVal) {
        this.condition = conditionVal;
        this.conditionString = mapConditionToString(conditionVal);
    }

    // Getters for other fields
    public int getBeds() {
        return beds;
    }

    public String getBedsString() {
        return formatValue(beds);
    }

    public void setBeds(int beds) {
        this.beds = normalizeInt(beds);
    }

    public int getBaths() {
        return baths;
    }

    public String getBathsString() {
        return formatValue(baths);
    }

    public void setBaths(int baths) {
        this.baths = normalizeInt(baths);
    }

    public int getLevels() {
        return levels;
    }

    public String getLevelsString() {
        return formatValue(levels);
    }

    public void setLevels(int levels) {
        this.levels = normalizeInt(levels);
    }

    public int getSize() {
        return size;
    }

    public String getSizeString() {
        return formatValue(size);
    }

    public void setSize(int size) {
        this.size = normalizeInt(size);
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public String getYearBuiltString() {
        return formatValue(yearBuilt);
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = normalizeInt(yearBuilt);
    }

    // Helper Methods for Normalization
    private String normalizeString(String value) {
        return (value == null || value.isBlank()) ? "Not found" : value;
    }

    private int normalizeInt(int value) {
        return value >= 0 ? value : -1;
    }

    private String formatValue(int value) {
        return value == -1 ? "Not found" : String.valueOf(value);
    }

    // Mapping Methods for propType
    private int mapPropTypeToInt(String propTypeStringVal) {
        switch (propTypeStringVal.toLowerCase()) {
            case "condo":
                return 0;
            case "sfr":
                return 1;
            default:
                return -1;
        }
    }

    private String mapPropTypeToString(int propTypeVal) {
        switch (propTypeVal) {
            case 0:
                return "Condo";
            case 1:
                return "SFR";
            default:
                return "Not found";
        }
    }

    // Mapping Methods for condition
    private int mapConditionToInt(String conditionStringVal) {
        switch (conditionStringVal.toLowerCase()) {
            case "bad":
                return 0;
            case "good":
                return 1;
            default:
                return -1;
        }
    }

    private String mapConditionToString(int conditionVal) {
        switch (conditionVal) {
            case 0:
                return "Bad";
            case 1:
                return "Good";
            default:
                return "Not found";
        }
    }

    // Summary Methods
    public String getBriefSummary() {
        return propTypeString + " - " + getBedsString() + " Beds, " + getBathsString() + " Baths, " + getSizeString() + " sqft, built in " + getYearBuiltString();
    }

    public String getFullSummary() {
        return getBriefSummary() + "\nCondition: " + conditionString + "\nLevels: " + getLevelsString();
    }

    @Override
    public String toString() {
        return getFullSummary();
    }
}

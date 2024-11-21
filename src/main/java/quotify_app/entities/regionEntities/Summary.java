package quotify_app.entities.regionEntities;

/**
 * The representation of a property summary in our program.
 */

public class Summary {
    private final String propType;
    private final int beds;
    private final int baths;
    private final String condition;
    private final int levels;
    private final int size;
    private final int yearBuilt;

    // Constructor
    public Summary(String propType, int beds, int baths, String condition, int levels, int size, int yearBuilt) {
        this.propType = propType;
        this.beds = beds;
        this.baths = baths;
        this.condition = condition;
        this.levels = levels;
        this.size = size;
        this.yearBuilt = yearBuilt;
    }

    // Getters
    public String getPropType() {
        return propType;
    }

    public int getBeds() {
        return beds;
    }

    public int getBaths() {
        return baths;
    }

    public String getCondition() {
        return condition;
    }

    public int getLevels() {
        return levels;
    }

    public int getSize() {
        return size;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Returns a brief description of the property.
     * @return summary of the property.
     */
    public String getBriefSummary() {
        return propType + " - " + beds + " Beds, " + baths + " Baths, " + size + " sqft, built in " + yearBuilt;
    }

    /**
     * Returns the full description of the property.
     * @return full summary of the property.
     */
    public String getFullSummary() {
        return getBriefSummary()
                + "\nCondition: " + condition + "\nLevels: " + levels;
    }

    @Override
    public String toString() {
        return getFullSummary();
    }
}

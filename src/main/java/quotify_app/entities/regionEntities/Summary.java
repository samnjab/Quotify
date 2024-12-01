package quotify_app.entities.regionEntities;

import java.util.Map;

/**
 * The representation of a property summary in our program.
 */

public class Summary {
    public static final Map<String, Integer> PROPTYPE = Map.of(
            "Condo", 0,
            "SFR", 1
    );
    public static final Map<String, Integer> CONDITION = Map.of(
            "Bad", 0,
            "Good", 1
    );
    private final int propType;
    private final int condition;
    private final int beds;
    private final int baths;
    private final int levels;
    private final int size;
    private final int yearBuilt;

    // Constructor
    public Summary(String propType, int beds, int baths, String condition, int levels, int size, int yearBuilt) {
        final Integer propTypeValue = PROPTYPE.get(propType);
        if (propTypeValue == null) {
            throw new IllegalArgumentException("Invalid property type: " + propType);
        }
        this.propType = propTypeValue;

        this.beds = beds;
        this.baths = baths;

        final Integer conditionValue = CONDITION.get(condition);
        if (conditionValue == null) {
            throw new IllegalArgumentException("Invalid condition: " + condition);
        }
        this.condition = conditionValue;

        this.levels = levels;
        this.size = size;
        this.yearBuilt = yearBuilt;
    }

    public Summary() {
        this.propType = -1;
        this.beds = -1;
        this.baths = -1;
        this.condition = -1;
        this.levels = -1;
        this.size = -1;
        this.yearBuilt = -1;
    }

    // Getters
    public int getPropType() {
        return propType;
    }

    public int getBeds() {
        return beds;
    }

    public int getBaths() {
        return baths;
    }

    public int getCondition() {
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

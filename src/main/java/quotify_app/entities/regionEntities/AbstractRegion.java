package quotify_app.entities.regionEntities;

import java.util.Objects;

/**
 * The abstract representation of a region in our program.
 */

public abstract class AbstractRegion implements Region {
    private final String name;
    private final String type;
    private final String regionId;
    private final String code;


    public AbstractRegion(String name, String type, String regionId, String code) {
        this.name = name;
        this.type = type;
        this.regionId = regionId;
        this.code = code;

    }

    @Override
    public String getRegionName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getRegionId() {
        return regionId;
    }

    @Override
    public String getCode() {
        return code;
    }


    @Override
    public String toString() {
        return type + ": " + name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractRegion that = (AbstractRegion) o;
        return Objects.equals(name, that.name)
                && Objects.equals(type, that.type)
                && Objects.equals(regionId, that.regionId)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, regionId, code);
    }
}
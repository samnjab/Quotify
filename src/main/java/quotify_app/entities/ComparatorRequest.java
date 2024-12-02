package quotify_app.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that specifies the format a request should take for the property comparison model.
 */
public final class ComparatorRequest {
    private final int numBeds;
    private final int numBaths;
    private final double propertySize;
    private final String geoId;
    private final String propertyType;

    @JsonProperty("year_built")
    private final int yearBuilt;

    @JsonProperty("zip_code")
    private final String zipCode;

    public ComparatorRequest(final ComparatorRequestBuilder builder) {
        this.numBeds = builder.getNumBeds();
        this.numBaths = builder.getNumBaths();
        this.propertySize = builder.getPropertySize();
        this.geoId = builder.getGeoId();
        this.propertyType = builder.getPropertyType();
        this.yearBuilt = builder.getYearBuilt();
        this.zipCode = builder.getZipCode();
    }

    // Getters for all fields
    public int getNumBeds() {
        return numBeds;
    }

    public int getNumBaths() {
        return numBaths;
    }

    public double getPropertySize() {
        return propertySize;
    }

    public String getGeoId() {
        return geoId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparatorRequest that = (ComparatorRequest) o;
        return numBeds == that.numBeds &&
                numBaths == that.numBaths &&
                Double.compare(that.propertySize, propertySize) == 0 &&
                yearBuilt == that.yearBuilt &&
                Objects.equals(geoId, that.geoId) &&
                Objects.equals(propertyType, that.propertyType) &&
                Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBeds, numBaths, propertySize, geoId, propertyType, yearBuilt, zipCode);
    }

    /**
     * A factory method for the builder.
     * @return the builder object for the ComparatorRequest
     */
    public static ComparatorRequestBuilder requestBuilder() {
        return new ComparatorRequestBuilder();
    }
}

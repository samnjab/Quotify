package quotify_app.entities;

/**
 * A builder for constructing instances of {@link ComparatorRequest}.
 */
public final class ComparatorRequestBuilder {
    private int numBeds;
    private int numBaths;
    private double propertySize;
    private String geoId;
    private String propertyType;
    private int yearBuilt;
    private String zipCode;

    public ComparatorRequestBuilder numBeds(final int numBeds) {
        this.numBeds = numBeds;
        return this;
    }

    public ComparatorRequestBuilder numBaths(final int numBaths) {
        this.numBaths = numBaths;
        return this;
    }

    public ComparatorRequestBuilder propertySize(final double propertySize) {
        this.propertySize = propertySize;
        return this;
    }

    public ComparatorRequestBuilder geoId(final String geoId) {
        this.geoId = geoId;
        return this;
    }

    public ComparatorRequestBuilder propertyType(final String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public ComparatorRequestBuilder yearBuilt(final int yearBuilt) {
        this.yearBuilt = yearBuilt;
        return this;
    }

    public ComparatorRequestBuilder zipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     * Builds a {@link ComparatorRequest} instance using the values provided.
     *
     * @return a fully constructed {@link ComparatorRequest} object
     */
    public ComparatorRequest build() {
        return new ComparatorRequest(this);
    }

    // Getter methods for each field

    int getNumBeds() {
        return numBeds;
    }

    int getNumBaths() {
        return numBaths;
    }

    double getPropertySize() {
        return propertySize;
    }

    String getGeoId() {
        return geoId;
    }

    String getPropertyType() {
        return propertyType;
    }

    int getYearBuilt() {
        return yearBuilt;
    }

    String getZipCode() {
        return zipCode;
    }
}

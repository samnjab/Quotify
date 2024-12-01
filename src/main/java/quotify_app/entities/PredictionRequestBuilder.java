package quotify_app.entities;

/**
 * A builder for constructing instances of {@link PredictionRequest}.
 */
public final class PredictionRequestBuilder {
    private int beds;
    private int baths;
    private double universalsize;
    private String geoidv4;
    private int propclass;
    private int yearbuilt;
    private int monthOffset;
    private int propertyage;
    private int year;
    private int month;

    /**
     * Initializes the number of beds for PredictionRequest Objects.
     * @param numbeds -- the number of beds in this object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder beds(final int numbeds) {
        this.beds = numbeds;
        return this;
    }

    /**
     * Initializes the number of baths for PredictionRequest Objects.
     * @param numbaths -- the number of beds in this object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder baths(final int numbaths) {
        this.baths = numbaths;
        return this;
    }

    /**
     * Initializes the universal for PredictionRequest Objects.
     * @param univsize -- the universalsize of this object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder universalsize(final double univsize) {
        this.universalsize = univsize;
        return this;
    }

    /**
     * Initializes the geoid for this PredictionRequest Object.
     * @param geoidv4init -- the geoid to initialize the object with.
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder geoidv4(final String geoidv4init) {
        this.geoidv4 = geoidv4init;
        return this;
    }

    /**
     * Initializes the propclass for PredictionRequest Objects.
     * @param propclassinit -- the propclass for this object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder propclass(final int propclassinit) {
        this.propclass = propclassinit;
        return this;
    }

    /**
     * Initializes the yearbuilt for PredictionRequest Objects.
     * @param yearbuiltinit -- the year built for this predictionrequest object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder yearbuilt(final int yearbuiltinit) {
        this.yearbuilt = yearbuiltinit;
        return this;
    }

    /**
     * Initializes the prediction offset for PredictionRequest Objects.
     * @param monthOffsetinit -- the month at which we want to predict.
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder monthOffset(final int monthOffsetinit) {
        this.monthOffset = monthOffsetinit;
        return this;
    }

    /**
     * Initializes the property age for PredictionRequest Objects.
     * @param propertyageinit -- the age of this property.
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder propertyage(final int propertyageinit) {
        this.propertyage = propertyageinit;
        return this;
    }

    /**
     * Initializes the year for PredictionRequest Objects.
     * @param yearinit -- the year in which we're making a prediction.
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder year(final int yearinit) {
        this.year = yearinit;
        return this;
    }

    /**
     * Initializes the month for PredictionRequest Objects.
     * @param monthinit -- the number of beds in this object
     * @return -- a pointer to this builder.
     */
    public PredictionRequestBuilder month(final int monthinit) {
        this.month = monthinit;
        return this;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getBeds() {
        return beds;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getBaths() {
        return baths;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    double getUniversalsize() {
        return universalsize;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    String getGeoidv4() {
        return geoidv4;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getPropclass() {
        return propclass;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getYearbuilt() {
        return yearbuilt;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getMonthOffset() {
        return monthOffset;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getPropertyage() {
        return propertyage;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getYear() {
        return year;
    }

    /**
     * Getter function for bed property.
     * @return integer representing the number of beds.
     */
    int getMonth() {
        return month;
    }

    /**
     * Builds a {@link PredictionRequest} instance using the values provided.
     *
     * @return a fully constructed {@link PredictionRequest} object
     */
    public PredictionRequest build() {
        return new PredictionRequest(this);
    }
}

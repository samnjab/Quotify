package quotify_app.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that specifies the format a request should take for the prediction model.
 */
public final class PredictionRequest {
    private final int beds;
    private final int baths;
    private final double universalsize;
    private final String geoidv4;
    private final int propclass;
    private final int yearbuilt;

    @JsonProperty("month_offset")
    private final int monthOffset;
    private final int propertyage;
    private final int year;
    private final int month;

    public PredictionRequest(final PredictionRequestBuilder builder) {
        this.beds = builder.getBeds();
        this.baths = builder.getBaths();
        this.universalsize = builder.getUniversalsize();
        this.geoidv4 = builder.getGeoidv4();
        this.propclass = builder.getPropclass();
        this.yearbuilt = builder.getYearbuilt();
        this.monthOffset = builder.getMonthOffset();
        this.propertyage = builder.getPropertyage();
        this.year = builder.getYear();
        this.month = builder.getMonth();
    }

    // Getters for all fields
    public int getBeds() {
        return beds;
    }

    public int getBaths() {
        return baths;
    }

    public double getUniversalsize() {
        return universalsize;
    }

    public String getGeoidv4() {
        return geoidv4;
    }

    public int getPropclass() {
        return propclass;
    }

    public int getYearbuilt() {
        return yearbuilt;
    }

    public int getMonthOffset() {
        return monthOffset;
    }

    public int getPropertyage() {
        return propertyage;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        final boolean result;
        if (this == o) {
            result = true;
        }
        else if (o == null || getClass() != o.getClass()) {
            result = false;
        }
        else {
            final PredictionRequest that = (PredictionRequest) o;
            result = hasEqualBasicFields(that) && hasEqualDerivedFields(that);
        }
        return result;
    }

    private boolean hasEqualBasicFields(final PredictionRequest that) {
        return beds == that.beds
                && baths == that.baths
                && Double.compare(universalsize, that.universalsize) == 0
                && propclass == that.propclass
                && Objects.equals(geoidv4, that.geoidv4);
    }

    private boolean hasEqualDerivedFields(final PredictionRequest that) {
        return yearbuilt == that.yearbuilt
                && monthOffset == that.monthOffset
                && propertyage == that.propertyage
                && year == that.year
                && month == that.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                beds,
                baths,
                universalsize,
                geoidv4,
                propclass,
                yearbuilt,
                monthOffset,
                propertyage,
                year,
                month
        );
    }

    /**
     * A factory method for the builder.
     * @return the builder object for the PredictionRequest
     */
    public static PredictionRequestBuilder requestBuilder() {
        return new PredictionRequestBuilder();
    }
}

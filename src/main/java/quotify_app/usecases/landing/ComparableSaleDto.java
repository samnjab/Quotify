package quotify_app.usecases.landing;

import java.time.LocalDate;

import quotify_app.entities.regionEntities.Property;

/**
 * Data Transfer Object (DTO) representing a property comparable sale.
 * This class encapsulates the property details, sale price, and sale date
 * for properties used in comparison analysis.
 */
public class ComparableSaleDto {
    private final Property property;
    private final double salePrice;
    private final LocalDate saleDate;

    /**
     * Constructs a new ComparableSaleDTO with the specified property, sale price, and sale date.
     *
     * @param property  The property associated with this sale.
     * @param salePrice The sale price of the property.
     * @param saleDate  The date the property was sold.
     */
    public ComparableSaleDto(Property property, double salePrice, LocalDate saleDate) {
        this.property = property;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
    }

    /**
     * Gets the property associated with this sale.
     *
     * @return The property object.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Gets the sale price of the property.
     *
     * @return The sale price as a double.
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Gets the date the property was sold.
     *
     * @return The sale date as a LocalDate.
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }
}

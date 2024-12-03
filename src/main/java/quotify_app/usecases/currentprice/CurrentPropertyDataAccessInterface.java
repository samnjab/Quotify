package quotify_app.usecases.currentprice;

import quotify_app.entities.regionEntities.Property;

/**
 * Interface for accessing property data.
 * Defines methods for retrieving the current property.
 */
public interface CurrentPropertyDataAccessInterface {

    /**
     * Gets the current property selected in the application.
     *
     * @return The current Property, or null if none is selected.
     */
    Property getCurrentProperty();
}

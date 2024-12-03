package quotify_app.usecases.future_pricing;

import quotify_app.entities.regionEntities.Property;

/**
 * Do stuff.
 */
public interface FuturePropertyDataAccessInterface {

    /**
     * Returns the cached property.
     * @return Property current property.
     */
    Property getCurrentProperty();
}

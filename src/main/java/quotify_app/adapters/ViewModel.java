package quotify_app.adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel for our CA implementation.
 * This class uses PropertyChangeSupport to manage property change events,
 * enabling views to listen for updates in the state.
 *
 * @param <T> The type of state object contained in the model.
 */
public class ViewModel<T> {
    private final String viewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private T state;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        this.state = state;
    }

    /**
     * Fires a property change event to notify listeners of state changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property change event to notify listeners of state changes with a certain property.
     * @param propertyName the property related to the property change.
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

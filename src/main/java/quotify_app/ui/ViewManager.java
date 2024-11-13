package quotify_app.ui;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import quotify_app.adapters.ViewManagerModel;

/**
 * Manages transitions between different views.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel viewPanel;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(JPanel viewPanel, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.viewPanel = viewPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final String viewName = (String) evt.getNewValue();
            System.out.println(viewName);
            cardLayout.show(viewPanel, viewName);
        }
    }
}

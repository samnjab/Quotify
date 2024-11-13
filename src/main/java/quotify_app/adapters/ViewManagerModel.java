package quotify_app.adapters;

/**
 * Model for the View Manager. Its state is the name of the currently active view.
 */
public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}

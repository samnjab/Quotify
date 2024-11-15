package quotify_app.ui;

import quotify_app.adapters.comparator.ComparatorController;
import quotify_app.adapters.comparator.ComparatorViewModel;
import quotify_app.adapters.function.FunctionController;
import quotify_app.adapters.function.FunctionViewModel;
import javax.swing.JPanel;

/**
 * The view for Comparing with similar houses.
 */
public class ComparatorView extends JPanel {
    private final String viewName = "comparator";
    private ComparatorController comparatorController;
    private final ComparatorViewModel comparatorViewModel;

    public ComparatorView(ComparatorViewModel comparatorViewModel) {
        this.comparatorViewModel = comparatorViewModel;
    }
}

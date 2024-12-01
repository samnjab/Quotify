package quotify_app.adapters.function;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the Function View.
 */
public class FunctionViewModel extends ViewModel<String> {

    // Labels for UI elements in the function view
    public static final String TITLE_LABEL = "Choose!";
    public static final String ESTIMATE_PRICE_LABEL = "Estimate Price";
    public static final String COMPARE_LABEL = "Compare Similar Properties";

    public FunctionViewModel() {
        super("function");
    }

}

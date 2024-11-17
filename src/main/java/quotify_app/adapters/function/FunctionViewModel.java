package quotify_app.adapters.function;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the Function View.
 */
public class FunctionViewModel extends ViewModel<FunctionState> {

    // Labels for UI elements in the login view
    public static final String TITLE_LABEL = "Choose!";
    public static final String ESTIMATE_PRICE_LABEL = "Estimate Current";
    public static final String COMPARE_LABEL = "Compare with similar property";

    public FunctionViewModel() {
        super("function");
        setState(new FunctionState());
    }

}

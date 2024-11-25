package quotify_app.adapters.comparator;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the Comparator View.
 */
public class ComparatorViewModel extends ViewModel<ComparatorState> {

    // Labels for UI elements in the login view
    public static final String TITLE_LABEL = "Property Comparator";
    public static final String HOUSE1_LABEL = "Property 1";
    public static final String HOUSE2_LABEL = "Property 2";
    public static final String HOUSE3_LABEL = "Property 3";
    public static final String USER_PROFILE_BUTTON_LABEL = "User Profile";
    public static final String NEW_HOUSE_BUTTON_LABEL = "New House";
    public static final String ESTIMATE_PRICE_BUTTON_LABEL = "Estimate Price";

    public ComparatorViewModel() {
        super("comparator");
        setState(new ComparatorState());
    }
}
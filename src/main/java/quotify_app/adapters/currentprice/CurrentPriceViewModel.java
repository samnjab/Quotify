package quotify_app.adapters.currentprice;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the CurrentPrice View.
 * Manages the state and labels for the CurrentPriceView.
 */
public class CurrentPriceViewModel extends ViewModel<CurrentPriceState> {

    // Labels for UI elements in the CurrentPrice view
    public static final String TITLE_LABEL = "Your House Is Currently Going For ";
    public static final String FUTURE_PRICING_LABEL = "See future pricing";
    public static final String FUTURE_BUTTON_LABEL = "Future";
    public static final String COMPARE_PROPERTY_BUTTON_LABEL = "Compare Property";
    public static final String LANDING_PAGE_LABEL = "Home";
    public static final String USER_PROFILE_BUTTON_LABEL = "User Profile";

    /**
     * Initializes the CurrentPriceViewModel with default state.
     */
    public CurrentPriceViewModel() {
        super("current price");
        setState(new CurrentPriceState());
    }
}

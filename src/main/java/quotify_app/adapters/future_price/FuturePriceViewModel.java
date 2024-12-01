package quotify_app.adapters.future_price;

import quotify_app.adapters.ViewModel;

/**
 * The ViewModel for the FuturePrice View.
 * Manages the state and labels for the FuturePriceView.
 */
public class FuturePriceViewModel extends ViewModel<FuturePriceState> {

    // Labels for UI elements in the CurrentPrice view
    public static final String TITLE_LABEL = "Your Property's Estimated Evaluation Over Time ";
    public static final String CURRENT_PRICING_BUTTON_LABEL = "Check Out Current Pricing";
    public static final String COMPARE_PROPERTY_BUTTON_LABEL = "Compare Property";
    public static final String LANDING_PAGE_BUTTOM_LABEL = "Home";
    public static final String USER_PROFILE_BUTTON_LABEL = "User Profile";

    /**
     * Initializes the FuturePriceViewModel with default state.
     */
    public FuturePriceViewModel() {
        super("future price");
        setState(new FuturePriceState());
    }
}

package quotify_app.usecases.future_pricing;

import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.regionEntities.Property;

/**
 * The Interactor for the Future Price use case.
 * Implements the business logic and interacts with the Presenter.
 */
public class FuturePriceInteractor implements FuturePriceInputBoundary {

    private final FuturePriceOutputBoundary futurePricePresenter;
    private final FuturePredictionDataAccessInterface predictionDataAccessObject;
    private final FuturePropertyDataAccessInterface propertyDataAccessObject;

    /**
     * Initializes the FuturePriceInteractor with the given Presenter.
     *
     * @param futurePricePresenter the Presenter for the Future Price use case.
     * @param predictionDataAccessObject prediction DAO to get future price data.
     * @param propertyDataAccessObject gets the property to find data on.
     */
    public FuturePriceInteractor(FuturePriceOutputBoundary futurePricePresenter,
                                 FuturePredictionDataAccessInterface predictionDataAccessObject,
                                 FuturePropertyDataAccessInterface propertyDataAccessObject) {
        this.futurePricePresenter = futurePricePresenter;
        this.predictionDataAccessObject = predictionDataAccessObject;
        this.propertyDataAccessObject = propertyDataAccessObject;
    }

    @Override
    public void goToLogin() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToLogin();
    }

    @Override
    public void goToSignup() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToSignup();
    }

    @Override
    public void goToCurrentPricing() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToCurrentPricing();
    }

    @Override
    public void goToComparator() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToComparator();
    }

    @Override
    public void checkLoginStatus() {
        // Instruct the presenter to update the login status
        futurePricePresenter.updateLoginStatus();
    }

    @Override
    public void goToUserProfile() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToUserProfile();
    }

    @Override
    public void goToLandingPage() {
        // Perform any necessary business logic here
        futurePricePresenter.presentGoToLandingPage();
    }

    @Override
    public void fetchFuturePrices() {
        try {
            // Get the current property from the property data access
            final Property property = propertyDataAccessObject.getCurrentProperty();
            if (property == null) {
                // Handle case where there is no current property
                futurePricePresenter.presentPredictionError("No property selected.");
                return;
            }
            // Get the predicted prices from the prediction data access
            final double[] predictedPrice = predictionDataAccessObject.getFuturePricePredictions(property);
            // Present the predicted price
            futurePricePresenter.presentFuturePrices(predictedPrice);
        }
        catch (PredictionClientException exception) {
            // Handle exceptions, inform presenter of error
            futurePricePresenter.presentPredictionError("Failed to fetch prediction: " + exception.getMessage());
        }
    }
}

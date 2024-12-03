package quotify_app.usecases.currentprice;

import quotify_app.entities.regionEntities.Property;

/**
 * The Interactor for the Current Price use case.
 * Implements the business logic and interacts with the Presenter.
 */
public class CurrentPriceInteractor implements CurrentPriceInputBoundary {

    private final CurrentPriceOutputBoundary currentPricePresenter;
    private final PredictionDataAccessInterface predictionDataAccess;
    private final CurrentPropertyDataAccessInterface propertyDataAccess;

    /**
     * Initializes the CurrentPriceInteractor with the given Presenter and Data Access Interfaces.
     *
     * @param currentPricePresenter The Presenter for the Current Price use case.
     * @param predictionDataAccess  The Data Access Interface for predictions.
     * @param propertyDataAccess    The Data Access Interface for property data.
     */
    public CurrentPriceInteractor(CurrentPriceOutputBoundary currentPricePresenter,
                                  PredictionDataAccessInterface predictionDataAccess,
                                  CurrentPropertyDataAccessInterface propertyDataAccess) {
        this.currentPricePresenter = currentPricePresenter;
        this.predictionDataAccess = predictionDataAccess;
        this.propertyDataAccess = propertyDataAccess;
    }

    @Override
    public void goToLogin() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToLogin();
    }

    @Override
    public void goToSignup() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToSignup();
    }

    @Override
    public void goToFuturePricing() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToFuturePricing();
    }

    @Override
    public void goToComparator() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToComparator();
    }

    @Override
    public void checkLoginStatus() {
        // Instruct the presenter to update the login status
        currentPricePresenter.updateLoginStatus();
    }

    @Override
    public void goToUserProfile() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToUserProfile();
    }

    @Override
    public void goToLandingPage() {
        // Perform any necessary business logic here
        currentPricePresenter.presentGoToLandingPage();
    }

    @Override
    public void fetchCurrentPrice() {
        try {
            // Get the current property from the property data access
            final Property property = propertyDataAccess.getCurrentProperty();

            if (property == null) {
                // Handle case where there is no current property
                currentPricePresenter.presentPredictionError("No property selected.");
                return;
            }

            // Get the predicted price from the prediction data access
            final double predictedPrice = predictionDataAccess.getCurrentPricePrediction(property);

            // Present the predicted price
            currentPricePresenter.presentCurrentPrice(predictedPrice);

        }
        catch (Exception e) {
            // Handle exceptions, inform presenter of error
            currentPricePresenter.presentPredictionError("Failed to fetch prediction: " + e.getMessage());
        }
    }

}

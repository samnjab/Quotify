package usecases.currentprice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.currentprice.CurrentPriceInteractor;
import quotify_app.usecases.currentprice.CurrentPriceOutputBoundary;
import quotify_app.usecases.currentprice.CurrentPropertyDataAccessInterface;
import quotify_app.usecases.currentprice.PredictionDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link CurrentPriceInteractor}.
 */
public class CurrentPriceInteractorTest {

    private CurrentPriceInteractor interactor;
    private MockCurrentPriceOutputBoundary presenter;
    private MockPredictionDataAccess predictionDataAccess;
    private MockCurrentPropertyDataAccess propertyDataAccess;

    @BeforeEach
    public void setUp() {
        presenter = new MockCurrentPriceOutputBoundary();
        predictionDataAccess = new MockPredictionDataAccess();
        propertyDataAccess = new MockCurrentPropertyDataAccess();
        interactor = new CurrentPriceInteractor(presenter, predictionDataAccess, propertyDataAccess);
    }

    @Test
    public void testGoToLogin() {
        // Act
        interactor.goToLogin();

        // Assert
        assertTrue(presenter.presentGoToLoginCalled, "presentGoToLogin should be called.");
    }

    @Test
    public void testGoToSignup() {
        // Act
        interactor.goToSignup();

        // Assert
        assertTrue(presenter.presentGoToSignupCalled, "presentGoToSignup should be called.");
    }

    @Test
    public void testGoToFuturePricing() {
        // Act
        interactor.goToFuturePricing();

        // Assert
        assertTrue(presenter.presentGoToFuturePricingCalled, "presentGoToFuturePricing should be called.");
    }

    @Test
    public void testGoToComparator() {
        // Act
        interactor.goToComparator();

        // Assert
        assertTrue(presenter.presentGoToComparatorCalled, "presentGoToComparator should be called.");
    }

    @Test
    public void testCheckLoginStatus() {
        // Act
        interactor.checkLoginStatus();

        // Assert
        assertTrue(presenter.updateLoginStatusCalled, "updateLoginStatus should be called.");
    }

    @Test
    public void testGoToUserProfile() {
        // Act
        interactor.goToUserProfile();

        // Assert
        assertTrue(presenter.presentGoToUserProfileCalled, "presentGoToUserProfile should be called.");
    }

    @Test
    public void testGoToLandingPage() {
        // Act
        interactor.goToLandingPage();

        // Assert
        assertTrue(presenter.presentGoToLandingPageCalled, "presentGoToLandingPage should be called.");
    }

    @Test
    public void testFetchCurrentPriceSuccess() {
        // Arrange
        Property mockProperty = new Property(); // Create a mock property
        propertyDataAccess.setProperty(mockProperty);
        predictionDataAccess.setPredictedPrice(500000.0);

        // Act
        interactor.fetchCurrentPrice();

        // Assert
        assertTrue(presenter.presentCurrentPriceCalled, "presentCurrentPrice should be called.");
        assertEquals(500000.0, presenter.pricePresented, 0.001, "The presented price should be 500000.0.");
    }

    @Test
    public void testFetchCurrentPriceNoProperty() {
        // Arrange
        propertyDataAccess.setProperty(null);

        // Act
        interactor.fetchCurrentPrice();

        // Assert
        assertTrue(presenter.presentPredictionErrorCalled, "presentPredictionError should be called.");
        assertEquals("No property selected.", presenter.errorMessage, "Error message should indicate no property selected.");
    }

    @Test
    public void testFetchCurrentPricePredictionException() {
        // Arrange
        Property mockProperty = new Property(); // Create a mock property
        propertyDataAccess.setProperty(mockProperty);
        predictionDataAccess.setThrowException(true);

        // Act
        interactor.fetchCurrentPrice();

        // Assert
        assertTrue(presenter.presentPredictionErrorCalled, "presentPredictionError should be called.");
        assertEquals("Failed to fetch prediction: Prediction service error", presenter.errorMessage, "Error message should reflect the exception.");
    }

    /**
     * Mock implementation of {@link CurrentPriceOutputBoundary} for testing purposes.
     */
    private static class MockCurrentPriceOutputBoundary implements CurrentPriceOutputBoundary {
        boolean presentGoToLoginCalled = false;
        boolean presentGoToSignupCalled = false;
        boolean presentGoToFuturePricingCalled = false;
        boolean presentGoToComparatorCalled = false;
        boolean updateLoginStatusCalled = false;
        boolean presentGoToUserProfileCalled = false;
        boolean presentGoToLandingPageCalled = false;
        boolean presentCurrentPriceCalled = false;
        boolean presentPredictionErrorCalled = false;
        double pricePresented = 0.0;
        String errorMessage = null;

        @Override
        public void presentGoToLogin() {
            presentGoToLoginCalled = true;
        }

        @Override
        public void presentGoToSignup() {
            presentGoToSignupCalled = true;
        }

        @Override
        public void presentGoToFuturePricing() {
            presentGoToFuturePricingCalled = true;
        }

        @Override
        public void presentGoToComparator() {
            presentGoToComparatorCalled = true;
        }

        @Override
        public void updateLoginStatus() {
            updateLoginStatusCalled = true;
        }

        @Override
        public void presentGoToUserProfile() {
            presentGoToUserProfileCalled = true;
        }

        @Override
        public void presentGoToLandingPage() {
            presentGoToLandingPageCalled = true;
        }

        @Override
        public void presentCurrentPrice(double price) {
            presentCurrentPriceCalled = true;
            pricePresented = price;
        }

        @Override
        public void presentPredictionError(String message) {
            presentPredictionErrorCalled = true;
            errorMessage = message;
        }
    }

    /**
     * Mock implementation of {@link PredictionDataAccessInterface} for testing purposes.
     */
    private static class MockPredictionDataAccess implements PredictionDataAccessInterface {
        private double predictedPrice = 0.0;
        private boolean throwException = false;

        public void setPredictedPrice(double predictedPrice) {
            this.predictedPrice = predictedPrice;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public double getCurrentPricePrediction(Property property) {
            if (throwException) {
                throw new RuntimeException("Prediction service error");
            }
            return predictedPrice;
        }
    }

    /**
     * Mock implementation of {@link CurrentPropertyDataAccessInterface} for testing purposes.
     */
    private static class MockCurrentPropertyDataAccess implements CurrentPropertyDataAccessInterface {
        private Property property = null;

        public void setProperty(Property property) {
            this.property = property;
        }

        @Override
        public Property getCurrentProperty() {
            return property;
        }
    }
}

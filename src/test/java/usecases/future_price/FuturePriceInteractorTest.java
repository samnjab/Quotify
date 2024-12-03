package usecases.future_price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.regionEntities.Property;
import quotify_app.usecases.future_pricing.FuturePredictionDataAccessInterface;
import quotify_app.usecases.future_pricing.FuturePriceInteractor;
import quotify_app.usecases.future_pricing.FuturePriceOutputBoundary;
import quotify_app.usecases.future_pricing.FuturePropertyDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class FuturePriceInteractorTest {

    private TestPresenter testPresenter;
    private TestPredictionDAO testPredictionDAO;
    private TestPropertyDAO testPropertyDAO;
    private FuturePriceInteractor interactor;

    @BeforeEach
    void setUp() {
        testPresenter = new TestPresenter();
        testPredictionDAO = new TestPredictionDAO();
        testPropertyDAO = new TestPropertyDAO();
        interactor = new FuturePriceInteractor(testPresenter, testPredictionDAO, testPropertyDAO);
    }

    @Test
    void testFetchFuturePrices_Success() {
        testPropertyDAO.setCurrentProperty(new Property());
        testPredictionDAO.setFuturePrices(new double[]{500000, 510000, 520000});

        interactor.fetchFuturePrices();

        assertArrayEquals(new double[]{500000, 510000, 520000}, testPresenter.getFuturePrices());
        assertNull(testPresenter.getErrorMessage());
    }

    @Test
    void testFetchFuturePrices_NoPropertySelected() {
        testPropertyDAO.setCurrentProperty(null);

        interactor.fetchFuturePrices();

        assertEquals("No property selected.", testPresenter.getErrorMessage());
        assertNull(testPresenter.getFuturePrices());
    }

    @Test
    void testFetchFuturePrices_PredictionClientException() {
        testPropertyDAO.setCurrentProperty(new Property());
        testPredictionDAO.setThrowException(true);

        interactor.fetchFuturePrices();

        assertEquals("Failed to fetch prediction: Prediction service unavailable", testPresenter.getErrorMessage());
        assertNull(testPresenter.getFuturePrices());
    }

    @Test
    void testGoToLogin() {
        interactor.goToLogin();
        assertTrue(testPresenter.isGoToLoginCalled());
    }

    @Test
    void testGoToSignup() {
        interactor.goToSignup();
        assertTrue(testPresenter.isGoToSignupCalled());
    }

    @Test
    void testGoToCurrentPricing() {
        interactor.goToCurrentPricing();
        assertTrue(testPresenter.isGoToCurrentPricingCalled());
    }

    @Test
    void testGoToComparator() {
        interactor.goToComparator();
        assertTrue(testPresenter.isGoToComparatorCalled());
    }

    @Test
    void testCheckLoginStatus() {
        interactor.checkLoginStatus();
        assertTrue(testPresenter.isUpdateLoginStatusCalled());
    }

    @Test
    void testGoToUserProfile() {
        interactor.goToUserProfile();
        assertTrue(testPresenter.isGoToUserProfileCalled());
    }

    @Test
    void testGoToLandingPage() {
        interactor.goToLandingPage();
        assertTrue(testPresenter.isGoToLandingPageCalled());
    }

    // Stub classes
    static class TestPresenter implements FuturePriceOutputBoundary {
        private double[] futurePrices;
        private String errorMessage;
        private boolean goToLoginCalled;
        private boolean goToSignupCalled;
        private boolean goToCurrentPricingCalled;
        private boolean goToComparatorCalled;
        private boolean updateLoginStatusCalled;
        private boolean goToUserProfileCalled;
        private boolean goToLandingPageCalled;

        @Override
        public void presentFuturePrices(double[] futurePrices) {
            this.futurePrices = futurePrices;
        }

        @Override
        public void presentPredictionError(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void presentGoToLogin() {
            goToLoginCalled = true;
        }

        @Override
        public void presentGoToSignup() {
            goToSignupCalled = true;
        }

        @Override
        public void presentGoToCurrentPricing() {
            goToCurrentPricingCalled = true;
        }

        @Override
        public void presentGoToComparator() {
            goToComparatorCalled = true;
        }

        @Override
        public void updateLoginStatus() {
            updateLoginStatusCalled = true;
        }

        @Override
        public void presentGoToUserProfile() {
            goToUserProfileCalled = true;
        }

        @Override
        public void presentGoToLandingPage() {
            goToLandingPageCalled = true;
        }

        public double[] getFuturePrices() {
            return futurePrices;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isGoToLoginCalled() {
            return goToLoginCalled;
        }

        public boolean isGoToSignupCalled() {
            return goToSignupCalled;
        }

        public boolean isGoToCurrentPricingCalled() {
            return goToCurrentPricingCalled;
        }

        public boolean isGoToComparatorCalled() {
            return goToComparatorCalled;
        }

        public boolean isUpdateLoginStatusCalled() {
            return updateLoginStatusCalled;
        }

        public boolean isGoToUserProfileCalled() {
            return goToUserProfileCalled;
        }

        public boolean isGoToLandingPageCalled() {
            return goToLandingPageCalled;
        }
    }

    static class TestPredictionDAO implements FuturePredictionDataAccessInterface {
        private double[] futurePrices;
        private boolean throwException;

        public void setFuturePrices(double[] futurePrices) {
            this.futurePrices = futurePrices;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public double[] getFuturePricePredictions(Property property) throws PredictionClientException {
            if (throwException) {
                throw new PredictionClientException("Prediction service unavailable");
            }
            return futurePrices;
        }
    }

    static class TestPropertyDAO implements FuturePropertyDataAccessInterface {
        private Property currentProperty;

        public void setCurrentProperty(Property currentProperty) {
            this.currentProperty = currentProperty;
        }

        @Override
        public Property getCurrentProperty() {
            return currentProperty;
        }
    }
}

package usecases.currentprice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.adapters.currentprice.CurrentPriceController;
import quotify_app.usecases.currentprice.CurrentPriceInputBoundary;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link CurrentPriceController}.
 */
public class CurrentPriceControllerTest {

    private CurrentPriceController controller;
    private MockCurrentPriceInputBoundary interactor;

    @BeforeEach
    public void setUp() {
        interactor = new MockCurrentPriceInputBoundary();
        controller = new CurrentPriceController(interactor);
    }

    @Test
    public void testGoToLogin() {
        // Act
        controller.goToLogin();

        // Assert
        assertTrue(interactor.goToLoginCalled, "goToLogin should be called on interactor.");
    }

    @Test
    public void testGoToSignup() {
        // Act
        controller.goToSignup();

        // Assert
        assertTrue(interactor.goToSignupCalled, "goToSignup should be called on interactor.");
    }

    @Test
    public void testGoToFuturePricing() {
        // Act
        controller.goToFuturePricing();

        // Assert
        assertTrue(interactor.goToFuturePricingCalled, "goToFuturePricing should be called on interactor.");
    }

    @Test
    public void testGoToComparator() {
        // Act
        controller.goToComparator();

        // Assert
        assertTrue(interactor.goToComparatorCalled, "goToComparator should be called on interactor.");
    }

    @Test
    public void testCheckLoginStatus() {
        // Act
        controller.checkLoginStatus();

        // Assert
        assertTrue(interactor.checkLoginStatusCalled, "checkLoginStatus should be called on interactor.");
    }

    @Test
    public void testGoToUserProfile() {
        // Act
        controller.goToUserProfile();

        // Assert
        assertTrue(interactor.goToUserProfileCalled, "goToUserProfile should be called on interactor.");
    }

    @Test
    public void testGoToLandingPage() {
        // Act
        controller.goToLandingPage();

        // Assert
        assertTrue(interactor.goToLandingPageCalled, "goToLandingPage should be called on interactor.");
    }

    @Test
    public void testFetchCurrentPrice() {
        // Act
        controller.fetchCurrentPrice();

        // Assert
        assertTrue(interactor.fetchCurrentPriceCalled, "fetchCurrentPrice should be called on interactor.");
    }

    /**
     * Mock implementation of {@link CurrentPriceInputBoundary} for testing purposes.
     */
    private static class MockCurrentPriceInputBoundary implements CurrentPriceInputBoundary {
        boolean goToLoginCalled = false;
        boolean goToSignupCalled = false;
        boolean goToFuturePricingCalled = false;
        boolean goToComparatorCalled = false;
        boolean checkLoginStatusCalled = false;
        boolean goToUserProfileCalled = false;
        boolean goToLandingPageCalled = false;
        boolean fetchCurrentPriceCalled = false;

        @Override
        public void goToLogin() {
            goToLoginCalled = true;
        }

        @Override
        public void goToSignup() {
            goToSignupCalled = true;
        }

        @Override
        public void goToFuturePricing() {
            goToFuturePricingCalled = true;
        }

        @Override
        public void goToComparator() {
            goToComparatorCalled = true;
        }

        @Override
        public void checkLoginStatus() {
            checkLoginStatusCalled = true;
        }

        @Override
        public void goToUserProfile() {
            goToUserProfileCalled = true;
        }

        @Override
        public void goToLandingPage() {
            goToLandingPageCalled = true;
        }

        @Override
        public void fetchCurrentPrice() {
            fetchCurrentPriceCalled = true;
        }
    }
}
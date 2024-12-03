package usecases.currentprice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.currentprice.CurrentPricePresenter;
import quotify_app.adapters.currentprice.CurrentPriceViewModel;
import quotify_app.app.ApplicationState;
import quotify_app.entities.CommonUserFactory;
import quotify_app.entities.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link CurrentPricePresenter}.
 */
public class CurrentPricePresenterTest {

    private CurrentPricePresenter presenter;
    private ViewManagerModel viewManagerModel;
    private CurrentPriceViewModel currentPriceViewModel;
    private CommonUserFactory commonUserFactory;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        currentPriceViewModel = new CurrentPriceViewModel();
        presenter = new CurrentPricePresenter(viewManagerModel, currentPriceViewModel);
        commonUserFactory = new CommonUserFactory();
    }

    @Test
    public void testPresentGoToLogin() {
        // Act
        presenter.presentGoToLogin();

        // Assert
        assertEquals("log in", viewManagerModel.getState());
    }

    @Test
    public void testPresentGoToSignup() {
        // Act
        presenter.presentGoToSignup();

        // Assert
        assertEquals("sign up", viewManagerModel.getState());
    }

    @Test
    public void testPresentGoToFuturePricing() {
        // Act
        presenter.presentGoToFuturePricing();

        // Assert
        assertEquals("future price", viewManagerModel.getState());
    }

    @Test
    public void testPresentGoToComparator() {
        // Act
        presenter.presentGoToComparator();

        // Assert
        assertEquals("comparator", viewManagerModel.getState());
    }

    @Test
    public void testPresentGoToUserProfile() {
        // Act
        presenter.presentGoToUserProfile();

        // Assert
        assertEquals("user profile", viewManagerModel.getState());
    }

    @Test
    public void testUpdateLoginStatusLoggedIn() {
        // Arrange
        final User user = commonUserFactory.create("TestUser",
                "testuser@example.com",
                "password");
        ApplicationState.getInstance().setLoggedIn(true, user.getName());

        // Act
        presenter.updateLoginStatus();

        // Assert
        assertTrue(currentPriceViewModel.getState().isLoggedIn(), "User should be logged in.");
    }

    @Test
    public void testUpdateLoginStatusLoggedOut() {
        // Arrange
        ApplicationState.getInstance().setLoggedIn(false, null);

        // Act
        presenter.updateLoginStatus();

        // Assert
        assertFalse(currentPriceViewModel.getState().isLoggedIn(), "User should be logged out.");
    }

    @Test
    public void testPresentGoToLandingPage() {
        // Act
        presenter.presentGoToLandingPage();

        // Assert
        assertEquals("landing", viewManagerModel.getState());
    }

    @Test
    public void testPresentCurrentPrice() {
        // Act
        double price = 250000.75;
        presenter.presentCurrentPrice(price);

        // Assert
        assertEquals("$250000.75", currentPriceViewModel.getState().getCurrentPrice());
    }

    @Test
    public void testPresentPredictionError() {
        // Act
        String errorMessage = "Prediction service unavailable.";
        presenter.presentPredictionError(errorMessage);

        // Assert
        assertEquals("Error: Prediction service unavailable.", currentPriceViewModel.getState().getCurrentPrice());
    }
}

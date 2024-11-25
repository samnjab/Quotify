package usecases.comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.comparator.ComparatorPresenter;
import quotify_app.adapters.comparator.ComparatorViewModel;
import quotify_app.usecases.comparator.ComparatorInteractor;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link ComparatorInteractor}.
 */
public class ComparatorInteractorTest {

    private ComparatorInteractor comparatorInteractor;
    private ComparatorPresenter comparatorPresenter;
    private ViewManagerModel viewManagerModel;
    private ComparatorViewModel comparatorViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        comparatorViewModel = new ComparatorViewModel();
        comparatorPresenter = new ComparatorPresenter(viewManagerModel, comparatorViewModel);
        comparatorInteractor = new ComparatorInteractor(comparatorPresenter);
    }

    @Test
    public void testGoToCurrentPrice() {
        // Act
        comparatorInteractor.goToCurrentPrice();

        // Assert
        assertTrue(viewManagerModel.getState().equals("currentPrice"));
    }

    @Test
    public void testGoToInput() {
        // Act
        comparatorInteractor.goToInput();

        // Assert
        assertTrue(viewManagerModel.getState().equals("input"));
    }

    @Test
    public void testCheckLoginStatus() {
        // Arrange
        boolean expectedLoginStatus = false;
        comparatorViewModel.getState().setLoggedIn(expectedLoginStatus);

        // Act
        comparatorInteractor.checkLoginStatus();

        // Assert
        assertTrue(comparatorViewModel.getState().isLoggedIn() == expectedLoginStatus);
    }

    @Test
    public void testGoToUserProfile() {
        // Act
        comparatorInteractor.goToUserProfile();

        // Assert
        assertTrue(viewManagerModel.getState().equals("user profile"));
    }
}

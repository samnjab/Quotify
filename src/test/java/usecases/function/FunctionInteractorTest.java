package usecases.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.function.FunctionPresenter;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.usecases.function.FunctionInteractor;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link FunctionInteractor}.
 */
public class FunctionInteractorTest {

    private FunctionInteractor functionInteractor;
    private FunctionPresenter functionPresenter;
    private ViewManagerModel viewManagerModel;
    private FunctionViewModel functionViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        functionViewModel = new FunctionViewModel();
        functionPresenter = new FunctionPresenter(viewManagerModel, functionViewModel);
        functionInteractor = new FunctionInteractor(functionPresenter);
    }

    @Test
    public void testGoToCurrentPrice() {
        // Act
        functionInteractor.goToCurrentPrice();

        // Assert
        assertTrue(viewManagerModel.getState().equals("current price"));
    }

    @Test
    public void testGoToComparator() {
        // Act
        functionInteractor.goToComparator();

        // Assert
        assertTrue(viewManagerModel.getState().equals("comparator"));
    }
}

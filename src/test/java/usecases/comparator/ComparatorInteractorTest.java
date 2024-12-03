package usecases.comparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.comparator.ComparatorPresenter;
import quotify_app.adapters.comparator.ComparatorViewModel;
import quotify_app.data_access.AreaDataAccessObject;
import quotify_app.data_access.AreaStore;
import quotify_app.data_access.ComparatorDataAccessObject;
import quotify_app.data_access.PropertyDataAccessObject;
import quotify_app.entities.regionEntities.*;
import quotify_app.usecases.comparator.ComparatorDataAccessInterface;
import quotify_app.usecases.comparator.ComparatorInteractor;
import quotify_app.adapters.currentprice.CurrentPriceController;
import quotify_app.usecases.currentprice.CurrentPriceInputBoundary;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ComparatorInteractor}.
 */
public class ComparatorInteractorTest {

    private ComparatorInteractor comparatorInteractor;
    private ComparatorPresenter comparatorPresenter;
    private ViewManagerModel viewManagerModel;
    private ComparatorViewModel comparatorViewModel;
    private ComparatorDataAccessObject comparatorDataAccessObject;
    private PropertyDataAccessObject propertyDataAccessObject;
    private final Address badAddress = new Address(
            "UA",
            "CZ",
            "San Brancisco",
            "Fails",
            "234",
            "01230123"
    );
    private final Identifier badIdentifier = new Identifier(
            "Nope",
            Map.of(
                    "ZI", "Bad",
                    "CS", "Bad",
                    "ST", "Bad",
                    "CO", "Terrible",
                    "N2", "Trash"
            )
    );
    private final Summary badSummary = new Summary(
            "-1",
            -1,
            0,
            "0",
            0,
            0,
            -1
    );
    private final Address goodAddress = new Address(
            "US",
            "NY",
            "Albany",
            "CAMPUS CLUB DR",
            "26",
            "12084"
    );
    private final Identifier goodIdentifier = new Identifier(
            "4692220",
            Map.of(
                    "ZI", "82052344736ea4a747ae8fd283a29734",
                    "CS", "ca9cb8ddcc92c619d4a64e6bc6125b8d",
                    "ST", "ST36",
                    "CO", "c92b20d9ac51365b22857a5e3e292c25",
                    "N2", "Oaa691ef3be9ab71a648069522e48d57"
            )
    );
    private final Summary goodSummary = new Summary(
            "1",
            4,
            3,
            "Good",
            2,
            2507,
            1968
    );

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        comparatorViewModel = new ComparatorViewModel();
        comparatorPresenter = new ComparatorPresenter(viewManagerModel, comparatorViewModel);
        propertyDataAccessObject = new PropertyDataAccessObject();
        comparatorDataAccessObject = new ComparatorDataAccessObject(propertyDataAccessObject);
        comparatorInteractor = new ComparatorInteractor(comparatorPresenter, comparatorDataAccessObject);
    }

    @Test
    public void testGoToCurrentPrice() {
        // Act
        comparatorInteractor.goToCurrentPrice();

        // Assert
        assertTrue(viewManagerModel.getState().equals("current price"));
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

    @Test public void testCompareSuccess() {
        final Property goodProperty = new Property(goodIdentifier, goodAddress, goodSummary);
        propertyDataAccessObject.setCurrentProperty(goodProperty);

        comparatorInteractor.getComparables();

        //
        assertTrue(!comparatorViewModel.getState().isCompareFailed());

    }

    @Test public void testCompareFail() {
        final Property badProperty = new Property(badIdentifier, badAddress, badSummary);
        propertyDataAccessObject.setCurrentProperty(badProperty);

        comparatorInteractor.getComparables();

        //
        assertTrue(comparatorViewModel.getState().isCompareFailed());
    }
}



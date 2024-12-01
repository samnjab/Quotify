package usecases.signup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.entities.User;
import quotify_app.entities.CommonUserFactory;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.data_access.DatabaseConnection;
import quotify_app.usecases.signup.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for {@link SignupInteractor}.
 */
public class SignupInteractorTest {

    private static DBUserDataAccessObject userRepository;
    private static CommonUserFactory userFactory;
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

    @BeforeAll
    static void setUpDatabase() throws Exception {
        DatabaseConnection.initializeDatabase();
        userFactory = new CommonUserFactory();
        userRepository = new DBUserDataAccessObject(userFactory);
    }

    @BeforeEach
    public void setup() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSuccessSignup() {
        SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "password");

        // Success presenter
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                assertEquals("Paul", outputData.getUsername());
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void goToLogin() {
                // Expected outcome for successful signup
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, userFactory);
        interactor.execute(inputData);
    }

    @Test
    public void testSignupUserAlreadyExists() {
        SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "password");

        // Prepopulate user data
        User user = userFactory.create("Paul", "password", "paul@example.com");
        userRepository.save(user);

        // Failure presenter for existing user
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Username already exists.", errorMessage);
            }

            @Override
            public void goToLogin() {
                // Expected outcome for failure case
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, userFactory);
        interactor.execute(inputData);
    }

    @Test
    public void testSignupEmailAlreadyInUse() {
        SignupInputData inputData = new SignupInputData("John", "paul@example.com", "password");

        // Prepopulate user data with same email
        User user = userFactory.create("Paul", "password", "paul@example.com");
        userRepository.save(user);

        // Failure presenter for existing email
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Email already in use.", errorMessage);
            }

            @Override
            public void goToLogin() {
                // Expected outcome for failure case
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, userFactory);
        interactor.execute(inputData);
    }

    @Test
    public void testSignupEmptyEmail() {
        SignupInputData inputData = new SignupInputData("Paul", "", "password");

        // Failure presenter for empty email
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Email cannot be empty.", errorMessage);
            }

            @Override
            public void goToLogin() {
                // Expected outcome for failure case
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, userFactory);
        interactor.execute(inputData);
    }

    @Test
    public void testSignupOutputData() {
        // Test coverage for SignupOutputData class
        SignupOutputData outputData = new SignupOutputData("TestUser");
        assertEquals("TestUser", outputData.getUsername());
    }

    @Test
    public void testGoToLogin() {
        // Arrange: Create a mock presenter and interactor
        SignupViewModel signupViewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("prepareSuccessView should not be called in this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView should not be called in this test.");
            }

            @Override
            public void goToLogin() {
                // Set the state to "login" in the view manager model to simulate navigation
                viewManagerModel.setState("login");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, signupPresenter, userFactory);

        // Act: Trigger the `goToLogin` method
        interactor.goToLogin();

        // Assert: Verify the state has been updated to "login"
        assertEquals("login", viewManagerModel.getState(), "Expected state to be 'login' after calling goToLogin.");
    }

}

package usecases.login;

import org.junit.jupiter.api.*;
import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.data_access.DatabaseConnection;
import quotify_app.entities.CommonUserFactory;
import quotify_app.entities.User;
import quotify_app.entities.UserFactory;
import quotify_app.usecases.login.*;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link LoginInteractor}.
 */
class LoginInteractorTest {
    private static DBUserDataAccessObject userRepository;
    private static UserFactory userFactory;

    @BeforeAll
    static void setUpDatabase() {
        DatabaseConnection.initializeDatabase();
        userFactory = new CommonUserFactory();
        userRepository = new DBUserDataAccessObject(userFactory);
    }

    @BeforeEach
    void cleanDatabase() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM users");
        }
    }

    @Test
    void testFailureEmptyPassword() {
        LoginInputData inputData = new LoginInputData("Paul", "");
        User user = userFactory.create("Paul", "password", "paul@example.com");
        userRepository.save(user);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void goToSignup() {
                // Dummy implementation for the test
            }

            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void testFailureInvalidUsernameFormat() {
        LoginInputData inputData = new LoginInputData("invalid user", "password123");

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("invalid user: Account does not exist.", error);
            }

            @Override
            public void goToSignup() {
                // Dummy implementation for the test
            }

            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        try {
            interactor.execute(inputData);
        } catch (RuntimeException e) {
            assertEquals("Database connection failed.", e.getMessage());
        }
    }

    @Test
    void TestFailureDatabaseConnection() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        DBUserDataAccessObject failingRepository = new DBUserDataAccessObject(userFactory) {
            @Override
            public boolean existsByName(String username) {
                throw new RuntimeException("Database connection failed.");
            }

            @Override
            public User get(String username) {
                throw new RuntimeException("Database connection failed.");
            }
        };

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Unable to connect to the database. Please try again later.", error);
            }

            @Override
            public void goToSignup() {
                // Dummy implementation for the test
            }

            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(failingRepository, failurePresenter);
        try {
            interactor.execute(inputData);
        } catch (RuntimeException e) {
            assertEquals("Database connection failed.", e.getMessage());
        }
    }

    @Test
    void testGToSignup() {
        LoginOutputBoundary transitionPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("This test should only verify signup transition.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("This test should only verify signup transition.");
            }

            @Override
            public void goToSignup() {
                assertTrue(true, "Successfully transitioned to signup view.");
            }

            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInteractor interactor = new LoginInteractor(userRepository, transitionPresenter);
        interactor.goToSignup();
    }

    @Test
    void testFailureIncorrectPassword() {
        LoginInputData inputData = new LoginInputData("Paul", "wrongPassword");
        User user = userFactory.create("Paul", "correctPassword", "paul@example.com");
        userRepository.save(user);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void goToSignup() {
                // Dummy implementation for the test
            }
            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void testFailureUserDoesNotExist() {
        LoginInputData inputData = new LoginInputData("NonExistentUser", "password123");

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("NonExistentUser: Account does not exist.", error);
            }

            @Override
            public void goToSignup() {
                // Dummy implementation for the test
            }
            @Override
            public void goToFunctionView() {
                // Dummy implementation for the test
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}

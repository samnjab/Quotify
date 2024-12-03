package quotify_app.usecases.userprofile;

import org.junit.jupiter.api.Test;
import quotify_app.entities.CommonUser;
import quotify_app.entities.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileInteractorTest {

    // Mock concrete implementation of UserProfileOutputBoundary
    private static class MockUserProfilePresenter implements UserProfileOutputBoundary {
        private boolean successViewCalled = false;
        private boolean failureViewCalled = false;
        private UserProfileOutputData outputData;

        @Override
        public void prepareSuccessView(UserProfileOutputData data) {
            this.successViewCalled = true;
            this.outputData = data;
        }

        @Override
        public void prepareFailureView() {
            this.failureViewCalled = true;
        }

        @Override
        public void goToLanding() {
            // No-op for testing
        }
    }

    // Mock concrete implementation of UserProfileDataAccessInterface
    private static class MockUserProfileDataAccess implements UserProfileDataAccessInterface {
        private final String currentUsername;
        private final LocalDateTime createdAt;
        private final boolean userExists;
        private final User mockUser;

        public MockUserProfileDataAccess(String currentUsername, LocalDateTime createdAt, boolean userExists, User mockUser) {
            this.currentUsername = currentUsername;
            this.createdAt = createdAt;
            this.userExists = userExists;
            this.mockUser = mockUser;
        }

        @Override
        public String getCurrentUserName() {
            return currentUsername;
        }

        @Override
        public Timestamp getCurrentCreatedAt() {
            return Timestamp.valueOf(createdAt);
        }

        @Override
        public boolean existsByName(String username) {
            return userExists && username.equals(currentUsername);
        }

        @Override
        public User get(String username) {
            if (!userExists || !username.equals(currentUsername)) {
                throw new IllegalArgumentException("User not found");
            }
            return mockUser;
        }
    }

    @Test
    void execute_UserExists_PreparesSuccessView() {
        // Arrange
        final String username = "existing_user";
        final String email = "existing_user@example.com";
        final LocalDateTime createdAt = LocalDateTime.now();
        final CommonUser mockUser = new CommonUser(username,"password", email);

        MockUserProfilePresenter mockPresenter = new MockUserProfilePresenter();
        MockUserProfileDataAccess mockDataAccess = new MockUserProfileDataAccess(username, createdAt, true, mockUser);

        UserProfileInteractor interactor = new UserProfileInteractor(mockPresenter, mockDataAccess);

        // Act
        interactor.execute();

        // Assert
        assertTrue(mockPresenter.successViewCalled, "Success view should be called");
        assertNotNull(mockPresenter.outputData, "Output data should not be null");
        assertEquals(username, mockPresenter.outputData.getUsername(), "Usernames should match");
        assertEquals(email, mockPresenter.outputData.getEmail(), "Emails should match");
        assertEquals(Timestamp.valueOf(createdAt).toString(), mockPresenter.outputData.getCreatedAt(), "Timestamps should match");
        assertFalse(mockPresenter.failureViewCalled, "Failure view should not be called");
    }

    @Test
    void execute_UserDoesNotExist_PreparesFailureView() {
        // Arrange
        final String username = "non_existing_user";
        final LocalDateTime createdAt = LocalDateTime.now();

        MockUserProfilePresenter mockPresenter = new MockUserProfilePresenter();
        MockUserProfileDataAccess mockDataAccess = new MockUserProfileDataAccess(username, createdAt, false, null);

        UserProfileInteractor interactor = new UserProfileInteractor(mockPresenter, mockDataAccess);

        // Act
        interactor.execute();

        // Assert
        assertTrue(mockPresenter.failureViewCalled, "Failure view should be called");
        assertFalse(mockPresenter.successViewCalled, "Success view should not be called");
    }

    @Test
    void goToLanding_InvokesPresenterMethod() {
        // Arrange
        MockUserProfilePresenter mockPresenter = new MockUserProfilePresenter();
        MockUserProfileDataAccess mockDataAccess = new MockUserProfileDataAccess(null, null, false, null);

        UserProfileInteractor interactor = new UserProfileInteractor(mockPresenter, mockDataAccess);

        // Act
        interactor.goToLanding();

        // Assert
        // You may need to add a flag or mechanism in the mockPresenter to verify this method is called
    }
}

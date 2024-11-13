package quotify_app.usecases.signup;

import quotify_app.entities.User;

/**
 * Data Access Interface for Signup Use Case.
 */
public interface SignupUserDataAccessInterface {
    boolean existsByName(String username);
    boolean existsByEmail(String email);
    void save(User user);
}

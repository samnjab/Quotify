package quotify_app.usecases.signup;

import quotify_app.entities.User;

/**
 * Data Access Interface for Signup Use Case.
 */
public interface SignupUserDataAccessInterface {
    /**
     * Checks if a user with the given username exists.
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByName(String username);

    /**
     * Retrieves a user by their email.
     * @param email the username to retrieve
     * @return Boolean object corresponding to whether the user exists or not.
     */
    boolean existsByEmail(String email);

    /**
     * Saves a given user's information to the users table in the database.
     * @param user the User object from which we get a username, email, password.
     */
    void save(User user);
}

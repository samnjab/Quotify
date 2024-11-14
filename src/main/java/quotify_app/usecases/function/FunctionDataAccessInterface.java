package quotify_app.usecases.function;

import quotify_app.entities.User;

/**
 * Data Access Interface for Signup Use Case.
 */
public interface FunctionDataAccessInterface {
    /**
     * Checks if a user with the given username exists.
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves a given user's information to the users table in the database.
     * @param user the User object from which we get a username, email, password.
     */
    void save(User user);
}

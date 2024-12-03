package quotify_app.usecases.login;

import quotify_app.entities.User;

/**
 * Data Access Interface for retrieving user data in the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if a user with the given username exists.
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByName(String username);

    /**
     * Retrieves a user by their username.
     * @param username the username to retrieve
     * @return the User object corresponding to the username
     */
    User get(String username);

    void setCurrentUsername(String username);
}

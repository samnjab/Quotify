package quotify_app.usecases.userprofile;

import java.sql.Timestamp;

import quotify_app.entities.User;

/**
 * Interface for the DBuserDAO for the userprofile usecase.
 */
public interface UserProfileDataAccessInterface {
    /**
     * Return the current user's username.
     * @return the string representation of the current user's username
     */
    String getCurrentUserName();

    /**
     * Return the time the current user create their account.
     * @return the string representation of the current user's account creation timestamp
     */
    Timestamp getCurrentCreatedAt();

    /**
     * Return the current user from the database.
     * @return the User object representing the current user.
     * */
    User get(String username);

    /**
     *  Return a boolean value verifying the existence of a user in the database.
     * @param username the username to query the database by.
     * @return a boolean value representing the outcome of this check.
     */
    boolean existsByName(String username);
}

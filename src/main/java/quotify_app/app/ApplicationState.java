package quotify_app.app;

import quotify_app.entities.User;

/**
 * Singleton class that holds the global application state.
 * <p>
 * This class is responsible for maintaining the application's global state,
 * specifically the user's login status and the currently logged-in user.
 * It follows the Singleton design pattern to ensure that only one instance
 * of the application state exists throughout the application's lifecycle.
 * </p>
 */
public final class ApplicationState {

    /**
     * The single instance of the ApplicationState.
     */
    private static ApplicationState instance;

    /**
     * Flag indicating whether a user is currently logged in.
     */
    private boolean isLoggedIn;

    /**
     * The currently logged-in user.
     * If no user is logged in, this will be {@code null}.
     */
    private User currentUser;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the application state with default values.
     */
    private ApplicationState() {
        this.isLoggedIn = false;
        this.currentUser = null;
    }

    /**
     * Returns the single instance of the ApplicationState.
     * If the instance does not exist yet, it creates one.
     *
     * @return the singleton instance of ApplicationState.
     */
    public static synchronized ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState();
        }
        return instance;
    }

    /**
     * Checks whether a user is currently logged in.
     *
     * @return {@code true} if a user is logged in; {@code false} otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Sets the login status of the application and updates the current user.
     * <p>
     * When a user logs in or out, this method should be called to update the application state.
     * </p>
     *
     * @param loggedIn {@code true} if the user has logged in; {@code false} if the user has logged out.
     * @param user     the {@link User} object representing the currently logged-in user;
     *                 should be {@code null} if {@code loggedIn} is {@code false}.
     */
    public void setLoggedIn(boolean loggedIn, User user) {
        this.isLoggedIn = loggedIn;
        this.currentUser = user;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the {@link User} object representing the current user;
     *         or {@code null} if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs out the current user by resetting the login status and clearing the current user information.
     * <p>
     * This method should be called when the user chooses to log out of the application.
     * </p>
     */
    public void logout() {
        this.isLoggedIn = false;
        this.currentUser = null;
    }
}

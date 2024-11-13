package quotify_app.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import quotify_app.entities.User;
import quotify_app.entities.UserFactory;
import quotify_app.usecases.login.LoginUserDataAccessInterface;
import quotify_app.usecases.signup.SignupUserDataAccessInterface;

/**
 * Data access object for user-related operations, interacting with an SQLite database.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final UserFactory userFactory;
    private String currentUsername;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        initializeDatabase();
    }

    /**
     * Initializes the database and ensures that the users table exists.
     */
    private void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users ("
                             + "username TEXT PRIMARY KEY, "
                             + "email TEXT UNIQUE, "
                             + "password TEXT NOT NULL)"
             )) {
            stmt.executeUpdate();
            System.out.println("Database initialized successfully. Users table created if it didn't exist.");
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    /**
     * Queries and returns a user by a given username.
     * @param username the given string username for a given user.
     * @return User
     */
    public User get(String username) {
        final String query = "SELECT username, email, password FROM users WHERE username = ?";
        User result = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = userFactory.create(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
        return result;
    }

    /**
     * Checks and returns whether a user exists by a given username.
     * @param username the given string username for a given user.
     * @return boolean value representing whether user exists or not.
     */
    @Override
    public boolean existsByName(String username) {
        final String query = "SELECT username FROM users WHERE username = ?";
        boolean flag = false;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            final ResultSet rs = stmt.executeQuery();
            flag = rs.next();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
        return flag;
    }

    /**
     * Checks and returns whether a user exists by a given email.
     * @param email the given string username for a given user.
     * @return boolean value representing whether user exists or not.
     */
    @Override
    public boolean existsByEmail(String email) {
        final String query = "SELECT email FROM users WHERE email = ?";
        boolean flag = false;
        // Debug
        System.out.println("Checking email: " + email);
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Found email in database: " + rs.getString("email"));
                flag = true;
            }
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
        return flag;
    }

    /**
     * Saves a given user's information to the users table in the database.
     * @param user the User object from which we get a username, email, password.
     */
    @Override
    public void save(User user) {
        final String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            // Assumes User has an getEmail() method.
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            System.out.println("User " + user.getName() + " saved successfully.");
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}

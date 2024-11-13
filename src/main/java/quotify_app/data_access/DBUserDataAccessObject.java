package quotify_app.data_access;

import quotify_app.entities.User;
import quotify_app.entities.UserFactory;
import quotify_app.usecases.login.LoginUserDataAccessInterface;
import quotify_app.usecases.signup.SignupUserDataAccessInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "username TEXT PRIMARY KEY, " +
                             "email TEXT UNIQUE, " +
                             "password TEXT NOT NULL)"
             )) {
            stmt.executeUpdate();
            System.out.println("Database initialized successfully. Users table created if it didn't exist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(String username) {
        String query = "SELECT username, email, password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return userFactory.create(rs.getString("username"), rs.getString("password"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        String query = "SELECT username FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        String query = "SELECT email FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail()); // Assumes User has an getEmail() method.
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            System.out.println("User " + user.getName() + " saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}

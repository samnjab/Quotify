package quotify_app.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class that initializes connection with the SQLite server.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:quotify.db";

    /**
     * Establishes a new connection to the SQLite database.
     *
     * @return a new Connection to the SQLite database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Initializes the database and ensures that the users table exists.
     */
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users ("
                             + "username TEXT PRIMARY KEY, "
                             + "email TEXT UNIQUE, "
                             + "password TEXT NOT NULL, "
                             + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
             )) {
            stmt.executeUpdate();
            System.out.println("Database initialized successfully. Users table created if it didn't exist.");
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}

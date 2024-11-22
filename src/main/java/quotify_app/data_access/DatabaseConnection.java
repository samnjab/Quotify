package quotify_app.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that initializes connection with the SQLite server.
 */
public class  DatabaseConnection {
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
     * Initializes the database by creating required tables if they don't exist.
     */
    public static void initializeDatabase() {
        final String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT PRIMARY KEY,"
                + "password TEXT NOT NULL,"
                + "email TEXT UNIQUE NOT NULL"
                + ");";

        try (Connection conn = getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            System.out.println("Database initialized successfully. Users table created if it didn't exist.");
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}

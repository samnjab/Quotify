package quotify_app.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:users.db";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL);
            createUsersTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to create the users table if it doesn't exist
    private static void createUsersTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "email TEXT NOT NULL, "
                + "username TEXT PRIMARY KEY, "
                + "password TEXT NOT NULL"
                + ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Users table created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

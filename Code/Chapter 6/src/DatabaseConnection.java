import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // PostgreSQL connection URL
    private static final String URL = "jdbc:postgresql://localhost:5432/MRCJava";
    private static final String USER = "postgres"; // Default Postgres user
    private static final String PASSWORD = "12345"; // Change this to your Postgres password

    public static Connection getConnection() throws SQLException {
        try {
            // Load PostgreSQL Driver
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found! Make sure you have the postgresql JAR.", e);
        }
    }
}

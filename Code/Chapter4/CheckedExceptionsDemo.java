
/**
 * CheckedExceptionsDemo.java
 * Demonstrates common Checked Exceptions in Java
 * - IOException
 * - FileNotFoundException
 * - ClassNotFoundException
 * - SQLException (simulated)
 */

import java.io.*;
import java.sql.*;

public class CheckedExceptionsDemo {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      CHECKED EXCEPTIONS DEMONSTRATION            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        // 1. FileNotFoundException Demo
        System.out.println("═══ 1. FileNotFoundException ═══");
        fileNotFoundDemo();

        // 2. IOException Demo
        System.out.println("\n═══ 2. IOException ═══");
        ioExceptionDemo();

        // 3. ClassNotFoundException Demo
        System.out.println("\n═══ 3. ClassNotFoundException ═══");
        classNotFoundDemo();

        // 4. SQLException Demo (simulated)
        System.out.println("\n═══ 4. SQLException ═══");
        sqlExceptionDemo();

        System.out.println("\n✅ All demos completed successfully!");
    }

    // ═══════════════════════════════════════════════════════════════
    // 1. FileNotFoundException
    // ═══════════════════════════════════════════════════════════════
    static void fileNotFoundDemo() {

        // ❌ Attempting to open a non-existent file
        try {
            System.out.println("Attempting to open 'nonexistent.txt'...");
            FileReader file = new FileReader("nonexistent.txt");
            System.out.println("File opened successfully!");
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("❌ FileNotFoundException caught!");
            System.out.println("   Message: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ IOException: " + e.getMessage());
        }

        // ✅ Prevention: Check if file exists before opening
        System.out.println("\n✅ Prevention technique:");
        File file = new File("test.txt");
        if (file.exists() && file.isFile()) {
            System.out.println("   File exists, safe to open.");
        } else {
            System.out.println("   File does not exist. Handle gracefully.");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 2. IOException
    // ═══════════════════════════════════════════════════════════════
    static void ioExceptionDemo() {

        // Create a test file first
        String filename = "test_io.txt";

        // ✅ Writing to file (try-with-resources - auto closes)
        try (FileWriter writer = new FileWriter(filename)) {
            System.out.println("Writing to file...");
            writer.write("Hello, Java Exception Handling!\n");
            writer.write("Line 2: IOException Demo\n");
            System.out.println("✅ Write successful!");
        } catch (IOException e) {
            System.out.println("❌ IOException during write: " + e.getMessage());
        }

        // ✅ Reading from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("\nReading from file:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   " + line);
            }
            System.out.println("✅ Read successful!");
        } catch (IOException e) {
            System.out.println("❌ IOException during read: " + e.getMessage());
        }

        // Cleanup - delete test file
        new File(filename).delete();
    }

    // ═══════════════════════════════════════════════════════════════
    // 3. ClassNotFoundException
    // ═══════════════════════════════════════════════════════════════
    static void classNotFoundDemo() {

        // ❌ Loading a class that doesn't exist
        try {
            System.out.println("Attempting to load 'com.example.FakeClass'...");
            Class<?> clazz = Class.forName("com.example.FakeClass");
            System.out.println("Class loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ClassNotFoundException caught!");
            System.out.println("   Message: " + e.getMessage());
        }

        // ✅ Loading a class that exists
        try {
            System.out.println("\nAttempting to load 'java.util.ArrayList'...");
            Class<?> clazz = Class.forName("java.util.ArrayList");
            System.out.println("✅ Class loaded: " + clazz.getName());
            System.out.println("   Simple name: " + clazz.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ClassNotFoundException: " + e.getMessage());
        }

        // Common use case: Loading JDBC driver
        System.out.println("\n💡 Common use case - JDBC Driver:");
        try {
            // This will fail if MySQL driver is not in classpath
            // Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("   JDBC drivers are typically loaded this way");
            System.out.println("   (Commented out as MySQL driver not in classpath)");
        } catch (Exception e) {
            System.out.println("   Driver not found");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 4. SQLException (Simulated - no actual database)
    // ═══════════════════════════════════════════════════════════════
    static void sqlExceptionDemo() {

        System.out.println("Note: This is a simulation (no actual database)");

        // Simulating SQLException
        try {
            // Simulate a database connection failure
            simulateDatabaseOperation();
        } catch (SQLException e) {
            System.out.println("❌ SQLException caught!");
            System.out.println("   Error Code: " + e.getErrorCode());
            System.out.println("   SQL State: " + e.getSQLState());
            System.out.println("   Message: " + e.getMessage());
        }

        System.out.println("\n✅ Proper SQLException handling pattern:");
        System.out.println("   try (Connection conn = DriverManager.getConnection(...);");
        System.out.println("        PreparedStatement ps = conn.prepareStatement(sql)) {");
        System.out.println("       // Execute query");
        System.out.println("   } catch (SQLException e) {");
        System.out.println("       // Handle error, log, rollback transaction");
        System.out.println("   }");
    }

    // Helper method to simulate SQLException
    private static void simulateDatabaseOperation() throws SQLException {
        System.out.println("Attempting database connection...");
        // Simulate a connection failure
        throw new SQLException(
                "Connection refused: Host 'localhost' is not available",
                "08001", // SQL State for connection error
                1045 // Error code
        );
    }
}

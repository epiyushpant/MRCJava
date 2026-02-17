import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileOperationsDemo.java
 * Topic 5.2: Opening and Closing Files
 * 
 * Demonstrates:
 * - Creating files and directories using File class
 * - File metadata (exists, name, size, permissions)
 * - Opening files for reading and writing (streams)
 * - Closing files manually and with try-with-resources
 * - Deleting files
 */
public class FileOperationsDemo {
    public static void main(String[] args) {

        // ==========================================
        // PART 1: Create a File
        // ==========================================
        System.out.println("===== PART 1: Creating Files =====");

        try {
            File file = new File("sample.txt");

            if (file.createNewFile()) {
                System.out.println("Created: " + file.getName());
            } else {
                System.out.println("Already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 2: File Information
        // ==========================================
        System.out.println("\n===== PART 2: File Information =====");

        File file = new File("sample.txt");
        if (file.exists()) {
            System.out.println("Name       : " + file.getName());
            System.out.println("Path       : " + file.getAbsolutePath());
            System.out.println("Size       : " + file.length() + " bytes");
            System.out.println("Readable   : " + file.canRead());
            System.out.println("Writable   : " + file.canWrite());
            System.out.println("Is File    : " + file.isFile());
            System.out.println("Is Directory: " + file.isDirectory());
        }

        // ==========================================
        // PART 3: Open, Write, Close (Manual)
        // ==========================================
        System.out.println("\n===== PART 3: Manual Open & Close =====");

        FileWriter writer = null;
        try {
            writer = new FileWriter("sample.txt"); // Open for writing
            writer.write("Hello from manual close example!\n");
            writer.write("Always close the writer.\n");
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // Close in finally block
                    System.out.println("Writer closed (manual).");
                }
            } catch (IOException e) {
                System.out.println("Error closing: " + e.getMessage());
            }
        }

        // ==========================================
        // PART 4: Open, Read, Close (try-with-resources)
        // ==========================================
        System.out.println("\n===== PART 4: try-with-resources =====");

        try (FileReader reader = new FileReader("sample.txt")) {
            // reader is auto-closed when this block ends
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println("Reader closed (auto).");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 5: Delete a File
        // ==========================================
        System.out.println("\n===== PART 5: Deleting Files =====");

        File toDelete = new File("sample.txt");
        if (toDelete.exists()) {
            if (toDelete.delete()) {
                System.out.println("Deleted: " + toDelete.getName());
            } else {
                System.out.println("Could not delete.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}

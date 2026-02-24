import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ByteStreamDemo.java
 * Topic 5.5: Reading and Writing Byte Streams
 * 
 * Demonstrates:
 * - FileOutputStream: writing bytes to a file
 * - FileInputStream: reading bytes from a file
 * - Copying a file using byte streams (with buffer array)
 * - Append mode with FileOutputStream
 * 
 * Byte streams work with raw binary data (1 byte = 8 bits at a time).
 * Best for: images, audio, video, PDF, or any binary file.
 */
public class ByteStreamDemo {
    public static void main(String[] args) {

        // ==========================================
        // PART 1: Writing Bytes (FileOutputStream)
        // ==========================================
        System.out.println("===== PART 1: Writing with FileOutputStream =====");

        try {
            FileOutputStream fos = new FileOutputStream("byte_data.txt");

            // Write a String as bytes
            String text = "Hello from Byte Stream!\n";
            byte[] data = text.getBytes(); // Convert String → byte[]
            fos.write(data);

            // Write individual bytes (ASCII values)
            fos.write(65); // 'A'
            fos.write(66); // 'B'
            fos.write(67); // 'C'
            fos.write(10); // newline character

            // Write another line
            fos.write("Byte streams handle raw binary data.\n".getBytes());

            fos.close();
            System.out.println("Data written to byte_data.txt\n");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 2: Reading Bytes (FileInputStream)
        // ==========================================
        System.out.println("===== PART 2: Reading with FileInputStream =====");

        try {
            FileInputStream fis = new FileInputStream("byte_data.txt");

            System.out.println("File contents:");
            int byteData;
            // read() returns -1 when end of file is reached
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);
            }

            fis.close();
            System.out.println();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 3: Append Bytes
        // ==========================================
        System.out.println("===== PART 3: Appending with FileOutputStream =====");

        try {
            // 'true' = append mode
            FileOutputStream fos = new FileOutputStream("byte_data.txt", true);
            fos.write("This line was appended!\n".getBytes());
            fos.close();
            System.out.println("Appended a line to byte_data.txt\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 4: File Copy (with buffer)
        // ==========================================
        System.out.println("===== PART 4: Copying File (Byte Stream) =====");

        try (
                FileInputStream fis = new FileInputStream("byte_data.txt");
                FileOutputStream fos = new FileOutputStream("byte_data_copy.txt")) {
            byte[] buffer = new byte[1024]; // 1KB buffer
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied: byte_data.txt → byte_data_copy.txt");
            System.out.println("Bytes copied: " + new java.io.File("byte_data_copy.txt").length());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

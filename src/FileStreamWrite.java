import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamWrite {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("bytes_output.txt");

            // Write a string as bytes
            String text = "Hello from Byte Stream!\n";
            byte[] data = text.getBytes();   // Convert String to byte array
            fos.write(data);

            // Write individual bytes (ASCII values)
            fos.write(65);   // 'A'
            fos.write(66);   // 'B'
            fos.write(67);   // 'C'
            fos.write(10);   // newline

            fos.close();
            System.out.println("Written using FileOutputStream.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
import java.io.FileInputStream;
import java.io.IOException;

public class FileStreamRead {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("bytes_output.txt");

            int byteData;
            // read() returns -1 when end of file is reached
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);   // Cast byte to char
            }

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
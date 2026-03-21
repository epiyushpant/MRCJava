import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getName());
                System.out.println("File path: " + file.getAbsolutePath());
                System.out.println("File size: " + file.length() + " bytes");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
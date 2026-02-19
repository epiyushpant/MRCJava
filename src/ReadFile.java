import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args){
        try {
            FileReader reader = new FileReader("newtext.txt");
            int character;
            while ((character = reader.read()) != -1) {
                System.out.println((char) character);
            }
                reader.close();
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

import java.io.FileWriter;

public class WriteFile {
    public static void main(String[] args){
        try {
            FileWriter writer = new FileWriter("newtext.txt", true);
            writer.write("Hello JAVA\n");
            writer.write("This line is appended");
            writer.close();
            System.out.println("File append Successflly");
        }
        catch (Exception e){
            System.out.println("AN erroe occur" + e.getMessage());
        }
    }
}

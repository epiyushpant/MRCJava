import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BufferReader {
    public static void main(String[] args){
    try {
            BufferedReader br = new BufferedReader(new FileReader("BufferWriter.txt"));
            String line;
            int lineNum=1;

            while((line = br.readLine()) != null){
            System.out.println(lineNum + ":" + line);
            lineNum++;

            }
            br.close();
    }
            catch(IOException e){
            System.out.println("Error: " + e.getMessage());
            }
    }
}

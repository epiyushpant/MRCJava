import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class BufferWrite {
    public static void main(String[] args){
        try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("BufferWriter.txt"));
                bw.write("Line 1: This is BufferWriter\n");
                bw.write("Line 2: Write Data Chunk\n");
                bw.close();
                System.out.println("Write Successfuly using BufferWriter");
        }
        catch(IOException e){
                System.out.println("Error: " + e.getMessage());
        }


        }
    }


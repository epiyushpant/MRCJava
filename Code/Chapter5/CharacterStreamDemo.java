import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * CharacterStreamDemo.java
 * Topic 5.6: Reading and Writing Character Streams
 * 
 * Demonstrates:
 * - FileWriter: writing characters to a file
 * - FileReader: reading characters from a file
 * - BufferedWriter: efficient writing (with newLine())
 * - BufferedReader: efficient reading (with readLine())
 * - PrintWriter: formatted text output to file
 * - Append mode
 * 
 * Character streams work with text data (1 char = 2 bytes / 16 bits).
 * Best for: .txt, .csv, .java, .html and other text files.
 */
public class CharacterStreamDemo {
    public static void main(String[] args) {

        // ==========================================
        // PART 1: FileWriter (Basic Writing)
        // ==========================================
        System.out.println("===== PART 1: FileWriter =====");

        try {
            FileWriter fw = new FileWriter("char_output.txt");
            fw.write("Hello from Character Stream!\n");
            fw.write("FileWriter writes text data.\n");
            fw.write("Each character is 2 bytes (16 bits).\n");
            fw.close();
            System.out.println("Written with FileWriter.\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 2: FileReader (Basic Reading)
        // ==========================================
        System.out.println("===== PART 2: FileReader =====");

        try {
            FileReader fr = new FileReader("char_output.txt");
            int ch;
            // read() returns -1 at end of file
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 3: BufferedWriter (Efficient)
        // ==========================================
        System.out.println("\n===== PART 3: BufferedWriter =====");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("buffered_output.txt"));

            bw.write("Line 1: BufferedWriter is efficient.");
            bw.newLine(); // Platform-independent newline
            bw.write("Line 2: Writes data in chunks, not one char at a time.");
            bw.newLine();
            bw.write("Line 3: Always close or flush the buffer.");

            bw.close(); // Also closes the underlying FileWriter
            System.out.println("Written with BufferedWriter.\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 4: BufferedReader (Efficient)
        // ==========================================
        System.out.println("===== PART 4: BufferedReader =====");

        try {
            BufferedReader br = new BufferedReader(new FileReader("buffered_output.txt"));

            String line;
            int lineNum = 1;
            // readLine() returns null at end of file
            while ((line = br.readLine()) != null) {
                System.out.println("  " + lineNum + ": " + line);
                lineNum++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 5: PrintWriter (Formatted)
        // ==========================================
        System.out.println("\n===== PART 5: PrintWriter =====");

        try {
            PrintWriter pw = new PrintWriter("student_record.txt");

            pw.println("===== Student Record =====");
            pw.println("Name : Ram Sharma");
            pw.println("Age  : 20");
            pw.printf("GPA  : %.2f%n", 3.85);
            pw.println("===========================");

            pw.close();
            System.out.println("Written with PrintWriter.\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 6: Append Mode
        // ==========================================
        System.out.println("===== PART 6: Append Mode =====");

        try {
            // 'true' = append (don't overwrite)
            FileWriter fw = new FileWriter("char_output.txt", true);
            fw.write("This line was appended!\n");
            fw.write("Append mode adds to the end.\n");
            fw.close();
            System.out.println("Appended to char_output.txt\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Verify by reading the full file
        System.out.println("===== Full file after append =====");
        try (BufferedReader br = new BufferedReader(new FileReader("char_output.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

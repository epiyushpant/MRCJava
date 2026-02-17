import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * ScannerDemo.java
 * Topic 5.3: Scanner Class
 * 
 * Demonstrates:
 * - Scanner for console input (keyboard)
 * - Scanner for file input (reading a text file)
 * - Various Scanner methods: nextLine, nextInt, nextDouble, hasNextLine
 */
public class ScannerDemo {
    public static void main(String[] args) {

        // ==========================================
        // STEP 1: Create a file to read from
        // ==========================================
        System.out.println("===== Creating test file =====");
        try {
            FileWriter fw = new FileWriter("students_data.txt");
            fw.write("Ram 20 3.85\n");
            fw.write("Sita 22 3.90\n");
            fw.write("Hari 19 3.60\n");
            fw.write("Gita 21 3.75\n");
            fw.close();
            System.out.println("Test file 'students_data.txt' created.\n");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        // ==========================================
        // PART 1: Scanner for Console Input
        // ==========================================
        System.out.println("===== PART 1: Scanner - Console Input =====");

        Scanner console = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = console.nextLine();

        System.out.print("Enter your age: ");
        int age = console.nextInt();

        System.out.print("Enter your GPA: ");
        double gpa = console.nextDouble();

        System.out.println("\nYou entered:");
        System.out.println("  Name: " + name);
        System.out.println("  Age : " + age);
        System.out.println("  GPA : " + gpa);

        // ==========================================
        // PART 2: Scanner for File Input (line by line)
        // ==========================================
        System.out.println("\n===== PART 2: Scanner - File Input (line by line) =====");

        try {
            Scanner fileScanner = new Scanner(new File("students_data.txt"));

            int lineNum = 1;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("  Line " + lineNum + ": " + line);
                lineNum++;
            }
            System.out.println("  Total lines: " + (lineNum - 1));

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("  File not found: " + e.getMessage());
        }

        // ==========================================
        // PART 3: Scanner for File Input (token by token)
        // ==========================================
        System.out.println("\n===== PART 3: Scanner - File Input (tokens) =====");

        try {
            Scanner tokenScanner = new Scanner(new File("students_data.txt"));

            System.out.println("  Name       Age    GPA");
            System.out.println("  ─────────  ───    ────");

            while (tokenScanner.hasNext()) {
                String studentName = tokenScanner.next(); // Read word
                int studentAge = tokenScanner.nextInt(); // Read int
                double studentGpa = tokenScanner.nextDouble(); // Read double

                System.out.printf("  %-10s  %d    %.2f%n", studentName, studentAge, studentGpa);
            }

            tokenScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("  File not found: " + e.getMessage());
        }

        console.close();
    }
}

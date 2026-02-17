import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * ConsoleAndFileIO.java
 * Topic 5.1: Console and File I/O
 * 
 * Demonstrates:
 * - Console Output: System.out (print, println, printf)
 * - Console Input: System.in.read() and Scanner
 * - File I/O basics: Creating a file using File class
 */
public class ConsoleAndFileIO {
    public static void main(String[] args) {

        // ==========================================
        // PART 1: Console Output
        // ==========================================
        System.out.println("========== CONSOLE OUTPUT ==========");

        // print() - no newline at end
        System.out.print("Hello ");
        System.out.print("World!");
        System.out.println(); // just a newline

        // println() - adds newline at end
        System.out.println("This is println - adds newline automatically.");

        // printf() - formatted output
        String name = "Ram";
        int age = 20;
        double gpa = 3.85;
        System.out.printf("Name: %s, Age: %d, GPA: %.2f%n", name, age, gpa);

        // ==========================================
        // PART 2: Console Input (Scanner)
        // ==========================================
        System.out.println("\n========== CONSOLE INPUT ==========");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String inputName = sc.nextLine();

        System.out.print("Enter your age: ");
        int inputAge = sc.nextInt();

        System.out.println("Hello " + inputName + ", you are " + inputAge + " years old.");

        // ==========================================
        // PART 3: Basic File I/O
        // ==========================================
        System.out.println("\n========== FILE I/O BASICS ==========");

        try {
            File file = new File("demo.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                System.out.println("Location: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}

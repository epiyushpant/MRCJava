import java.io.RandomAccessFile;
import java.io.IOException;

/**
 * RandomAccessDemo.java
 * Topic 5.7: Random Access Files
 * 
 * Demonstrates:
 * - RandomAccessFile for reading and writing
 * - seek() to jump to any position in the file
 * - Reading/writing primitive types (int, double, UTF strings)
 * - Modifying data at a specific position
 * 
 * Unlike sequential streams (FileReader, FileWriter), RandomAccessFile
 * lets you jump to any byte position in the file using seek().
 */
public class RandomAccessDemo {
    public static void main(String[] args) {

        // ==========================================
        // PART 1: Write Records
        // ==========================================
        System.out.println("===== PART 1: Writing Records =====");

        try {
            // "rw" = read and write mode
            RandomAccessFile raf = new RandomAccessFile("student_records.dat", "rw");

            // Write Record 1
            raf.writeUTF("Ram"); // Writes string (with length prefix)
            raf.writeInt(20); // Writes 4 bytes
            raf.writeDouble(3.85); // Writes 8 bytes

            // Save position where Record 2 starts
            long record2Start = raf.getFilePointer();

            // Write Record 2
            raf.writeUTF("Sita");
            raf.writeInt(22);
            raf.writeDouble(3.90);

            // Write Record 3
            raf.writeUTF("Hari");
            raf.writeInt(19);
            raf.writeDouble(3.60);

            System.out.println("3 records written.");
            System.out.println("File size: " + raf.length() + " bytes");
            System.out.println("Record 2 starts at byte: " + record2Start);

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 2: Read Sequentially
        // ==========================================
        System.out.println("\n===== PART 2: Read All Records =====");

        try {
            RandomAccessFile raf = new RandomAccessFile("student_records.dat", "r");

            // Read from beginning
            raf.seek(0);

            for (int i = 1; i <= 3; i++) {
                String name = raf.readUTF();
                int age = raf.readInt();
                double gpa = raf.readDouble();
                System.out.println("  Record " + i + ": " + name + ", Age=" + age + ", GPA=" + gpa);
            }

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 3: Random Access (Jump to position)
        // ==========================================
        System.out.println("\n===== PART 3: Random Access - Read Specific Record =====");

        try {
            RandomAccessFile raf = new RandomAccessFile("student_records.dat", "r");

            // Skip Record 1, jump to Record 2
            // Record 1 size: "Ram" (2+3=5 bytes) + int(4) + double(8) = 17 bytes
            raf.seek(17);

            System.out.println("  Jumped to byte position 17 (Record 2):");
            System.out.println("  Name: " + raf.readUTF());
            System.out.println("  Age : " + raf.readInt());
            System.out.println("  GPA : " + raf.readDouble());

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ==========================================
        // PART 4: Modify Data (Writing Integers)
        // ==========================================
        System.out.println("\n===== PART 4: Modifying Data at Position =====");

        try {
            RandomAccessFile raf = new RandomAccessFile("numbers.dat", "rw");

            // Write 5 integers: 10, 20, 30, 40, 50
            for (int i = 1; i <= 5; i++) {
                raf.writeInt(i * 10);
            }

            // Read before modification
            raf.seek(0);
            System.out.print("  Before: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();

            // Modify 3rd number: position = 2 * 4 = 8 (each int = 4 bytes)
            raf.seek(8);
            raf.writeInt(999); // Replace 30 with 999

            // Read after modification
            raf.seek(0);
            System.out.print("  After : ");
            for (int i = 0; i < 5; i++) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();
            System.out.println("  (3rd value changed from 30 to 999)");

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

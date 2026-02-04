/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                   LESSON 7: STATIC VS INSTANCE MEMBERS                    ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  STATIC: Belongs to the CLASS, shared by all objects                      ║
 * ║  INSTANCE: Belongs to each OBJECT, unique for every instance              ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

class Student {
    // ══════════════════════════════════════════════════════════════════════════
    // STATIC VARIABLE - One copy shared by ALL objects
    // ══════════════════════════════════════════════════════════════════════════
    static String collegeName = "MRC College";  // Same for all students
    static int totalStudents = 0;               // Counter for all students

    // ══════════════════════════════════════════════════════════════════════════
    // INSTANCE VARIABLES - Unique for each object
    // ══════════════════════════════════════════════════════════════════════════
    String name;       // Different for each student
    int rollNumber;    // Different for each student
    double marks;      // Different for each student

    // Constructor
    Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        totalStudents++;  // Increment shared counter
        System.out.println("📚 Student #" + totalStudents + " created: " + name);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // STATIC METHOD - Can be called without creating object
    // ══════════════════════════════════════════════════════════════════════════
    static void displayCollegeInfo() {
        System.out.println("🏫 College: " + collegeName);
        System.out.println("   Total Students Enrolled: " + totalStudents);
        
        // ❌ CANNOT access instance variables from static method
        // System.out.println(name);  // Error: non-static variable cannot be referenced
    }

    static int getTotalStudents() {
        return totalStudents;
    }

    // ══════════════════════════════════════════════════════════════════════════
    // INSTANCE METHOD - Requires object to call
    // ══════════════════════════════════════════════════════════════════════════
    void displayStudentInfo() {
        // ✅ Instance method CAN access both static and instance variables
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ Name: " + name);
        System.out.println("│ Roll No: " + rollNumber);
        System.out.println("│ Marks: " + marks);
        System.out.println("│ College: " + collegeName);  // Can access static
        System.out.println("└─────────────────────────────────┘");
    }

    double getMarks() {
        return marks;
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 2: Bank Counter System
// ════════════════════════════════════════════════════════════════════════════

class BankCounter {
    // Static - Same for all counters
    static String bankName = "Nepal Bank";
    static int totalTokensIssued = 0;
    static double dailyTransactionTotal = 0;

    // Instance - Unique per counter
    int counterNumber;
    String cashierName;
    int customersServed;
    double counterTransactions;

    BankCounter(int counterNumber, String cashierName) {
        this.counterNumber = counterNumber;
        this.cashierName = cashierName;
        this.customersServed = 0;
        this.counterTransactions = 0;
    }

    // Static method - Works at bank level
    static int issueToken() {
        totalTokensIssued++;
        System.out.println("🎫 Token #" + totalTokensIssued + " issued");
        return totalTokensIssued;
    }

    static void displayBankSummary() {
        System.out.println("\n═══ " + bankName + " Daily Summary ═══");
        System.out.println("Total Tokens Issued: " + totalTokensIssued);
        System.out.println("Total Transactions: Rs. " + dailyTransactionTotal);
    }

    // Instance method - Works at counter level
    void serveCustomer(double amount) {
        customersServed++;
        counterTransactions += amount;
        dailyTransactionTotal += amount;  // Update static variable too
        System.out.println("Counter " + counterNumber + " (" + cashierName + 
                          "): Processed Rs. " + amount);
    }

    void displayCounterStats() {
        System.out.println("Counter " + counterNumber + " - " + cashierName);
        System.out.println("  Customers Served: " + customersServed);
        System.out.println("  Counter Total: Rs. " + counterTransactions);
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class StaticVsInstanceDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           LESSON 7: STATIC VS INSTANCE DEMO                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Static vs Instance Variables
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Static vs Instance Variables ───\n");

        // Accessing static BEFORE creating any objects
        System.out.println("Before creating students:");
        Student.displayCollegeInfo();  // Static - called using ClassName

        System.out.println();

        // Create student objects
        Student s1 = new Student("Ram", 101, 85);
        Student s2 = new Student("Sita", 102, 92);
        Student s3 = new Student("Hari", 103, 78);

        System.out.println();
        s1.displayStudentInfo();
        s2.displayStudentInfo();

        // Static variable is SHARED
        System.out.println("\n--- Static Variable Demonstration ---");
        System.out.println("Total students (via class): " + Student.totalStudents);
        System.out.println("Total students (via s1): " + s1.totalStudents);  // Same value
        System.out.println("Total students (via s2): " + s2.totalStudents);  // Same value
        System.out.println("^ All access the SAME variable!");

        // Changing static variable affects all
        System.out.println("\nChanging college name via Student.collegeName = 'ABC College'");
        Student.collegeName = "ABC College";
        System.out.println("s1's college: " + s1.collegeName);
        System.out.println("s2's college: " + s2.collegeName);
        System.out.println("s3's college: " + s3.collegeName);
        System.out.println("^ All changed to 'ABC College'!");

        // Instance variables are UNIQUE
        System.out.println("\n--- Instance Variable Demonstration ---");
        System.out.println("s1 marks: " + s1.marks + ", s2 marks: " + s2.marks);
        s1.marks = 95;
        System.out.println("After s1.marks = 95:");
        System.out.println("s1 marks: " + s1.marks + ", s2 marks: " + s2.marks);
        System.out.println("^ Only s1's marks changed!");

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Bank Counter System
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 2: Bank Counter System ───\n");

        // Issue tokens (static method - no object needed)
        BankCounter.issueToken();
        BankCounter.issueToken();
        BankCounter.issueToken();

        System.out.println();

        // Create counters
        BankCounter counter1 = new BankCounter(1, "Ram Bahadur");
        BankCounter counter2 = new BankCounter(2, "Sita Kumari");

        // Serve customers
        counter1.serveCustomer(5000);
        counter1.serveCustomer(3000);
        counter2.serveCustomer(10000);
        counter2.serveCustomer(2000);
        counter1.serveCustomer(7000);

        System.out.println();
        counter1.displayCounterStats();
        System.out.println();
        counter2.displayCounterStats();

        BankCounter.displayBankSummary();

        // ══════════════════════════════════════════════════════════════════════
        // COMPARISON TABLE
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("              STATIC VS INSTANCE COMPARISON                    ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("┌───────────────────────┬─────────────────────────────────────┐");
        System.out.println("│ STATIC                │ INSTANCE                            │");
        System.out.println("├───────────────────────┼─────────────────────────────────────┤");
        System.out.println("│ Belongs to CLASS      │ Belongs to OBJECT                   │");
        System.out.println("│ One copy shared       │ Each object has own copy            │");
        System.out.println("│ Call: ClassName.x     │ Call: objectName.x                  │");
        System.out.println("│ Created when class    │ Created when object is              │");
        System.out.println("│ is loaded             │ instantiated                        │");
        System.out.println("│ Cannot use 'this'     │ Can use 'this'                      │");
        System.out.println("│ Can only access       │ Can access both static              │");
        System.out.println("│ static members        │ and instance members                │");
        System.out.println("│ Use: Counters, utils  │ Use: Object-specific data           │");
        System.out.println("└───────────────────────┴─────────────────────────────────────┘");
    }
}

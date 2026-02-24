/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                     LESSON 1: CLASSES AND OBJECTS                         ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Topics Covered:                                                          ║
 * ║  • What is a Class? (Blueprint/Template)                                  ║
 * ║  • What is an Object? (Instance of a Class)                               ║
 * ║  • Attributes (Fields/Properties)                                         ║
 * ║  • Methods (Behaviors/Functions)                                          ║
 * ║  • Constructors (Default and Parameterized)                               ║
 * ║  • The 'this' keyword                                                     ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 1: Basic Class with Attributes and Methods
// ════════════════════════════════════════════════════════════════════════════

/**
 * A class is like a BLUEPRINT.
 * Think of it as a recipe - it defines what something will have and do.
 */
class Student {
    // ──────────────────────────────────────────────────────────────────────────
    // ATTRIBUTES (Fields/Properties) - What the object HAS
    // ──────────────────────────────────────────────────────────────────────────
    String name;        // Name of the student
    int rollNumber;     // Roll number
    double marks;       // Marks obtained

    // ──────────────────────────────────────────────────────────────────────────
    // DEFAULT CONSTRUCTOR - Called when no arguments are provided
    // ──────────────────────────────────────────────────────────────────────────
    Student() {
        // This is called when you write: new Student()
        name = "Unknown";
        rollNumber = 0;
        marks = 0.0;
        System.out.println("📦 Default constructor called - Student created with default values");
    }

    // ──────────────────────────────────────────────────────────────────────────
    // PARAMETERIZED CONSTRUCTOR - Called with arguments
    // ──────────────────────────────────────────────────────────────────────────
    Student(String name, int rollNumber, double marks) {
        // 'this' refers to the current object
        // Used to distinguish between parameter and class attribute
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        System.out.println("📦 Parameterized constructor called - Student '" + name + "' created");
    }

    // ──────────────────────────────────────────────────────────────────────────
    // METHODS (Behaviors) - What the object CAN DO
    // ──────────────────────────────────────────────────────────────────────────
    
    void displayInfo() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ Student Information             │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│ Name: " + name);
        System.out.println("│ Roll Number: " + rollNumber);
        System.out.println("│ Marks: " + marks);
        System.out.println("└─────────────────────────────────┘");
    }

    String getGrade() {
        if (marks >= 80) return "A";
        else if (marks >= 60) return "B";
        else if (marks >= 40) return "C";
        else return "F";
    }

    boolean isPassing() {
        return marks >= 40;
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 2: Constructor Chaining with 'this()'
// ════════════════════════════════════════════════════════════════════════════

class Book {
    String title;
    String author;
    double price;
    int pages;

    // Constructor 1: No arguments
    Book() {
        this("Unknown Title", "Unknown Author", 0.0, 0);  // Calls Constructor 4
        System.out.println("📚 Book created with all defaults");
    }

    // Constructor 2: Only title
    Book(String title) {
        this(title, "Unknown Author", 0.0, 0);  // Calls Constructor 4
        System.out.println("📚 Book created with title only");
    }

    // Constructor 3: Title and author
    Book(String title, String author) {
        this(title, author, 0.0, 0);  // Calls Constructor 4
        System.out.println("📚 Book created with title and author");
    }

    // Constructor 4: All parameters (main constructor)
    Book(String title, String author, double price, int pages) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.pages = pages;
    }

    void displayBook() {
        System.out.println("📖 \"" + title + "\" by " + author + 
                          " | Price: Rs." + price + " | Pages: " + pages);
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS - Demonstration
// ════════════════════════════════════════════════════════════════════════════

public class ClassesAndObjectsDemo {
    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           LESSON 1: CLASSES AND OBJECTS DEMO                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Creating Objects Using Different Constructors
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Creating Student Objects ───\n");

        // Using default constructor
        Student s1 = new Student();
        s1.displayInfo();

        System.out.println();

        // Using parameterized constructor
        Student s2 = new Student("Ram Sharma", 101, 85.5);
        s2.displayInfo();
        System.out.println("Grade: " + s2.getGrade());
        System.out.println("Passing: " + s2.isPassing());

        System.out.println();

        Student s3 = new Student("Sita Thapa", 102, 35.0);
        s3.displayInfo();
        System.out.println("Grade: " + s3.getGrade());
        System.out.println("Passing: " + s3.isPassing());

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Constructor Chaining
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 2: Constructor Chaining with Books ───\n");

        Book b1 = new Book();
        b1.displayBook();

        Book b2 = new Book("Java Programming");
        b2.displayBook();

        Book b3 = new Book("OOP Concepts", "John Doe");
        b3.displayBook();

        Book b4 = new Book("Advanced Java", "Jane Smith", 599.99, 450);
        b4.displayBook();

        // ══════════════════════════════════════════════════════════════════════
        // PART 3: Multiple Objects, Same Class
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 3: Objects are Independent ───\n");

        Student student1 = new Student("Hari", 1, 90);
        Student student2 = new Student("Gita", 2, 75);

        // Modifying one object doesn't affect the other
        student1.marks = 95;  // Only affects student1

        System.out.println("Student1 marks: " + student1.marks);  // 95
        System.out.println("Student2 marks: " + student2.marks);  // 75 (unchanged)

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("                    KEY TAKEAWAYS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("• Class = Blueprint/Template");
        System.out.println("• Object = Instance of a class (created with 'new')");
        System.out.println("• Constructor = Special method called when object is created");
        System.out.println("• 'this' keyword = Refers to current object");
        System.out.println("• Each object has its own copy of instance variables");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                         PRACTICE EXERCISES                                ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║ 1. Create a class 'Car' with attributes: brand, model, year, price       ║
 * ║    Add methods: displayInfo(), isVintage() (year < 2000)                  ║
 * ║                                                                           ║
 * ║ 2. Create a class 'Rectangle' with width and height                      ║
 * ║    Add methods: calculateArea(), calculatePerimeter()                    ║
 * ║                                                                           ║
 * ║ 3. Create a class 'BankAccount' with accountNumber, holderName, balance  ║
 * ║    Add constructors: default, with all parameters                        ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

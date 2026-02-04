/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                        LESSON 4: POLYMORPHISM                             ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Definition: "Many Forms" - Same name, different behaviors                ║
 * ║  Two Types:                                                               ║
 * ║  1. Compile-time (Method Overloading) - Same class, different params      ║
 * ║  2. Runtime (Method Overriding) - Parent-child, same signature            ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// PART 1: METHOD OVERLOADING (Compile-time Polymorphism)
// Same method name, DIFFERENT parameters in the SAME class
// ════════════════════════════════════════════════════════════════════════════

class Calculator {
    // Method 1: Two int parameters
    int add(int a, int b) {
        System.out.println("Called: add(int, int)");
        return a + b;
    }

    // Method 2: Three int parameters (different NUMBER of params)
    int add(int a, int b, int c) {
        System.out.println("Called: add(int, int, int)");
        return a + b + c;
    }

    // Method 3: Two double parameters (different TYPE of params)
    double add(double a, double b) {
        System.out.println("Called: add(double, double)");
        return a + b;
    }

    // Method 4: Different order of params
    String add(String text, int number) {
        System.out.println("Called: add(String, int)");
        return text + number;
    }

    String add(int number, String text) {
        System.out.println("Called: add(int, String)");
        return number + text;
    }
}

// Real-world example: Area calculation
class AreaCalculator {
    // Calculate area of square
    double calculateArea(double side) {
        System.out.println("📐 Calculating area of SQUARE");
        return side * side;
    }

    // Calculate area of rectangle
    double calculateArea(double length, double width) {
        System.out.println("📐 Calculating area of RECTANGLE");
        return length * width;
    }

    // Calculate area of circle
    double calculateArea(double radius, boolean isCircle) {
        System.out.println("📐 Calculating area of CIRCLE");
        return Math.PI * radius * radius;
    }

    // Calculate area of triangle
    double calculateArea(double base, double height, String shape) {
        System.out.println("📐 Calculating area of TRIANGLE");
        return 0.5 * base * height;
    }
}

// ════════════════════════════════════════════════════════════════════════════
// PART 2: METHOD OVERRIDING (Runtime Polymorphism)
// Same method signature in Parent and Child classes
// ════════════════════════════════════════════════════════════════════════════

class NepalBank {
    String bankName = "Generic Nepal Bank";
    
    double getInterestRate() {
        return 0.0;  // Base rate
    }
    
    void displayInfo() {
        System.out.println("This is a bank registered in Nepal");
    }
}

class NABILBank extends NepalBank {
    NABILBank() {
        bankName = "NABIL Bank";
    }
    
    @Override  // Annotation indicates we're overriding parent method
    double getInterestRate() {
        return 8.0;  // NABIL specific rate
    }
    
    @Override
    void displayInfo() {
        System.out.println("NABIL Bank - One of the leading banks in Nepal");
    }
}

class NICAsiaBank extends NepalBank {
    NICAsiaBank() {
        bankName = "NIC Asia Bank";
    }
    
    @Override
    double getInterestRate() {
        return 7.5;  // NIC Asia specific rate
    }
    
    @Override
    void displayInfo() {
        System.out.println("NIC Asia Bank - Digital Banking Pioneer");
    }
}

class GlobalIMEBank extends NepalBank {
    GlobalIMEBank() {
        bankName = "Global IME Bank";
    }
    
    @Override
    double getInterestRate() {
        return 7.8;
    }
    // displayInfo() NOT overridden - will use parent's version
}

// ════════════════════════════════════════════════════════════════════════════
// PART 3: RUNTIME POLYMORPHISM (Dynamic Method Dispatch)
// Parent reference pointing to Child object
// ════════════════════════════════════════════════════════════════════════════

class AnimalSound {
    void makeSound() {
        System.out.println("Animal makes a sound...");
    }
}

class DogSound extends AnimalSound {
    @Override
    void makeSound() {
        System.out.println("Dog says: Bhau Bhau! 🐕");
    }
}

class CatSound extends AnimalSound {
    @Override
    void makeSound() {
        System.out.println("Cat says: Meow! 🐱");
    }
}

class CowSound extends AnimalSound {
    @Override
    void makeSound() {
        System.out.println("Cow says: Moo! 🐄");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              LESSON 4: POLYMORPHISM DEMO                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Method Overloading
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Method OVERLOADING (Compile-time) ───\n");
        
        Calculator calc = new Calculator();
        
        System.out.println("Result: " + calc.add(5, 3));
        System.out.println("Result: " + calc.add(5, 3, 2));
        System.out.println("Result: " + calc.add(5.5, 3.5));
        System.out.println("Result: " + calc.add("Roll No: ", 101));
        System.out.println("Result: " + calc.add(101, " is the roll number"));

        System.out.println("\n--- Area Calculator ---");
        AreaCalculator area = new AreaCalculator();
        System.out.println("Square (5): " + area.calculateArea(5));
        System.out.println("Rectangle (4x6): " + area.calculateArea(4, 6));
        System.out.println("Circle (r=3): " + area.calculateArea(3, true));
        System.out.println("Triangle (b=4, h=5): " + area.calculateArea(4, 5, "triangle"));

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Method Overriding
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 2: Method OVERRIDING (Runtime) ───\n");
        
        NABILBank nabil = new NABILBank();
        NICAsiaBank nicAsia = new NICAsiaBank();
        GlobalIMEBank globalIME = new GlobalIMEBank();
        
        System.out.println(nabil.bankName + " Interest Rate: " + nabil.getInterestRate() + "%");
        nabil.displayInfo();
        
        System.out.println();
        System.out.println(nicAsia.bankName + " Interest Rate: " + nicAsia.getInterestRate() + "%");
        nicAsia.displayInfo();
        
        System.out.println();
        System.out.println(globalIME.bankName + " Interest Rate: " + globalIME.getInterestRate() + "%");
        globalIME.displayInfo();  // Uses parent's version (not overridden)

        // ══════════════════════════════════════════════════════════════════════
        // PART 3: Runtime Polymorphism (Dynamic Method Dispatch)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 3: Dynamic Method Dispatch ───\n");
        System.out.println("Parent reference → Child object:\n");
        
        // Parent reference, Child object
        AnimalSound animal;
        
        animal = new DogSound();   // Parent ref points to Dog
        animal.makeSound();        // Calls Dog's makeSound()
        
        animal = new CatSound();   // Parent ref points to Cat
        animal.makeSound();        // Calls Cat's makeSound()
        
        animal = new CowSound();   // Parent ref points to Cow
        animal.makeSound();        // Calls Cow's makeSound()

        System.out.println("\n--- Using Array of Parent Type ---");
        AnimalSound[] animals = {new DogSound(), new CatSound(), new CowSound()};
        for (AnimalSound a : animals) {
            a.makeSound();  // Calls appropriate child method
        }

        // ══════════════════════════════════════════════════════════════════════
        // COMPARISON TABLE
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("          OVERLOADING vs OVERRIDING COMPARISON                 ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("┌─────────────────────┬─────────────────────────────────────┐");
        System.out.println("│ METHOD OVERLOADING  │ METHOD OVERRIDING                   │");
        System.out.println("├─────────────────────┼─────────────────────────────────────┤");
        System.out.println("│ Same class          │ Parent-child classes (inheritance) │");
        System.out.println("│ Different params    │ SAME method signature               │");
        System.out.println("│ Compile-time        │ Runtime                             │");
        System.out.println("│ Return type can     │ Return type must be same            │");
        System.out.println("│ differ              │ (or covariant)                      │");
        System.out.println("│ Also called:        │ Also called:                        │");
        System.out.println("│ Static binding      │ Dynamic binding                     │");
        System.out.println("└─────────────────────┴─────────────────────────────────────┘");
    }
}

/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                    LESSON 8: DIAMOND PROBLEM                              ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  What is the Diamond Problem?                                             ║
 * ║  When a class inherits from two classes that have the same method,        ║
 * ║  which method should the child class inherit? This is AMBIGUOUS!          ║
 * ║                                                                           ║
 * ║  Why Java doesn't allow Multiple Inheritance with Classes?                ║
 * ║  To avoid this ambiguity/confusion!                                       ║
 * ║                                                                           ║
 * ║  Solutions:                                                               ║
 * ║  1. Use Interfaces with explicit override                                 ║
 * ║  2. Use Composition (has-a relationship)                                  ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// THE PROBLEM: Why Multiple Class Inheritance is NOT Allowed
// ════════════════════════════════════════════════════════════════════════════

/*
 * ILLEGAL CODE - This would cause Diamond Problem:
 * 
 * class ParentA {
 *     void speak() { System.out.println("ParentA speaks"); }
 * }
 * 
 * class ParentB {
 *     void speak() { System.out.println("ParentB speaks"); }
 * }
 * 
 * class Child extends ParentA, ParentB {  // ❌ COMPILE ERROR!
 *     // Which speak() should Child inherit? ParentA or ParentB?
 *     // This is the DIAMOND PROBLEM!
 * }
 * 
 * Visual representation:
 *        Grandparent
 *          /     \
 *     ParentA   ParentB   <-- Both have speak() method
 *          \     /
 *           Child         <-- Which speak() to inherit?
 */

// ════════════════════════════════════════════════════════════════════════════
// SOLUTION 1: Using INTERFACES with Explicit Override
// ════════════════════════════════════════════════════════════════════════════

interface SpeakableA {
    default void speak() {
        System.out.println("SpeakableA: Hello!");
    }
}

interface SpeakableB {
    default void speak() {
        System.out.println("SpeakableB: Hi there!");
    }
}

// A class CAN implement multiple interfaces
class Speaker implements SpeakableA, SpeakableB {
    
    // Java FORCES you to override when both interfaces have same default method
    @Override
    public void speak() {
        // Option 1: Call one interface's method
        System.out.println("--- Calling SpeakableA ---");
        SpeakableA.super.speak();
        
        // Option 2: Call other interface's method
        System.out.println("--- Calling SpeakableB ---");
        SpeakableB.super.speak();
        
        // Option 3: Provide completely new implementation
        System.out.println("--- Speaker's own implementation ---");
        System.out.println("Speaker says: Namaste! 🙏");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// SOLUTION 2: Using COMPOSITION (Has-A Relationship)
// ════════════════════════════════════════════════════════════════════════════

class BarkBehavior {
    void makeSound() {
        System.out.println("🐕 Bark behavior: Woof Woof!");
    }
}

class MeowBehavior {
    void makeSound() {
        System.out.println("🐱 Meow behavior: Meow Meow!");
    }
}

// Instead of inheriting, we COMPOSE (have as members)
class SuperPet {
    // Has-A relationship instead of Is-A
    private BarkBehavior barkBehavior = new BarkBehavior();
    private MeowBehavior meowBehavior = new MeowBehavior();
    private String petName;

    SuperPet(String petName) {
        this.petName = petName;
    }

    // Delegate to the appropriate behavior
    void bark() {
        System.out.print(petName + " uses: ");
        barkBehavior.makeSound();
    }

    void meow() {
        System.out.print(petName + " uses: ");
        meowBehavior.makeSound();
    }

    void makeAllSounds() {
        System.out.println(petName + " is a super pet that can:");
        bark();
        meow();
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 3: Practical Example - Bank with Multiple Features
// ════════════════════════════════════════════════════════════════════════════

interface Loanable {
    default void processLoan(double amount) {
        System.out.println("📋 Default loan processing for Rs. " + amount);
    }
    void calculateInterest();
}

interface Saveable {
    default void processSaving(double amount) {
        System.out.println("💰 Default saving processing for Rs. " + amount);
    }
    void calculateInterest();  // Same method name as Loanable!
}

class UniversalBank implements Loanable, Saveable {
    
    // MUST override calculateInterest since both interfaces have it
    @Override
    public void calculateInterest() {
        System.out.println("🏦 Universal Bank: Combined interest calculation");
        System.out.println("   Loan Interest: 12%");
        System.out.println("   Savings Interest: 6%");
    }
    
    // Can still use default methods from interfaces
    void demonstrateServices() {
        processLoan(50000);    // From Loanable
        processSaving(100000); // From Saveable
        calculateInterest();   // Our implementation
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class DiamondProblemDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║             LESSON 8: DIAMOND PROBLEM DEMO                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: The Problem Explained
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Understanding the Problem ───\n");
        System.out.println("The Diamond Problem occurs when:");
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│           Grandparent                              │");
        System.out.println("│              ╱   ╲                                 │");
        System.out.println("│        ParentA   ParentB   ← Both have speak()    │");
        System.out.println("│              ╲   ╱                                 │");
        System.out.println("│             Child          ← Which speak() to use?│");
        System.out.println("│                                                    │");
        System.out.println("│  Java's Solution: Disallow multiple class         │");
        System.out.println("│  inheritance. Use INTERFACES instead!             │");
        System.out.println("└────────────────────────────────────────────────────┘\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Solution with Interfaces
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 2: Solution - Interfaces ───\n");

        Speaker speaker = new Speaker();
        speaker.speak();

        // ══════════════════════════════════════════════════════════════════════
        // PART 3: Solution with Composition
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 3: Solution - Composition ───\n");

        SuperPet superPet = new SuperPet("Tommy");
        superPet.makeAllSounds();

        // ══════════════════════════════════════════════════════════════════════
        // PART 4: Practical Example - Bank
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 4: Practical Bank Example ───\n");

        UniversalBank bank = new UniversalBank();
        bank.demonstrateServices();

        // ══════════════════════════════════════════════════════════════════════
        // KEY TAKEAWAYS
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("                    KEY TAKEAWAYS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("• Java prevents: class C extends A, B (Multiple Inheritance)");
        System.out.println("• Reason: Ambiguity in which method to inherit");
        System.out.println("• Solution 1: Implement multiple interfaces");
        System.out.println("  → Must explicitly override conflicting methods");
        System.out.println("• Solution 2: Use composition (has-a) instead of inheritance");
        System.out.println("  → More flexible, avoids coupling");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

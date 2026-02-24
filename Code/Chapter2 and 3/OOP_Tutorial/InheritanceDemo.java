/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                        LESSON 3: INHERITANCE                              ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Definition: Child class inherits properties/behaviors from parent class ║
 * ║  Topics: extends, super keyword, Types of Inheritance                    ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// PARENT CLASS (Superclass)
class Animal {
    protected String name;
    protected int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("🐾 Animal constructor called for: " + name);
    }

    void eat() {
        System.out.println(name + " is eating...");
    }

    void sleep() {
        System.out.println(name + " is sleeping... 💤");
    }
}

// CHILD CLASS - inherits from Animal
class Dog extends Animal {
    private String breed;

    Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor FIRST
        this.breed = breed;
        System.out.println("🐕 Dog constructor called");
    }

    void bark() {
        System.out.println(name + " says: Bhau Bhau! 🐕");
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Breed: " + breed);
    }
}

// Another CHILD CLASS
class Cat extends Animal {
    Cat(String name, int age) {
        super(name, age);
        System.out.println("🐱 Cat constructor called");
    }

    void meow() {
        System.out.println(name + " says: Meow! 🐱");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MULTI-LEVEL INHERITANCE: Grandparent → Parent → Child
// ════════════════════════════════════════════════════════════════════════════

class NepalBankAccount {
    protected String accountNumber;
    protected double balance;

    NepalBankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        System.out.println("🏦 [Level 1] NepalBankAccount created");
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: Rs. " + amount);
    }
}

class SavingsAccount extends NepalBankAccount {
    protected double interestRate;

    SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
        System.out.println("💰 [Level 2] SavingsAccount created");
    }

    void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest added: Rs. " + interest);
    }
}

class PremiumSavingsAccount extends SavingsAccount {
    private double bonusRate;

    PremiumSavingsAccount(String accountNumber, double balance, double interestRate, double bonusRate) {
        super(accountNumber, balance, interestRate);
        this.bonusRate = bonusRate;
        System.out.println("⭐ [Level 3] PremiumSavingsAccount created");
    }

    @Override
    void addInterest() {
        super.addInterest();  // Regular interest
        double bonus = balance * (bonusRate / 100);
        balance += bonus;
        System.out.println("Bonus added: Rs. " + bonus);
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               LESSON 3: INHERITANCE DEMO                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // PART 1: Basic Inheritance
        System.out.println("─── PART 1: Single Inheritance ───\n");
        
        Dog tommy = new Dog("Tommy", 3, "Labrador");
        System.out.println();
        tommy.displayInfo();
        tommy.eat();   // Inherited from Animal
        tommy.bark();  // Dog's own method

        System.out.println();
        
        Cat whiskers = new Cat("Whiskers", 2);
        whiskers.eat();   // Inherited
        whiskers.meow();  // Cat's own

        // PART 2: Multi-Level Inheritance
        System.out.println("\n─── PART 2: Multi-Level Inheritance ───\n");
        
        PremiumSavingsAccount account = new PremiumSavingsAccount("NABIL-001", 100000, 8.0, 2.0);
        System.out.println("\nBalance: Rs. " + account.balance);
        account.deposit(50000);
        account.addInterest();
        System.out.println("Final Balance: Rs. " + account.balance);

        // KEY TAKEAWAYS
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("                    KEY TAKEAWAYS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("• extends keyword: class Child extends Parent");
        System.out.println("• super(): Call parent constructor (must be first line)");
        System.out.println("• super.method(): Call parent's method");
        System.out.println("• Types: Single, Multi-level, Hierarchical");
        System.out.println("• Multiple inheritance NOT supported with classes");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

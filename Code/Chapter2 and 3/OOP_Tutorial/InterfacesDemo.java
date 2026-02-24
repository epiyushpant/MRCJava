/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                         LESSON 6: INTERFACES                              ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Definition: A contract that defines what a class MUST do                 ║
 * ║  Key Points:                                                              ║
 * ║  • All methods are public and abstract by default                         ║
 * ║  • All variables are public, static, and final (constants)                ║
 * ║  • A class can implement MULTIPLE interfaces                              ║
 * ║  • Use 'implements' keyword (not 'extends')                               ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 1: Payment System Interface
// ════════════════════════════════════════════════════════════════════════════

interface Payment {
    // All methods in interface are public abstract by default
    void pay(double amount);
    boolean verifyPayment();
    void generateReceipt();
    
    // Constants (public static final)
    double SERVICE_CHARGE = 10.0;
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    private double balance = 0;

    CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        double total = amount + SERVICE_CHARGE;
        balance = total;
        System.out.println("💳 Paid Rs. " + total + " via Credit Card");
        System.out.println("   (Amount: " + amount + " + Service Charge: " + SERVICE_CHARGE + ")");
    }

    @Override
    public boolean verifyPayment() {
        System.out.println("✅ Credit Card payment verified");
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("📄 Credit Card Receipt Generated");
        System.out.println("   Card: **** **** **** " + cardNumber.substring(cardNumber.length()-4));
    }
}

class MobileBanking implements Payment {
    private String phoneNumber;

    MobileBanking(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("📱 Paid Rs. " + (amount + SERVICE_CHARGE) + " via Mobile Banking");
    }

    @Override
    public boolean verifyPayment() {
        System.out.println("✅ OTP verified for " + phoneNumber);
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("📄 e-Receipt sent to " + phoneNumber);
    }
}

class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("💵 Paid Rs. " + amount + " in Cash (No service charge)");
    }

    @Override
    public boolean verifyPayment() {
        System.out.println("✅ Cash counted and verified");
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("📄 Printed receipt from billing machine");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 2: Multiple Interface Implementation
// ════════════════════════════════════════════════════════════════════════════

interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

interface Faxable {
    void fax(String destination);
}

// A class can implement MULTIPLE interfaces
class MultiFunctionPrinter implements Printable, Scannable, Faxable {
    private String model;

    MultiFunctionPrinter(String model) {
        this.model = model;
    }

    @Override
    public void print() {
        System.out.println("🖨️  " + model + ": Printing document...");
    }

    @Override
    public void scan() {
        System.out.println("📠 " + model + ": Scanning document...");
    }

    @Override
    public void fax(String destination) {
        System.out.println("📤 " + model + ": Faxing to " + destination + "...");
    }
}

// This class only implements one interface
class BasicPrinter implements Printable {
    @Override
    public void print() {
        System.out.println("🖨️  Basic Printer: Printing...");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 3: Interface with Default Methods (Java 8+)
// ════════════════════════════════════════════════════════════════════════════

interface Vehicle {
    void start();
    void stop();
    
    // Default method - has implementation
    default void honk() {
        System.out.println("🔊 BEEP BEEP!");
    }
    
    // Static method
    static void showInfo() {
        System.out.println("ℹ️  Vehicle Interface v1.0");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("🚗 Car engine started");
    }

    @Override
    public void stop() {
        System.out.println("🚗 Car stopped");
    }
    // Note: honk() not overridden, uses default
}

class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("🏍️  Motorcycle started");
    }

    @Override
    public void stop() {
        System.out.println("🏍️  Motorcycle stopped");
    }

    @Override
    public void honk() {
        System.out.println("🔊 Beep beep! (Motorcycle horn)");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class InterfacesDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               LESSON 6: INTERFACES DEMO                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Payment System
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Payment System ───\n");

        Payment[] payments = {
            new CreditCardPayment("1234567890123456"),
            new MobileBanking("9841234567"),
            new CashPayment()
        };

        double[] amounts = {5000, 2500, 1000};

        for (int i = 0; i < payments.length; i++) {
            payments[i].pay(amounts[i]);
            payments[i].verifyPayment();
            payments[i].generateReceipt();
            System.out.println();
        }

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Multiple Interfaces
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 2: Multiple Interface Implementation ───\n");

        MultiFunctionPrinter mfp = new MultiFunctionPrinter("HP LaserJet Pro");
        mfp.print();
        mfp.scan();
        mfp.fax("01-4123456");

        System.out.println();
        
        BasicPrinter bp = new BasicPrinter();
        bp.print();
        // bp.scan(); ❌ Error - BasicPrinter doesn't implement Scannable

        // ══════════════════════════════════════════════════════════════════════
        // PART 3: Default and Static Methods
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 3: Default & Static Methods ───\n");

        Vehicle.showInfo();  // Static method
        System.out.println();

        Car car = new Car();
        car.start();
        car.honk();  // Uses default implementation
        car.stop();

        System.out.println();

        Motorcycle bike = new Motorcycle();
        bike.start();
        bike.honk();  // Uses overridden version
        bike.stop();

        // ══════════════════════════════════════════════════════════════════════
        // COMPARISON: Abstract Class vs Interface
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("           ABSTRACT CLASS vs INTERFACE                         ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("┌────────────────────────┬──────────────────────────────────┐");
        System.out.println("│ ABSTRACT CLASS         │ INTERFACE                        │");
        System.out.println("├────────────────────────┼──────────────────────────────────┤");
        System.out.println("│ Can have constructor   │ Cannot have constructor          │");
        System.out.println("│ Can have instance vars │ Only constants (static final)    │");
        System.out.println("│ Single inheritance     │ Multiple implementation          │");
        System.out.println("│ extends keyword        │ implements keyword               │");
        System.out.println("│ Can have concrete      │ All abstract (default in Java 8+)│");
        System.out.println("│ methods                │                                  │");
        System.out.println("│ IS-A relationship      │ CAN-DO relationship              │");
        System.out.println("│ (Dog IS-A Animal)      │ (Dog CAN Bark)                   │");
        System.out.println("└────────────────────────┴──────────────────────────────────┘");
    }
}

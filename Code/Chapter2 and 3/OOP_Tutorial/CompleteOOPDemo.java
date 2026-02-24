/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                LESSON 9: COMPLETE OOP EXAMPLE                             ║
 * ║                     Nepal Banking System                                  ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  This example demonstrates ALL OOP concepts together:                     ║
 * ║  ✓ Encapsulation (private fields, getters/setters)                        ║
 * ║  ✓ Inheritance (Account → SavingsAccount, CurrentAccount)                 ║
 * ║  ✓ Polymorphism (Method overriding, dynamic dispatch)                     ║
 * ║  ✓ Abstraction (Abstract class, Interface)                                ║
 * ║  ✓ Static vs Instance members                                             ║
 * ║  ✓ Interfaces (Transaction)                                               ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// INTERFACE - Defines contract for transactions
// ════════════════════════════════════════════════════════════════════════════

interface Transactable {
    void deposit(double amount);
    boolean withdraw(double amount);
    void displayBalance();
}

interface InterestBearing {
    void applyInterest();
    double getInterestRate();
}

// ════════════════════════════════════════════════════════════════════════════
// ABSTRACT CLASS - Base Account
// ════════════════════════════════════════════════════════════════════════════

abstract class BankAccount implements Transactable {
    // STATIC - Shared by all accounts
    private static String bankName = "Nepal Rastra Bank";
    private static int totalAccounts = 0;
    private static double totalDeposits = 0;

    // PRIVATE (Encapsulation) - Instance variables
    private String accountNumber;
    private String holderName;
    protected double balance;  // Protected for child access
    private String pin;

    // Constructor
    BankAccount(String accountNumber, String holderName, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        setPin(pin);
        totalAccounts++;
        totalDeposits += initialBalance;
    }

    // ABSTRACT METHOD - Must be implemented by child classes
    abstract String getAccountType();
    abstract double getMinimumBalance();

    // ENCAPSULATION - Getters
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    // ENCAPSULATION - Setter with validation
    public void setPin(String pin) {
        if (pin != null && pin.matches("\\d{4}")) {
            this.pin = pin;
        } else {
            this.pin = "0000";  // Default PIN
            System.out.println("⚠️ Invalid PIN format. Set to default 0000");
        }
    }

    protected boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // INTERFACE IMPLEMENTATION
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            totalDeposits += amount;
            System.out.println("✅ Deposited: Rs. " + amount);
        } else {
            System.out.println("❌ Invalid deposit amount");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount");
            return false;
        }
        if (balance - amount < getMinimumBalance()) {
            System.out.println("❌ Insufficient balance. Minimum: Rs. " + getMinimumBalance());
            return false;
        }
        balance -= amount;
        System.out.println("✅ Withdrawn: Rs. " + amount);
        return true;
    }

    @Override
    public void displayBalance() {
        System.out.println("Current Balance: Rs. " + String.format("%.2f", balance));
    }

    // STATIC METHODS
    public static void displayBankInfo() {
        System.out.println("🏦 " + bankName);
        System.out.println("   Total Accounts: " + totalAccounts);
        System.out.println("   Total Deposits: Rs. " + totalDeposits);
    }

    // Common display method
    public void displayAccountInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│ Account Type: " + getAccountType());
        System.out.println("│ Account No: " + accountNumber);
        System.out.println("│ Holder: " + holderName);
        System.out.println("│ Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("│ Min Balance: Rs. " + getMinimumBalance());
        System.out.println("└─────────────────────────────────────────┘");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// CONCRETE CLASS - Savings Account
// ════════════════════════════════════════════════════════════════════════════

class SavingsAccountNP extends BankAccount implements InterestBearing {
    private double interestRate;
    private static final double MIN_BALANCE = 1000.0;

    SavingsAccountNP(String accountNumber, String holderName, double balance, 
                     String pin, double interestRate) {
        super(accountNumber, holderName, balance, pin);
        this.interestRate = interestRate;
    }

    // ABSTRACT METHOD IMPLEMENTATION
    @Override
    String getAccountType() {
        return "💰 Savings Account";
    }

    @Override
    double getMinimumBalance() {
        return MIN_BALANCE;
    }

    // INTERFACE IMPLEMENTATION
    @Override
    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("📈 Interest applied: Rs. " + String.format("%.2f", interest));
        System.out.println("   New Balance: Rs. " + String.format("%.2f", balance));
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("  Interest Rate: " + interestRate + "%");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// CONCRETE CLASS - Current Account
// ════════════════════════════════════════════════════════════════════════════

class CurrentAccountNP extends BankAccount {
    private double overdraftLimit;
    private static final double MIN_BALANCE = 5000.0;

    CurrentAccountNP(String accountNumber, String holderName, double balance, 
                     String pin, double overdraftLimit) {
        super(accountNumber, holderName, balance, pin);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    String getAccountType() {
        return "💼 Current Account";
    }

    @Override
    double getMinimumBalance() {
        return MIN_BALANCE;
    }

    // POLYMORPHISM - Override withdraw to allow overdraft
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount");
            return false;
        }
        // Current account can go negative up to overdraft limit
        if (balance - amount < -overdraftLimit) {
            System.out.println("❌ Exceeds overdraft limit of Rs. " + overdraftLimit);
            return false;
        }
        balance -= amount;
        System.out.println("✅ Withdrawn: Rs. " + amount);
        if (balance < 0) {
            System.out.println("⚠️ Account is in overdraft: Rs. " + Math.abs(balance));
        }
        return true;
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("  Overdraft Limit: Rs. " + overdraftLimit);
    }
}

// ════════════════════════════════════════════════════════════════════════════
// CONCRETE CLASS - Fixed Deposit Account
// ════════════════════════════════════════════════════════════════════════════

class FixedDepositAccount extends BankAccount implements InterestBearing {
    private int termMonths;
    private double interestRate;
    private boolean isMatured;

    FixedDepositAccount(String accountNumber, String holderName, double amount, 
                        String pin, int termMonths, double interestRate) {
        super(accountNumber, holderName, amount, pin);
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.isMatured = false;
    }

    @Override
    String getAccountType() {
        return "🔒 Fixed Deposit Account";
    }

    @Override
    double getMinimumBalance() {
        return balance;  // Cannot withdraw before maturity
    }

    // Override withdraw - not allowed before maturity
    @Override
    public boolean withdraw(double amount) {
        if (!isMatured) {
            System.out.println("❌ Cannot withdraw before maturity (" + termMonths + " months)");
            System.out.println("   Early withdrawal will incur 2% penalty");
            return false;
        }
        return super.withdraw(amount);
    }

    public void mature() {
        isMatured = true;
        applyInterest();
        System.out.println("🎉 FD has matured! You can now withdraw.");
    }

    @Override
    public void applyInterest() {
        double interest = balance * (interestRate / 100) * (termMonths / 12.0);
        balance += interest;
        System.out.println("📈 FD Interest applied: Rs. " + String.format("%.2f", interest));
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("  Term: " + termMonths + " months");
        System.out.println("  Interest Rate: " + interestRate + "%");
        System.out.println("  Status: " + (isMatured ? "Matured ✓" : "Active"));
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class CompleteOOPDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         LESSON 9: COMPLETE OOP BANKING SYSTEM                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // Creating Different Account Types
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── Creating Accounts ───\n");

        SavingsAccountNP savings = new SavingsAccountNP(
            "SAV-001", "Ram Sharma", 50000, "1234", 7.5
        );

        CurrentAccountNP current = new CurrentAccountNP(
            "CUR-001", "Sita Enterprises", 100000, "5678", 50000
        );

        FixedDepositAccount fd = new FixedDepositAccount(
            "FD-001", "Hari Bahadur", 200000, "9999", 12, 10.0
        );

        // Display all accounts
        savings.displayAccountInfo();
        System.out.println();
        current.displayAccountInfo();
        System.out.println();
        fd.displayAccountInfo();

        // ══════════════════════════════════════════════════════════════════════
        // POLYMORPHISM - Same method, different behavior
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── Polymorphism Demo ───\n");

        BankAccount[] accounts = {savings, current, fd};

        System.out.println("Attempting Rs. 80000 withdrawal from each account:\n");
        for (BankAccount acc : accounts) {
            System.out.println("Account: " + acc.getAccountNumber() + " (" + acc.getAccountType() + ")");
            acc.withdraw(80000);
            acc.displayBalance();
            System.out.println();
        }

        // ══════════════════════════════════════════════════════════════════════
        // INTERFACE - Interest Bearing Accounts
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── Interest Application ───\n");

        InterestBearing[] interestAccounts = {savings, fd};
        for (InterestBearing ib : interestAccounts) {
            System.out.println("Interest Rate: " + ib.getInterestRate() + "%");
            ib.applyInterest();
            System.out.println();
        }

        // ══════════════════════════════════════════════════════════════════════
        // STATIC - Bank Level Information
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── Bank Summary (Static) ───\n");
        BankAccount.displayBankInfo();

        // ══════════════════════════════════════════════════════════════════════
        // KEY CONCEPTS SUMMARY
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("              OOP CONCEPTS USED IN THIS EXAMPLE                ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("┌───────────────────┬───────────────────────────────────────┐");
        System.out.println("│ CONCEPT           │ WHERE USED                            │");
        System.out.println("├───────────────────┼───────────────────────────────────────┤");
        System.out.println("│ Encapsulation     │ Private fields, getters/setters       │");
        System.out.println("│ Inheritance       │ BankAccount → Savings, Current, FD    │");
        System.out.println("│ Polymorphism      │ withdraw() behaves differently        │");
        System.out.println("│ Abstraction       │ Abstract BankAccount class            │");
        System.out.println("│ Interface         │ Transactable, InterestBearing         │");
        System.out.println("│ Static            │ bankName, totalAccounts, displayBank  │");
        System.out.println("│ Protected         │ balance accessible in child classes   │");
        System.out.println("│ final (constant)  │ MIN_BALANCE in each account type      │");
        System.out.println("└───────────────────┴───────────────────────────────────────┘");
    }
}

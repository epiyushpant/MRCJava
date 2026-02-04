/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                       LESSON 2: ENCAPSULATION                             ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Definition: Wrapping data (variables) and code (methods) together        ║
 * ║              as a single unit, and restricting direct access to data.    ║
 * ║                                                                           ║
 * ║  Topics Covered:                                                          ║
 * ║  • Why Encapsulation? (Data Protection)                                   ║
 * ║  • Private Fields                                                         ║
 * ║  • Getters and Setters                                                    ║
 * ║  • Data Validation                                                        ║
 * ║  • Read-Only and Write-Only Properties                                    ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 1: WITHOUT Encapsulation (Bad Practice ❌)
// ════════════════════════════════════════════════════════════════════════════

/**
 * This is a BAD example - fields are public and can be modified directly.
 * Anyone can set invalid values!
 */
class BadBankAccount {
    public String accountNumber;  // ❌ Public - can be accessed directly
    public double balance;        // ❌ Public - can be set to negative!
    public String pin;            // ❌ Public - security risk!

    // No validation, no protection!
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 2: WITH Encapsulation (Good Practice ✅)
// ════════════════════════════════════════════════════════════════════════════

/**
 * This is a GOOD example - data is protected and validated.
 * Real-world Nepal Bank Account example.
 */
class BankAccount {
    // ──────────────────────────────────────────────────────────────────────────
    // PRIVATE FIELDS - Cannot be accessed directly from outside
    // ──────────────────────────────────────────────────────────────────────────
    private String accountNumber;  // ✅ Private - protected
    private String holderName;     // ✅ Private - protected
    private double balance;        // ✅ Private - protected
    private String pin;            // ✅ Private - highly sensitive

    // ──────────────────────────────────────────────────────────────────────────
    // CONSTRUCTOR
    // ──────────────────────────────────────────────────────────────────────────
    public BankAccount(String accountNumber, String holderName, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        setBalance(initialBalance);  // Using setter for validation
        setPin(pin);                 // Using setter for validation
    }

    // ──────────────────────────────────────────────────────────────────────────
    // GETTERS (Accessors) - Provide READ access
    // ──────────────────────────────────────────────────────────────────────────
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Note: No getter for PIN - it's highly sensitive!
    // We only verify PIN, never expose it

    // ──────────────────────────────────────────────────────────────────────────
    // SETTERS (Mutators) - Provide WRITE access with VALIDATION
    // ──────────────────────────────────────────────────────────────────────────
    
    public void setHolderName(String holderName) {
        if (holderName != null && !holderName.trim().isEmpty()) {
            this.holderName = holderName;
        } else {
            System.out.println("❌ Error: Name cannot be empty!");
        }
    }

    private void setBalance(double balance) {
        // Validation: Balance cannot be negative
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("❌ Error: Balance cannot be negative! Setting to 0.");
            this.balance = 0;
        }
    }

    public void setPin(String pin) {
        // Validation: PIN must be exactly 4 digits
        if (pin != null && pin.matches("\\d{4}")) {
            this.pin = pin;
            System.out.println("✅ PIN set successfully");
        } else {
            System.out.println("❌ Error: PIN must be exactly 4 digits!");
        }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // BUSINESS METHODS - Operate on private data
    // ──────────────────────────────────────────────────────────────────────────
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited Rs. " + amount);
            System.out.println("   New Balance: Rs. " + balance);
        } else {
            System.out.println("❌ Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount, String enteredPin) {
        // Step 1: Verify PIN
        if (!verifyPin(enteredPin)) {
            System.out.println("❌ Transaction failed: Invalid PIN!");
            return;
        }

        // Step 2: Validate amount
        if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be positive!");
            return;
        }

        // Step 3: Check sufficient balance
        if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
            System.out.println("   Available: Rs. " + balance);
            return;
        }

        // Step 4: Perform withdrawal
        balance -= amount;
        System.out.println("✅ Withdrawn Rs. " + amount);
        System.out.println("   Remaining Balance: Rs. " + balance);
    }

    private boolean verifyPin(String enteredPin) {
        return this.pin != null && this.pin.equals(enteredPin);
    }

    public void displayAccountInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│         BANK ACCOUNT DETAILS            │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Account No: " + accountNumber);
        System.out.println("│ Holder: " + holderName);
        System.out.println("│ Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("│ PIN: ****  (hidden for security)");
        System.out.println("└─────────────────────────────────────────┘");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 3: Read-Only and Write-Only Properties
// ════════════════════════════════════════════════════════════════════════════

class Person {
    private String name;
    private final String nationalId;  // READ-ONLY after creation
    private String password;           // WRITE-ONLY (no getter)

    public Person(String name, String nationalId) {
        this.name = name;
        this.nationalId = nationalId;
    }

    // Name: Both getter and setter (read-write)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // NationalId: Only getter (READ-ONLY)
    public String getNationalId() { return nationalId; }
    // No setter for nationalId - it cannot be changed after creation!

    // Password: Only setter (WRITE-ONLY)
    public void setPassword(String password) {
        if (password != null && password.length() >= 8) {
            this.password = password;
            System.out.println("✅ Password updated successfully");
        } else {
            System.out.println("❌ Password must be at least 8 characters!");
        }
    }
    // No getter for password - security risk!

    // Method to verify password without exposing it
    public boolean verifyPassword(String enteredPassword) {
        return this.password != null && this.password.equals(enteredPassword);
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS - Demonstration
// ════════════════════════════════════════════════════════════════════════════

public class EncapsulationDemo {
    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              LESSON 2: ENCAPSULATION DEMO                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Problem with NO Encapsulation
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Without Encapsulation (Bad) ───\n");

        BadBankAccount badAccount = new BadBankAccount();
        badAccount.accountNumber = "12345";
        badAccount.balance = -5000;  // ❌ Negative balance allowed!
        badAccount.pin = "1234";      // ❌ PIN exposed!
        
        System.out.println("Bad Account Balance: Rs. " + badAccount.balance);
        System.out.println("Pin exposed: " + badAccount.pin);
        System.out.println("^ This should NOT be possible!\n");

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Proper Encapsulation
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 2: With Encapsulation (Good) ───\n");

        BankAccount account = new BankAccount("NP-001-2024", "Ram Bahadur", 10000, "1234");
        account.displayAccountInfo();

        // ══════════════════════════════════════════════════════════════════════
        // PART 3: Data Validation in Action
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 3: Validation in Action ───\n");

        // Valid deposit
        account.deposit(5000);

        // Invalid deposit
        account.deposit(-100);

        // Withdraw with wrong PIN
        System.out.println("\nAttempting withdrawal with wrong PIN:");
        account.withdraw(2000, "0000");

        // Withdraw with correct PIN
        System.out.println("\nAttempting withdrawal with correct PIN:");
        account.withdraw(2000, "1234");

        // Try to overdraw
        System.out.println("\nAttempting to withdraw more than balance:");
        account.withdraw(50000, "1234");

        // ══════════════════════════════════════════════════════════════════════
        // PART 4: Cannot Access Private Fields Directly
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 4: Direct Access Blocked ───\n");

        // These lines would cause COMPILE ERRORS if uncommented:
        // account.balance = -5000;       // ❌ Error: balance has private access
        // account.pin = "9999";          // ❌ Error: pin has private access
        // System.out.println(account.pin); // ❌ Error: pin has private access

        System.out.println("✅ Private fields cannot be accessed directly!");
        System.out.println("✅ Can only interact through public methods (getters/setters)");

        // ══════════════════════════════════════════════════════════════════════
        // PART 5: Changing PIN with Validation
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 5: Setter Validation ───\n");

        System.out.println("Trying to set invalid PIN:");
        account.setPin("12");       // Too short
        account.setPin("abcd");     // Not digits
        
        System.out.println("\nTrying to set valid PIN:");
        account.setPin("9876");     // Valid

        // Final account state
        System.out.println("\n─── FINAL ACCOUNT STATE ───");
        account.displayAccountInfo();

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("                    KEY TAKEAWAYS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("• Encapsulation = Data hiding + controlled access");
        System.out.println("• Make fields PRIVATE to protect data");
        System.out.println("• Use GETTERS for read access");
        System.out.println("• Use SETTERS for write access with validation");
        System.out.println("• Sensitive data (like passwords) should have no getter");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                         PRACTICE EXERCISES                                ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║ 1. Create a class 'Employee' with:                                        ║
 * ║    - Private fields: id, name, salary, department                         ║
 * ║    - Validation: salary must be positive, name cannot be empty            ║
 * ║                                                                           ║
 * ║ 2. Create a class 'Product' with:                                         ║
 * ║    - Private fields: productId (read-only), name, price, quantity         ║
 * ║    - Validation: price > 0, quantity >= 0                                 ║
 * ║    - Method: calculateTotalValue() returns price * quantity               ║
 * ║                                                                           ║
 * ║ 3. Create a class 'User' with:                                            ║
 * ║    - Private fields: username (read-only), email, password (write-only)  ║
 * ║    - Email validation: must contain '@'                                   ║
 * ║    - Password validation: minimum 8 characters                            ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

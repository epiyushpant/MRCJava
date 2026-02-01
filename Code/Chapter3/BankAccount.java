// Custom Checked Exception
class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Main Class
public class BankAccount {

    // Method that declares a checked exception
    static void withdraw(double balance, double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Withdrawal amount exceeds available balance."
            );
        }

        System.out.println("Withdrawal successful.");
        System.out.println("Remaining Balance: " + (balance - amount));
    }

    public static void main(String[] args) {

        try {
            withdraw(5000, 7000);   // Invalid withdrawal
        } catch (InsufficientBalanceException e) {
            System.out.println("Exception Handled: " + e.getMessage());
        }

        System.out.println("Program continues normally...");
    }
}

//code to explain encapsulation concept

public class Account {
    // Private fields
    private String accountNumber;
    private double balance;

    // Constructor
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Public method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Public method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Public method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Public method to get the account number
    public String getAccountNumber() {
        return accountNumber;
    }

    public static void main(String[] args) {
        Account myAccount = new Account("123456789", 1000.0);

        myAccount.deposit(500.0);
        System.out.println("Balance after deposit: " + myAccount.getBalance());

        myAccount.withdraw(200.0);
        System.out.println("Balance after withdrawal: " + myAccount.getBalance());

        myAccount.withdraw(2000.0); 

        //myAccount.accountNumber = "987654321";
        
        // Invalid withdrawal
    }


}

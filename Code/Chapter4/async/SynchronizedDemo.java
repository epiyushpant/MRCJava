/**
 * SynchronizedDemo.java
 * Demonstrates thread synchronization to prevent race conditions
 */

// Unsafe counter - race condition problem
class UnsafeCounter {
    private int count = 0;

    public void increment() {
        count++; // NOT thread-safe!
    }

    public int getCount() {
        return count;
    }
}

// Safe counter - synchronized method
class SynchronizedCounter {
    private int count = 0;

    // Synchronized method - only one thread can access at a time
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// Safe counter - synchronized block
class BlockSynchronizedCounter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) { // Only this block is synchronized
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

// Bank account example
class BankAccount {
    private double balance;
    private String accountHolder;

    public BankAccount(String holder, double initialBalance) {
        this.accountHolder = holder;
        this.balance = initialBalance;
    }

    // Synchronized withdrawal
    public synchronized void withdraw(double amount) {
        String thread = Thread.currentThread().getName();

        if (balance >= amount) {
            System.out.println(thread + " withdrawing $" + amount);

            // Simulate processing delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            balance -= amount;
            System.out.println(thread + " completed. Balance: $" + balance);
        } else {
            System.out.println(thread + " failed: Insufficient balance!");
        }
    }

    // Synchronized deposit
    public synchronized void deposit(double amount) {
        String thread = Thread.currentThread().getName();
        System.out.println(thread + " depositing $" + amount);

        balance += amount;
        System.out.println(thread + " completed. Balance: $" + balance);
    }

    public synchronized double getBalance() {
        return balance;
    }
}

public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Race Condition Demo (Without Sync) ===\n");
        raceConditionDemo();

        System.out.println("\n=== With Synchronization ===\n");
        synchronizedDemo();

        System.out.println("\n=== Bank Account Demo ===\n");
        bankAccountDemo();
    }

    // Demonstrates race condition
    static void raceConditionDemo() throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();

        // Create 100 threads, each incrementing 1000 times
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // Wait for all threads
        for (Thread t : threads) {
            t.join();
        }

        // Expected: 100,000 | Actual: Often less due to race condition!
        System.out.println("Expected count: 100000");
        System.out.println("Actual count:   " + counter.getCount());
        System.out.println("⚠️ Race condition caused lost updates!");
    }

    // Demonstrates synchronized counter
    static void synchronizedDemo() throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Expected count: 100000");
        System.out.println("Actual count:   " + counter.getCount());
        System.out.println("✅ Synchronization prevented race condition!");
    }

    // Bank account with multiple threads
    static void bankAccountDemo() throws InterruptedException {
        BankAccount account = new BankAccount("John", 1000);

        System.out.println("Initial Balance: $" + account.getBalance() + "\n");

        // Thread trying to withdraw $600
        Thread t1 = new Thread(() -> {
            account.withdraw(600);
        }, "ATM-1");

        // Another thread also trying to withdraw $600
        Thread t2 = new Thread(() -> {
            account.withdraw(600);
        }, "ATM-2");

        // Thread depositing money
        Thread t3 = new Thread(() -> {
            account.deposit(200);
        }, "ATM-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nFinal Balance: $" + account.getBalance());
    }
}

/**
 * BasicThread.java
 * Demonstrates two ways to create threads in Java
 */

// Method 1: Extending Thread class
class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + ": Count " + i);
            try {
                Thread.sleep(500); // Sleep for 500ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}

// Method 2: Implementing Runnable interface (Preferred)
class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": Count " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}

public class BasicThread {
    public static void main(String[] args) {

        System.out.println("=== Method 1: Extending Thread ===");

        // Creating threads by extending Thread class
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread-A");
        t2.setName("Thread-B");

        t1.start(); // ⚠️ Always use start(), NOT run()
        t2.start();

        // Wait for threads to complete
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Method 2: Implementing Runnable ===");

        // Creating threads by implementing Runnable (Preferred)
        MyRunnable task = new MyRunnable();

        Thread t3 = new Thread(task, "Runnable-A");
        Thread t4 = new Thread(task, "Runnable-B");

        t3.start();
        t4.start();

        // Wait for completion
        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Using Lambda Expression ===");

        // Using lambda (Java 8+)
        Thread t5 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Lambda Thread: " + i);
            }
        });

        t5.start();

        try {
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll threads completed!");
    }
}

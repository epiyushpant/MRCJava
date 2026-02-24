/**
 * BeginnerThreads.java
 * Demonstrates the basics of multithreading using Thread and Runnable.
 */

// Method 1: Extending Thread
class WorkerThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("WorkerThread: " + i);
            try {
                // Sleep for 500ms to simulate work
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Method 2: Implementing Runnable (Preferred)
class RunnableTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("RunnableTask: " + i);
            try {
                // Sleep for 700ms (different speed)
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class BeginnerThreads {
    public static void main(String[] args) {
        System.out.println("--- Main Thread Started ---");

        // 1. Using Thread class
        WorkerThread t1 = new WorkerThread();
        t1.start(); // Start the first thread

        // 2. Using Runnable interface
        RunnableTask task = new RunnableTask();
        Thread t2 = new Thread(task); // Create a new Thread using the Runnable
        t2.start(); // Start the second thread

        // The main thread finishes here, but the other threads continue running.
        System.out.println("--- Main Thread Finished (other threads continue in background) ---");
    }
}

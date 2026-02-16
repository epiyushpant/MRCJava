/**
 * ThreadClassDemo.java
 * The most basic example of extending the Thread class.
 */

// 1. Extend the Thread class
class NumberThread extends Thread {

    // 2. Override the run() method
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " is counting: " + i);
            try {
                Thread.sleep(500); // Pause for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class ThreadClassDemo {
    public static void main(String[] args) {
        // 3. Create objects of your thread class
        NumberThread t1 = new NumberThread();
        NumberThread t2 = new NumberThread();

        // Optional: Name your threads
        t1.setName("Thread-A");
        t2.setName("Thread-B");

        // 4. Call start() to begin execution
        t1.start();
        t2.start();
    }
}

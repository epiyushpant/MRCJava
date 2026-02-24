/**
 * RunnableInterfaceDemo.java
 * The most basic example of implementing the Runnable interface.
 * Runnable is often preferred because it allows extending other classes.
 */

// 1. Implement the Runnable interface
class MessageTask implements Runnable {

    // 2. Implement the run() method
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " says: Hello!");
            try {
                Thread.sleep(500); // Pause for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class RunnableInterfaceDemo {
    public static void main(String[] args) {
        // 3. Create your runnable object
        MessageTask task = new MessageTask();

        // 4. Create Thread objects and pass the runnable to them
        Thread t1 = new Thread(task, "Runner-1");
        Thread t2 = new Thread(task, "Runner-2");

        // 5. Start the threads
        t1.start();
        t2.start();
    }
}

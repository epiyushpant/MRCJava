# 🧵 Basics of Multithreading in Java

This guide covers the fundamental concepts of multithreading in Java, specifically designed for beginners. It explains the **Thread class** and **Runnable interface**.

---

## 1. What is Multithreading?

**Multithreading** is the ability of a CPU to execute multiple processes or threads concurrently. 
*   **Process**: An executing program (e.g., MS Word, Music Player).
*   **Thread**: A lightweight sub-process, the smallest unit of processing.

**Real-world Analogy:**
Imagine a chef in a kitchen:
*   **Single-threaded:** The chef cuts vegetables, *then* boils water, *then* cooks pasta. (One task at a time).
*   **Multithreaded:** The chef puts water to boil (Thread 1), and while it's heating, cuts vegetables (Thread 2). (Tasks happen concurrently).

---

## 2. Creating Threads in Java

There are two main ways to create a thread in Java:

1.  **Extending the `Thread` class**
2.  **Implementing the `Runnable` interface** (Recommended)

### Method 1: Extending `Thread` Class

-   Create a class that extends `Thread`.
-   Override the `run()` method.
-   Create an object of the class and call `start()`.

```java
// 1. Extend Thread
class MyThread extends Thread {
    public void run() {
        // Code to be executed in this thread
        System.out.println("Thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Starts the thread
    }
}
```

### Method 2: Implementing `Runnable` Interface

-   Create a class that implements `Runnable`.
-   Implement the `run()` method.
-   Create a `Thread` object, passing your runnable object to the constructor.
-   Call `start()`.

```java
// 1. Implement Runnable
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable); // Pass runnable to Thread
        t1.start();
    }
}
```

**Why is `Runnable` better?**
*   Java doesn't support multiple inheritance. If you extend `Thread`, you can't extend any other class.
*   Implementing `Runnable` allows your class to extend another class if needed.

---

## 3. Important Methods

| Method | Description |
| :--- | :--- |
| `start()` | Starts the execution of the thread. It internally calls `run()`. |
| `run()` | The entry point for the thread. Contains the code to executing. |
| `sleep(milliseconds)` | Pauses the thread for the specified time. |
| `join()` | Waits for a thread to die (complete execution). |
| `getName()` | Returns the thread's name. |

**⚠️ Critical Logic:**
*   **Never call `run()` directly!**
    *   Calling `t1.run()` executes the code in the **current thread** (synchronously), just like a normal method call.
    *   Calling `t1.start()` creates a **new thread** and executes `run()` inside it.

---

## 4. Complete Code Example

Here is a complete, runnable example combining both methods. You can copy this into a file named `BeginnerThreads.java`.

```java
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
                Thread.sleep(500); // Sleep for 500ms
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Method 2: Implementing Runnable
class RunnableTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("RunnableTask: " + i);
            try {
                Thread.sleep(700); // Sleep for 700ms (different speed)
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
        t1.start();

        // 2. Using Runnable interface
        RunnableTask task = new RunnableTask();
        Thread t2 = new Thread(task);
        t2.start();

        System.out.println("--- Main Thread Finished (other threads continue) ---");
    }
}
```

### Expected Output
You will see the outputs mixed together, because they are running at the same time!

```text
--- Main Thread Started ---
--- Main Thread Finished (other threads continue) ---
WorkerThread: 1
RunnableTask: 1
WorkerThread: 2
RunnableTask: 2
WorkerThread: 3
...
```

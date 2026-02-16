# 🧵 Basics of Multithreading in Java

This guide covers the fundamental concepts of multithreading in Java, specifically designed for beginners. It explains the **Thread class** and **Runnable interface**.

---

## 1. What is Multithreading?

**Multithreading** in Java is a process of executing multiple threads simultaneously.

To understand this, we first need to understand **Multitasking**. Multitasking is performing multiple tasks at once, and it is achieved in two ways:

1.  **Process-based Multitasking (Multiprocessing)**
2.  **Thread-based Multitasking (Multithreading)**

### Key Definitions

| Term | Definition | Characteristics |
| :--- | :--- | :--- |
| **Process** | A program in execution (e.g., MS Word, Music Player). | **ciHeavyweight.** Has its own separate memory space. Switching between processes is expensive. |
| **Thread** | A sub-part of a process. The smallest unit of processing. | **Lightweight.** Shares the same memory area as the process. Switching between threads is fast. |

### Multiprocessing vs. Multithreading

*   **Multiprocessing:** Using two or more CPUs (processors) within a single computer system. Why? To increase computing power.
    *   *Analogy:* Two different people cooking in two different kitchens.
*   **Multithreading:** Executing multiple threads within a single process. Why? To maximize the utilization of the CPU.
    *   *Analogy:* One person cooking multiple dishes at the same time (chopping while water boils).

> **Note:** Java provides built-in support for **Multithreading**, not Multiprocessing (which is OS-level).

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

### 3. Which one should you use? (Important!)

**✅ Implementing `Runnable` is preferred over extending `Thread`.**

Here is why:

| Feature | `implements Runnable` (Best) | `extends Thread` (Avoid) |
| :--- | :--- | :--- |
| **Inheritance** | **Flexible:** You can extend another class (e.g., `class Player extends GamePerson implements Runnable`). | **Restricted:** Java only allows extending ONE class. You cannot extend anything else! |
| **Reusability** | **High:** The same task (runnable) can be passed to multiple threads. | **Low:** Each thread is a unique object. |
| **Resources** | **Lightweight:** Runnable is just a task. | **Heavy:** Thread objects require more memory. |

**Rule of Thumb:**
*   Use `Runnable` for defining the **task** (what to do).
*   Use `Thread` only to **run** the task.

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

## 5. Basic Templates: Separate Examples

These are the most fundamental, standalone examples for copy-pasting.

### Example A: Thread Class (Simplest)
This creates multiple threads by extending the `Thread` class.

Save as `ThreadClassDemo.java`:

```java
/**
 * ThreadClassDemo.java
 * The most basic example of extending the Thread class.
 */

class NumberThread extends Thread {
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
        NumberThread t1 = new NumberThread();
        NumberThread t2 = new NumberThread();
        
        t1.setName("Thread-A");
        t2.setName("Thread-B");
        
        t1.start();
        t2.start();
    }
}
```

### Example B: Runnable Interface (Reusable)
This defines a task that can be run by multiple threads.

Save as `RunnableInterfaceDemo.java`:

```java
/**
 * RunnableInterfaceDemo.java
 * The most basic example of implementing the Runnable interface.
 */

class MessageTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " says: Hello!");
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class RunnableInterfaceDemo {
    public static void main(String[] args) {
        MessageTask task = new MessageTask();
        
        Thread t1 = new Thread(task, "Runner-1");
        Thread t2 = new Thread(task, "Runner-2");
        
        t1.start();
        t2.start();
    }
}
```



### Example C: Why Runnable is Preferred? (Inheritance)

If you extend `Thread`, you **cannot** extend any other class because Java only supports single inheritance.
However, with `Runnable`, your class can extend another parent class **AND** behave like a thread.

Save as `RunnableInheritanceDemo.java`:

```java
/**
 * RunnableInheritanceDemo.java
 * Demonstrates: Extending a parent class AND implementing Runnable
 */

// 1. A normal parent class
class GameCharacter {
    public void jump() {
        System.out.println("🏃 Character jumps!");
    }
}

// 2. We extend GameCharacter, but can STILL run as a thread!
class Player extends GameCharacter implements Runnable {
    @Override
    public void run() {
        System.out.println("... Background task running ...");
    }
}

public class RunnableInheritanceDemo {
    public static void main(String[] args) {
        Player p1 = new Player();
        
        // Use inherited method (normal OOP)
        p1.jump(); 
        
        // Run as a thread
        Thread t = new Thread(p1);
        t.start();
    }
}
```

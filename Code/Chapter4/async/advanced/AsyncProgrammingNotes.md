# 📘 Asynchronous Programming in Java - Complete Notes

---

## 📌 Table of Contents

1. [What is Asynchronous Programming?](#1-what-is-asynchronous-programming)
2. [Synchronous vs Asynchronous](#2-synchronous-vs-asynchronous)
3. [Threads in Java](#3-threads-in-java)
4. [Creating Threads](#4-creating-threads)
5. [Thread Lifecycle](#5-thread-lifecycle)
6. [Thread Methods](#6-thread-methods)
7. [Runnable Interface](#7-runnable-interface)
8. [Callable Interface](#8-callable-interface)
9. [ExecutorService](#9-executorservice)
10. [CompletableFuture](#10-completablefuture)
11. [Thread Synchronization](#11-thread-synchronization)
12. [Best Practices](#12-best-practices)
13. [Code Examples Summary](#13-code-examples-summary)

---

## 1. What is Asynchronous Programming?

**Asynchronous programming** allows multiple tasks to run concurrently without blocking the main program execution. Instead of waiting for one task to complete before starting another, tasks can run in parallel.

### Key Concepts:
- **Thread** - A lightweight unit of execution
- **Concurrency** - Multiple tasks making progress
- **Parallelism** - Multiple tasks running simultaneously
- **Non-blocking** - Main thread continues while other tasks execute

---

## 2. Synchronous vs Asynchronous

### 🔴 Synchronous (Blocking)
```
Task 1 ─────────────►
                     Task 2 ─────────────►
                                          Task 3 ─────────────►
Time: ═══════════════════════════════════════════════════════►
```
Tasks execute **one after another**. Each task waits for the previous one to complete.

### 🟢 Asynchronous (Non-blocking)
```
Task 1 ─────────────►
Task 2 ─────────────►
Task 3 ─────────────►
Time: ═══════════════►
```
Tasks execute **concurrently**. Total time is significantly reduced.

### Example Comparison:
```java
// Synchronous - Takes 9 seconds total
downloadFile1();  // 3 seconds
downloadFile2();  // 3 seconds
downloadFile3();  // 3 seconds

// Asynchronous - Takes ~3 seconds total
startDownload(file1);  // Starts immediately
startDownload(file2);  // Starts immediately
startDownload(file3);  // Starts immediately
waitForAll();          // All complete around same time
```

---

## 3. Threads in Java

A **thread** is the smallest unit of execution within a process. Java supports multithreading natively.

### Main Thread
Every Java program has at least one thread - the **main thread**.

```java
public class MainThreadDemo {
    public static void main(String[] args) {
        // Get reference to current (main) thread
        Thread mainThread = Thread.currentThread();
        
        System.out.println("Thread Name: " + mainThread.getName());
        System.out.println("Thread ID: " + mainThread.getId());
        System.out.println("Thread Priority: " + mainThread.getPriority());
        System.out.println("Is Alive: " + mainThread.isAlive());
    }
}
```

**Output:**
```
Thread Name: main
Thread ID: 1
Thread Priority: 5
Is Alive: true
```

---

## 4. Creating Threads

There are **two ways** to create threads in Java:

### Method 1: Extending Thread Class

```java
class MyThread extends Thread {
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + ": Count " + i);
            try {
                Thread.sleep(500);  // Sleep for 500ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        
        t1.setName("Thread-A");
        t2.setName("Thread-B");
        
        t1.start();  // ⚠️ Use start(), NOT run()
        t2.start();
        
        System.out.println("Main thread continues...");
    }
}
```

**Sample Output:** (Order may vary!)
```
Main thread continues...
Thread-A: Count 1
Thread-B: Count 1
Thread-A: Count 2
Thread-B: Count 2
...
```

### Method 2: Implementing Runnable Interface (Preferred)

```java
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

public class ThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        
        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");
        
        t1.start();
        t2.start();
    }
}
```

### Why Runnable is Preferred?
| Extending Thread | Implementing Runnable |
|-----------------|----------------------|
| Cannot extend another class | Can extend another class |
| Tightly coupled | Loosely coupled |
| Less flexible | More flexible |
| One task per thread | Same task, multiple threads |

---

## 5. Thread Lifecycle

```
                    ┌──────────────┐
                    │     NEW      │
                    └──────┬───────┘
                           │ start()
                           ▼
                    ┌──────────────┐
          ┌────────►│   RUNNABLE   │◄────────┐
          │         └──────┬───────┘         │
          │                │ run()           │
          │                ▼                 │
          │         ┌──────────────┐         │
          │         │   RUNNING    │         │
          │         └──────┬───────┘         │
          │                │                 │
          │    ┌───────────┼───────────┐     │
          │    │           │           │     │
          │    ▼           ▼           ▼     │
     ┌─────────────┐ ┌──────────┐ ┌─────────────┐
     │   BLOCKED   │ │ WAITING  │ │TIMED_WAITING│
     └──────┬──────┘ └────┬─────┘ └──────┬──────┘
            │             │              │
            └─────────────┴──────────────┘
                          │ notify()/timeout
                          ▼
                   ┌──────────────┐
                   │  TERMINATED  │
                   └──────────────┘
```

| State | Description |
|-------|-------------|
| **NEW** | Thread created but not started |
| **RUNNABLE** | Ready to run, waiting for CPU |
| **RUNNING** | Currently executing |
| **BLOCKED** | Waiting for monitor lock |
| **WAITING** | Waiting indefinitely |
| **TIMED_WAITING** | Waiting for specified time |
| **TERMINATED** | Execution completed |

---

## 6. Thread Methods

| Method | Description |
|--------|-------------|
| `start()` | Starts the thread (calls run()) |
| `run()` | Contains the task code |
| `sleep(ms)` | Pauses thread for milliseconds |
| `join()` | Waits for thread to complete |
| `yield()` | Gives hint to scheduler |
| `interrupt()` | Interrupts a thread |
| `isAlive()` | Checks if thread is running |
| `getName()` | Returns thread name |
| `setName()` | Sets thread name |
| `getPriority()` | Returns thread priority (1-10) |
| `setPriority()` | Sets thread priority |

### Example: join() Method
```java
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread 1: " + i);
                try { Thread.sleep(500); } 
                catch (InterruptedException e) {}
            }
        });
        
        t1.start();
        t1.join();  // Main thread waits for t1 to complete
        
        System.out.println("Main thread: t1 has finished!");
    }
}
```

**Output:**
```
Thread 1: 1
Thread 1: 2
Thread 1: 3
Main thread: t1 has finished!
```

---

## 7. Runnable Interface

`Runnable` is a functional interface with a single method `run()`. It **cannot return a value** or throw checked exceptions.

### Using Lambda Expression (Java 8+)
```java
public class RunnableLambda {
    public static void main(String[] args) {
        
        // Using lambda expression
        Runnable task = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task: " + i);
            }
        };
        
        Thread thread = new Thread(task);
        thread.start();
        
        // Even shorter - inline
        new Thread(() -> System.out.println("Quick task!")).start();
    }
}
```

---

## 8. Callable Interface

`Callable` is similar to `Runnable` but **can return a value** and **throw exceptions**.

```java
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        
        // Callable returns a result
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;  // ✅ Can return value
        };
        
        // Use ExecutorService to run Callable
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(task);
        
        // Get the result (blocks until complete)
        Integer result = future.get();
        System.out.println("Sum: " + result);
        
        executor.shutdown();
    }
}
```

**Output:**
```
Sum: 5050
```

### Runnable vs Callable

| Feature | Runnable | Callable |
|---------|----------|----------|
| Return type | `void` | Generic `V` |
| Method name | `run()` | `call()` |
| Throws exception | No | Yes |
| Introduced in | Java 1.0 | Java 5 |

---

## 9. ExecutorService

`ExecutorService` provides a higher-level API for managing threads using thread pools.

### Types of Thread Pools

```java
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {
        
        // 1. Fixed Thread Pool - Fixed number of threads
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        
        // 2. Cached Thread Pool - Creates threads as needed
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        // 3. Single Thread Executor - Only one thread
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        
        // 4. Scheduled Thread Pool - For delayed/periodic tasks
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
    }
}
```

### Complete Example: Fixed Thread Pool
```java
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        
        // Create pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit 5 tasks (only 3 will run at a time)
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started by " + 
                                   Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                System.out.println("Task " + taskId + " completed");
            });
        }
        
        // Shutdown the executor
        executor.shutdown();
        
        try {
            // Wait for all tasks to complete
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("All tasks completed!");
    }
}
```

**Sample Output:**
```
Task 1 started by pool-1-thread-1
Task 2 started by pool-1-thread-2
Task 3 started by pool-1-thread-3
Task 1 completed
Task 4 started by pool-1-thread-1
Task 2 completed
Task 5 started by pool-1-thread-2
Task 3 completed
Task 4 completed
Task 5 completed
All tasks completed!
```

---

## 10. CompletableFuture

`CompletableFuture` (Java 8+) is the most powerful way to handle asynchronous programming in Java.

### Basic Usage
```java
import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        
        // Run async task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);  // Simulate long operation
            } catch (InterruptedException e) {}
            return "Hello from async!";
        });
        
        System.out.println("Main thread continues...");
        
        // Get result (blocks)
        String result = future.join();
        System.out.println("Result: " + result);
    }
}
```

### Chaining Operations
```java
import java.util.concurrent.*;

public class CompletableFutureChain {
    public static void main(String[] args) {
        
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Step 1: Fetching data...");
            return "Raw Data";
        })
        .thenApply(data -> {
            System.out.println("Step 2: Processing...");
            return data.toUpperCase();
        })
        .thenApply(data -> {
            System.out.println("Step 3: Transforming...");
            return "Processed: " + data;
        })
        .thenAccept(result -> {
            System.out.println("Step 4: Final Result: " + result);
        })
        .join();  // Wait for completion
    }
}
```

**Output:**
```
Step 1: Fetching data...
Step 2: Processing...
Step 3: Transforming...
Step 4: Final Result: Processed: RAW DATA
```

### Combining Multiple Futures
```java
import java.util.concurrent.*;

public class CombineFutures {
    public static void main(String[] args) {
        
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Result 1";
        });
        
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            return "Result 2";
        });
        
        // Combine both results
        CompletableFuture<String> combined = future1.thenCombine(future2, 
            (r1, r2) -> r1 + " + " + r2);
        
        System.out.println("Combined: " + combined.join());
        
        // Wait for all to complete
        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2);
        all.join();
        System.out.println("All futures completed!");
    }
    
    static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
```

### Exception Handling with CompletableFuture
```java
import java.util.concurrent.*;

public class FutureExceptionHandling {
    public static void main(String[] args) {
        
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return 42;
        })
        .exceptionally(ex -> {
            System.out.println("Error: " + ex.getMessage());
            return -1;  // Default value on error
        });
        
        System.out.println("Result: " + future.join());
    }
}
```

**Output:**
```
Error: java.lang.RuntimeException: Something went wrong!
Result: -1
```

### CompletableFuture Methods Summary

| Method | Description |
|--------|-------------|
| `supplyAsync()` | Runs async, returns result |
| `runAsync()` | Runs async, no result |
| `thenApply()` | Transform result |
| `thenAccept()` | Consume result |
| `thenRun()` | Run action after |
| `thenCombine()` | Combine two futures |
| `allOf()` | Wait for all futures |
| `anyOf()` | Wait for any future |
| `exceptionally()` | Handle exceptions |
| `handle()` | Handle result or exception |
| `join()` | Get result (blocks) |

---

## 11. Thread Synchronization

When multiple threads access shared resources, we need **synchronization** to prevent race conditions.

### The Problem: Race Condition
```java
class Counter {
    private int count = 0;
    
    public void increment() {
        count++;  // NOT thread-safe!
    }
    
    public int getCount() {
        return count;
    }
}
```

### Solution 1: synchronized Keyword
```java
class SynchronizedCounter {
    private int count = 0;
    
    // Synchronized method
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

### Solution 2: synchronized Block
```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();
    
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
```

### Complete Synchronization Example
```java
class BankAccount {
    private double balance = 1000;
    
    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + 
                             " withdrawing: " + amount);
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}

public class SyncDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        
        // Two threads trying to withdraw
        Thread t1 = new Thread(() -> account.withdraw(600), "Thread-1");
        Thread t2 = new Thread(() -> account.withdraw(600), "Thread-2");
        
        t1.start();
        t2.start();
    }
}
```

**Output:** (With synchronization)
```
Thread-1 withdrawing: 600
Remaining balance: 400.0
Insufficient balance!
```

---

## 12. Best Practices

### ✅ Do:
1. **Use thread pools** instead of creating threads manually
2. **Prefer Runnable** over extending Thread
3. **Use CompletableFuture** for complex async workflows
4. **Always shutdown ExecutorService** properly
5. **Handle exceptions** in async code
6. **Use synchronized** for shared mutable state

### ❌ Don't:
1. Don't call `run()` directly - use `start()`
2. Don't ignore `InterruptedException`
3. Don't create too many threads (use pools)
4. Don't use `Thread.stop()` (deprecated)
5. Don't forget to synchronize shared data

### Thread Pool Sizing Guidelines:
- **CPU-bound tasks:** `threads = number of CPU cores`
- **I/O-bound tasks:** `threads = CPU cores * (1 + wait_time/compute_time)`

---

## 13. Code Examples Summary

| File | Concept | Key Learning |
|------|---------|--------------|
| `BasicThread.java` | Thread creation | Extending Thread class |
| `RunnableDemo.java` | Runnable interface | Preferred thread creation |
| `CallableDemo.java` | Callable interface | Return values from threads |
| `ThreadPoolDemo.java` | ExecutorService | Managing thread pools |
| `CompletableFutureDemo.java` | Async operations | Modern async programming |
| `SynchronizedDemo.java` | Synchronization | Thread safety |

---

## 🎯 Quick Reference Card

```
┌─────────────────────────────────────────────────────────────┐
│                    THREAD CREATION                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   // Method 1: Extend Thread                                │
│   class MyThread extends Thread {                           │
│       public void run() { /* code */ }                      │
│   }                                                         │
│   new MyThread().start();                                   │
│                                                             │
│   // Method 2: Implement Runnable (Preferred)               │
│   Runnable task = () -> { /* code */ };                     │
│   new Thread(task).start();                                 │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                    EXECUTOR SERVICE                         │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   ExecutorService executor = Executors.newFixedThreadPool(4);│
│   executor.submit(() -> { /* task */ });                    │
│   executor.shutdown();                                      │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                 COMPLETABLE FUTURE                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   CompletableFuture.supplyAsync(() -> getData())            │
│       .thenApply(data -> process(data))                     │
│       .thenAccept(result -> display(result))                │
│       .exceptionally(ex -> handleError(ex));                │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                    SYNCHRONIZATION                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   public synchronized void method() { /* thread-safe */ }   │
│                                                             │
│   synchronized(lockObject) { /* critical section */ }       │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

> 📝 **Async Programming in Java - Chapter 4** 

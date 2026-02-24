
/**
 * ThreadPoolDemo.java
 * Demonstrates ExecutorService and thread pools
 */

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println("=== Fixed Thread Pool Demo ===\n");

        // Create a pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 5 tasks (only 3 will run at a time)
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " STARTED by " + threadName);

                // Simulate some work
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task " + taskId + " COMPLETED by " + threadName);
            });
        }

        // Shutdown the executor (no new tasks accepted)
        executor.shutdown();

        try {
            // Wait for all tasks to complete (max 10 seconds)
            boolean completed = executor.awaitTermination(10, TimeUnit.SECONDS);

            if (completed) {
                System.out.println("\n✅ All tasks completed successfully!");
            } else {
                System.out.println("\n⚠️ Some tasks did not complete in time.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Callable with Future Demo ===\n");

        // Create new executor for Callable demo
        ExecutorService executor2 = Executors.newFixedThreadPool(2);

        // Callable can return a value
        Callable<Integer> sumTask = () -> {
            System.out.println("Calculating sum of 1 to 100...");
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            Thread.sleep(500); // Simulate work
            return sum;
        };

        Callable<Integer> factorialTask = () -> {
            System.out.println("Calculating factorial of 10...");
            int factorial = 1;
            for (int i = 1; i <= 10; i++) {
                factorial *= i;
            }
            Thread.sleep(500); // Simulate work
            return factorial;
        };

        // Submit tasks and get Future objects
        Future<Integer> sumFuture = executor2.submit(sumTask);
        Future<Integer> factorialFuture = executor2.submit(factorialTask);

        try {
            // Get results (blocks until complete)
            System.out.println("Sum Result: " + sumFuture.get());
            System.out.println("Factorial Result: " + factorialFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor2.shutdown();

        System.out.println("\n✅ Program completed!");
    }
}

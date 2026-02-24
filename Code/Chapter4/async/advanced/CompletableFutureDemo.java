
/**
 * CompletableFutureDemo.java
 * Demonstrates modern async programming with CompletableFuture (Java 8+)
 */

import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        System.out.println("=== Basic CompletableFuture ===\n");
        basicDemo();

        System.out.println("\n=== Chaining Operations ===\n");
        chainingDemo();

        System.out.println("\n=== Combining Futures ===\n");
        combiningDemo();

        System.out.println("\n=== Exception Handling ===\n");
        exceptionDemo();

        System.out.println("\n✅ All demos completed!");
    }

    // Basic async operation
    static void basicDemo() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Running async task...");
            sleep(1000);
            return "Hello from async!";
        });

        System.out.println("  Main thread continues immediately...");

        // join() blocks until result is ready
        String result = future.join();
        System.out.println("  Result: " + result);
    }

    // Chaining multiple operations
    static void chainingDemo() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("  Step 1: Fetching data...");
            sleep(500);
            return "raw data";
        })
                .thenApply(data -> {
                    System.out.println("  Step 2: Processing data...");
                    return data.toUpperCase();
                })
                .thenApply(data -> {
                    System.out.println("  Step 3: Formatting data...");
                    return "Processed: [" + data + "]";
                })
                .thenAccept(result -> {
                    System.out.println("  Step 4: Final result -> " + result);
                })
                .join();
    }

    // Combining multiple futures
    static void combiningDemo() {

        // Two independent async operations
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Fetching user...");
            sleep(1000);
            return "John";
        });

        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Fetching order...");
            sleep(800);
            return "Order #123";
        });

        // Combine results when both complete
        CompletableFuture<String> combined = userFuture.thenCombine(orderFuture,
                (user, order) -> "User: " + user + ", " + order);

        System.out.println("  " + combined.join());

        // Alternative: Wait for all futures
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "Task A";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "Task B";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            sleep(400);
            return "Task C";
        });

        // Wait for all to complete
        CompletableFuture.allOf(f1, f2, f3).join();
        System.out.println("  All tasks completed: " +
                f1.join() + ", " + f2.join() + ", " + f3.join());
    }

    // Exception handling
    static void exceptionDemo() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Running risky operation...");

            // Simulate an error
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return 42;
        })
                .exceptionally(ex -> {
                    System.out.println("  ⚠️ Error caught: " + ex.getMessage());
                    return -1; // Return default value on error
                });

        System.out.println("  Result: " + future.join());

        // Using handle() for both success and failure
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> {
            return "Success!";
        })
                .handle((result, ex) -> {
                    if (ex != null) {
                        return "Error: " + ex.getMessage();
                    }
                    return "Handled: " + result;
                });

        System.out.println("  " + handled.join());
    }

    // Helper method
    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

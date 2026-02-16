/**
 * RunnableInheritanceDemo.java
 * Demonstrates WHY Runnable is preferred:
 * It allows the class to extend another class (ParentClass),
 * which is impossible if you extend Thread directly.
 */

// 1. A completely unrelated parent class
class GameCharacter {
    public void jump() {
        System.out.println("🏃 Character is jumping...");
    }
}

// 2. We can extend GameCharacter AND implement Runnable
//
// WHY IS THIS BETTER?
// Java only supports "Single Inheritance" (you can only extend one class).
// - If we said "class Player extends Thread", we could NOT extend
// GameCharacter.
// - By saying "implements Runnable", we are free to extend GameCharacter.
//
class Player extends GameCharacter implements Runnable {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // This thread runs in the background
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " is running background task: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    // We can also use methods from the parent class
    public void performAction() {
        System.out.print(name + " does action: ");
        jump(); // Inherited method
    }
}

public class RunnableInheritanceDemo {
    public static void main(String[] args) {

        System.out.println("=== Demonstrating Inheritance with Runnable ===");

        // Create the object
        Player p1 = new Player("Mario");

        // Usage 1: Call inherited methods (normal OOP)
        p1.performAction();

        // Usage 2: Run it as a thread
        Thread t1 = new Thread(p1);
        t1.start();

        System.out.println("=== Main thread finished launching ===\n");
    }
}

// // DiamondProblemDemo.java
// // Simple, beginner-friendly demonstration of the "Diamond Problem" and
// // two ways to avoid it in Java: interfaces (explicit resolution) and
// // composition (delegation).

// // --- Illegal (explained only) ---
// // class Parent { void act() { System.out.println("Parent acts"); } }
// // class Left extends Parent { void act() { System.out.println("Left acts"); } }
// // class Right extends Parent { void act() { System.out.println("Right acts"); } }
// // // Illegal in Java: class Child extends Left, Right { }

// // --- Solution 1: Use interfaces with default methods ---
// interface SpeakA {
//     default void speak() {
//         System.out.println("SpeakA: hello");
//     }
// }

// interface SpeakB {
//     default void speak() {
//         System.out.println("SpeakB: hi");
//     }
// }

// // If a class implements both SpeakA and SpeakB, Java requires an explicit
// // override to resolve which default implementation (if any) should be used.
// class DogResolve implements SpeakA, SpeakB {
//     @Override
//     public void speak() {
//         // Example choices shown for learners:
//         System.out.print("call SpeakA -> ");
//         SpeakA.super.speak();

//         System.out.print("call SpeakB -> ");
//         SpeakB.super.speak();

//         // Or implement a new behavior combining both:
//         System.out.println("DogResolve: combined sound (woof)");
//     }
// }

// // --- Solution 2: Composition / Delegation ---
// class GenericSpeak {
//     void speak() { System.out.println("Generic: ..."); }
// }

// class BarkSpeak {
//     void speak() { System.out.println("Bark: woof woof"); }
// }

// class DogByComposition {
//     private final GenericSpeak generic = new GenericSpeak();
//     private final BarkSpeak bark = new BarkSpeak();

//     void asGeneric() { generic.speak(); }
//     void asBark()    { bark.speak(); }
// }

// // --- Runner with clear output for beginners ---
// public class DiamondProblemDemo {
//     public static void main(String[] args) {
//         System.out.println("--- Interface based: explicit resolution ---");
//         DogResolve dr = new DogResolve();
//         dr.speak();

//         System.out.println("\n--- Composition based: delegate to chosen behaviour ---");
//         DogByComposition dc = new DogByComposition();
//         System.out.print("delegate generic -> "); dc.asGeneric();
//         System.out.print("delegate bark    -> "); dc.asBark();

//         System.out.println("\n--- Short summary for beginners ---");
//         System.out.println("Java prevents 'class C extends A, B' to avoid ambiguous behavior.");
//         System.out.println("Use interfaces with explicit overrides or composition/delegation.");
//     }
// }

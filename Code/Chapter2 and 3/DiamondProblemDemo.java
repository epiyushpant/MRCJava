// DiamondProblemDemo.java

// --- Solution : Use interfaces with default methods ---


// class SpeakA {
//     void speak() {
//         System.out.println("SpeakA says: Hello");
//     }
// }

// // Another class SpeakB extends SpeakA
// class SpeakB {
//         void speak() {
//         System.out.println("SpeakB says: Hi");
//     }
// }

// // Diamond problem: cannot extend two classes in Java
// class Dog extends SpeakA, SpeakB { 
//     //Which speak() should Dog inherit? SpeakA.speak() or SpeakB.speak()?
// }

interface SpeakA {
    void speak();
}

interface SpeakB {
    void speak();
}


class Dog implements SpeakA, SpeakB {
    // Java forces you to implement speak() because both interfaces declare it
    @Override
    public void speak() {
        // You can combine behavior or write your own
        System.out.println("Dog says: Woof!");
    }
}


// --- Runner with clear output for beginners ---
public class DiamondProblemDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.speak();  // Output: Dog says: Woof!
    }
}

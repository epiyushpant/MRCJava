// Example: Runtime Polymorphism (Method Overriding)

class Animal {
    void eat() {
        System.out.println("animal is eating...");
    }
}

class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("dog is eating...");
    }
}

class BabyDog extends Dog {
    @Override
    void eat() {
        System.out.println("baby dog is eating...");
    }
}

class Cat extends Animal {
    // Cat does NOT override eat() method
    // So it will inherit and use Animal's eat() method
}

public class DemoPoly {
    public static void main(String args[]) {
        System.out.println("=== When child OVERRIDES method ===");
        
        // Reference of parent type pointing to child object
        Animal a = new BabyDog();
        a.eat(); // Calls BabyDog's eat()
        
        System.out.println("\n=== More Polymorphism Examples ===");
        
        Animal a2 = new Dog();
        a2.eat(); // Calls Dog's eat()
        
        Animal a3 = new Animal();
        a3.eat(); // Calls Animal's eat()
        
        System.out.println("\n=== When child DOES NOT override method ===");
        
        Cat cat = new Cat();
        cat.eat(); // Cat doesn't override, so calls Animal's eat()
        

        //Rutime Polymorphism
        Animal a4 = new Cat();
        a4.eat(); // Reference is Animal, object is Cat - still calls Animal's eat()
    }
}

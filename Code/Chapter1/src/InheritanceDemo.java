// Demonstration of all types of inheritance in Java

// ============= SINGLE INHERITANCE =============
// Parent class
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class inheriting from Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// ============= MULTILEVEL INHERITANCE =============
// Grandchild class inheriting from Child class
class Puppy extends Dog {
    void play() {
        System.out.println("Puppy is playing");
    }
}

// ============= HIERARCHICAL INHERITANCE =============
// Another child class of Animal
class Cat extends Animal {
    void meow() {
        System.out.println("Cat is meowing");
    }
}

// Another child class of Animal
class Bird extends Animal {
    void chirp() {
        System.out.println("Bird is chirping");
    }
}

// ============= MULTIPLE INHERITANCE (Using Interfaces) =============
interface Swimmer {
    void swim();
}

interface Flyer {
    void fly();
}

// Duck class implementing multiple interfaces
class Duck extends Animal implements Swimmer, Flyer {
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }

    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
}

// ============= HYBRID INHERITANCE =============
// Combination of multiple types
interface Predator {
    void hunt();
}

class Eagle extends Animal implements Flyer, Predator {
    @Override
    public void fly() {
        System.out.println("Eagle is flying high");
    }

    @Override
    public void hunt() {
        System.out.println("Eagle is hunting");
    }
}

// ============= MAIN CLASS TO DEMONSTRATE ALL TYPES =============
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("===== SINGLE INHERITANCE =====");
        Dog dog = new Dog();
        dog.eat();      // From Animal class
        dog.bark();     // From Dog class

        System.out.println("\n===== MULTILEVEL INHERITANCE =====");
        Puppy puppy = new Puppy();
        puppy.eat();    // From Animal class (grandparent)
        puppy.bark();   // From Dog class (parent)
        puppy.play();   // From Puppy class

        System.out.println("\n===== HIERARCHICAL INHERITANCE =====");
        Cat cat = new Cat();
        cat.eat();      // From Animal class
        cat.meow();     // From Cat class

        Bird bird = new Bird();
        bird.eat();     // From Animal class
        bird.chirp();   // From Bird class

        System.out.println("\n===== MULTIPLE INHERITANCE (Via Interfaces) =====");
        Duck duck = new Duck();
        duck.eat();     // From Animal class
        duck.swim();    // From Swimmer interface
        duck.fly();     // From Flyer interface

        System.out.println("\n===== HYBRID INHERITANCE =====");
        Eagle eagle = new Eagle();
        eagle.eat();    // From Animal class
        eagle.fly();    // From Flyer interface
        eagle.hunt();   // From Predator interface
    }
}

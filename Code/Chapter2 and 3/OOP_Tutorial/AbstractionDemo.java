/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║                        LESSON 5: ABSTRACTION                              ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║  Definition: Hiding implementation details, showing only functionality    ║
 * ║  Achieved through: Abstract Classes and Interfaces                        ║
 * ║                                                                           ║
 * ║  Abstract Class Rules:                                                    ║
 * ║  • Cannot be instantiated (cannot create object directly)                 ║
 * ║  • Can have abstract methods (no body) and concrete methods (with body)   ║
 * ║  • Child class MUST implement all abstract methods                        ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 1: Abstract Class - Employee Salary System
// ════════════════════════════════════════════════════════════════════════════

abstract class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;

    // Constructor - abstract classes CAN have constructors
    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    // ABSTRACT METHOD - NO body, must be implemented by child
    abstract double calculateSalary();

    // CONCRETE METHOD - has body, can be used or overridden
    void displayDetails() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ ID: " + id);
        System.out.println("│ Name: " + name);
        System.out.println("│ Base Salary: Rs. " + baseSalary);
        System.out.println("└─────────────────────────────────┘");
    }

    // Another concrete method
    void work() {
        System.out.println(name + " is working...");
    }
}

// Concrete class - MUST implement calculateSalary()
class FullTimeEmployee extends Employee {
    private double bonus;
    private double allowance;

    FullTimeEmployee(String name, int id, double baseSalary, double bonus, double allowance) {
        super(name, id, baseSalary);
        this.bonus = bonus;
        this.allowance = allowance;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus + allowance;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("  Type: Full-Time");
        System.out.println("  Bonus: Rs. " + bonus);
        System.out.println("  Allowance: Rs. " + allowance);
        System.out.println("  Total: Rs. " + calculateSalary());
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    PartTimeEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id, 0);  // Base salary is 0 for part-time
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    void displayDetails() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ ID: " + id);
        System.out.println("│ Name: " + name);
        System.out.println("│ Type: Part-Time");
        System.out.println("│ Hourly Rate: Rs. " + hourlyRate);
        System.out.println("│ Hours Worked: " + hoursWorked);
        System.out.println("│ Total: Rs. " + calculateSalary());
        System.out.println("└─────────────────────────────────┘");
    }
}

// ════════════════════════════════════════════════════════════════════════════
// EXAMPLE 2: Abstract Class - Shape Hierarchy
// ════════════════════════════════════════════════════════════════════════════

abstract class Shape {
    protected String color;

    Shape(String color) {
        this.color = color;
    }

    // Abstract methods - child MUST implement
    abstract double calculateArea();
    abstract double calculatePerimeter();

    // Concrete method
    void displayColor() {
        System.out.println("Color: " + color);
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

class Circle extends Shape {
    private double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;
    private double side1, side2, side3;

    Triangle(String color, double base, double height, double s1, double s2, double s3) {
        super(color);
        this.base = base;
        this.height = height;
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    double calculatePerimeter() {
        return side1 + side2 + side3;
    }
}

// ════════════════════════════════════════════════════════════════════════════
// MAIN CLASS
// ════════════════════════════════════════════════════════════════════════════

public class AbstractionDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              LESSON 5: ABSTRACTION DEMO                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // Cannot create object of abstract class
        // ══════════════════════════════════════════════════════════════════════
        // Employee emp = new Employee("Test", 1, 1000);  // ❌ COMPILE ERROR!

        // ══════════════════════════════════════════════════════════════════════
        // PART 1: Employee Salary System
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("─── PART 1: Employee Salary System ───\n");

        Employee e1 = new FullTimeEmployee("Ram Sharma", 101, 50000, 10000, 5000);
        Employee e2 = new PartTimeEmployee("Sita Thapa", 102, 500, 120);

        e1.displayDetails();
        e1.work();
        
        System.out.println();
        
        e2.displayDetails();
        e2.work();

        // ══════════════════════════════════════════════════════════════════════
        // PART 2: Shape Hierarchy
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n─── PART 2: Shape Calculations ───\n");

        Shape[] shapes = {
            new Rectangle("Red", 5, 4),
            new Circle("Blue", 3),
            new Triangle("Green", 6, 4, 5, 5, 6)
        };

        String[] names = {"Rectangle", "Circle", "Triangle"};
        
        for (int i = 0; i < shapes.length; i++) {
            System.out.println("--- " + names[i] + " ---");
            shapes[i].displayColor();
            System.out.printf("Area: %.2f\n", shapes[i].calculateArea());
            System.out.printf("Perimeter: %.2f\n\n", shapes[i].calculatePerimeter());
        }

        // ══════════════════════════════════════════════════════════════════════
        // KEY TAKEAWAYS
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("                    KEY TAKEAWAYS                              ");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("• Abstract class: use 'abstract' keyword");
        System.out.println("• Cannot create object of abstract class");
        System.out.println("• Abstract method: no body, ends with semicolon");
        System.out.println("• Child class MUST implement all abstract methods");
        System.out.println("• Abstract class can have constructors");
        System.out.println("• Abstract class can have concrete (regular) methods too");
        System.out.println("• Use when classes share common behavior but differ in details");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}

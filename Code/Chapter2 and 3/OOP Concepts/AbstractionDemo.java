
// Abstract class
abstract class Employee {

    protected String name;
    protected int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method
    abstract double calculateSalary();

    // Concrete method
    void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }
}

// Full-time employee
abstract class FullTimeEmployee extends Employee {

    private double monthlySalary;

    FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
     double calculateSalary() {
        return monthlySalary;
    }
} 

// Part-time employee
class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private int hoursWorked;

    PartTimeEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {

        Employee e1 = new FullTimeEmployee("Ram", 1, 50000);
        Employee e2 = new PartTimeEmployee("Sita", 2, 500, 40);

        e1.displayDetails();
        System.out.println("Salary: " + e1.calculateSalary());

        System.out.println();

        e2.displayDetails();
        System.out.println("Salary: " + e2.calculateSalary());
    }
}

public class Employee {

    String name;
    String location;
    double salary;
    int age;

    Employee(String name, String location, double salary,
             int age) {
        this.name = name;
        this.location = location;
        this.salary = salary;
        this.age = age;
    }

    void raiseSalary() {
        this.salary = this.salary * 1.2;
    }
}

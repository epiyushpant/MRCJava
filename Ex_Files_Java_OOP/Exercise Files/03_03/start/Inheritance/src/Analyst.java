public class Analyst {

    private String name;
    private double salary;
    private int age;

    public Analyst(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }

    public double getAnnualBonus() {
        return this.salary * .05;
    }

    public void raiseSalary() {
        this.salary = this.salary * 1.2;
    }
}

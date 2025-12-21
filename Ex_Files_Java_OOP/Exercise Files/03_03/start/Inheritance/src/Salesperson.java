public class Salesperson {

    private String name;
    private double salary;
    private int age;
    private double commissionPercentage;

    public Salesperson(String name, double salary, int age,
                       double commissionPercentage) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.commissionPercentage = commissionPercentage;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getAge() {
        return this.age;
    }

    public double getCommissionPercentage() {
        return this.commissionPercentage;
    }

    public void raiseSalary() {
        this.salary = this.salary * 1.2;
    }

    public void raiseCommission() {
        if (this.commissionPercentage < .30) {
            this.commissionPercentage =
                    this.commissionPercentage * 1.2;
        }
    }
}

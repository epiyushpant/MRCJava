public class Main {

    public static void main(String[] args) {
        Employee employeeOne = new Employee("Sally Roberts",
                "Los Angeles", 70000, 34);

        Employee employeeTwo = new Employee("Matt Johnson",
                "New York", 65000, 32);

        employeeTwo.raiseSalary();

        System.out.println(employeeOne.salary);
        System.out.println(employeeTwo.salary);
    }
}

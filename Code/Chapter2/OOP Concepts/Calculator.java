public class Calculator {
    
    //Method with two int parameters
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with three int parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method with double parameters
    double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {

        //Method overloading 
        //Same method name, different parameter list (number, type, or order) in the same class.
        Calculator calc = new Calculator();

        // Calling method with two int parameters
        System.out.println("Sum of 2 and 3: " + calc.add(2, 3));
        

        // Calling overloaded method with three int parameters
       System.out.println("Sum of 2, 3 and 4: " + calc.add(2, 3, 4));

        // Calling overloaded method with double parameters
        System.out.println("Sum of 2.5 and 3.5: " + calc.add(2.5, 3.5));
    }
}




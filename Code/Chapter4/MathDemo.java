class MathDemo {
    static int divide(int a, int b) throws ArithmeticException {
        return a / b; // may throw ArithmeticException
    }

    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero!");
        }
        finally {
            System.out.println("Finally block executed.");
        }
    }
}

//if we dont handle exception then it will be propagated to the caller method and
//if it is not handled there then it will be propagated to the main method and if 
// it is not handled there then it will be propagated to the JVM and the program will terminate with an error message.

//here throws keyword is used to declare that a method may throw an exception and it is the responsibility of 
// the caller method to handle it.
//here we are handling the exception in the main method using try-catch block and we are printing a custom 
// error message when the exception occurs.

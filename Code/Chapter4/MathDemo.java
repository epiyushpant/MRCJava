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

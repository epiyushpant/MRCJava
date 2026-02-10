
/**
 * UncheckedExceptionsDemo.java
 * Demonstrates common Unchecked (Runtime) Exceptions in Java
 * - ArithmeticException
 * - NullPointerException
 * - ArrayIndexOutOfBoundsException
 * - StringIndexOutOfBoundsException
 * - NumberFormatException
 * - IllegalArgumentException
 */

import java.util.Objects;

public class UncheckedExceptionsDemo {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   UNCHECKED (RUNTIME) EXCEPTIONS DEMONSTRATION   ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        // 1. ArithmeticException
        System.out.println("═══ 1. ArithmeticException ═══");
        arithmeticExceptionDemo();

        // 2. NullPointerException
        System.out.println("\n═══ 2. NullPointerException ═══");
        nullPointerExceptionDemo();

        // 3. ArrayIndexOutOfBoundsException
        System.out.println("\n═══ 3. ArrayIndexOutOfBoundsException ═══");
        arrayIndexExceptionDemo();

        // 4. StringIndexOutOfBoundsException
        System.out.println("\n═══ 4. StringIndexOutOfBoundsException ═══");
        stringIndexExceptionDemo();

        // 5. NumberFormatException
        System.out.println("\n═══ 5. NumberFormatException ═══");
        numberFormatExceptionDemo();

        // 6. IllegalArgumentException
        System.out.println("\n═══ 6. IllegalArgumentException ═══");
        illegalArgumentExceptionDemo();

        System.out.println("\n✅ All demos completed successfully!");
    }

    // ═══════════════════════════════════════════════════════════════
    // 1. ArithmeticException - Division by Zero
    // ═══════════════════════════════════════════════════════════════
    static void arithmeticExceptionDemo() {

        // ❌ Integer division by zero
        try {
            int a = 100;
            int b = 0;
            System.out.println("Attempting: 100 / 0");
            int result = a / b; // Throws ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("❌ ArithmeticException: " + e.getMessage());
        }

        // ⚠️ Floating-point division (No exception - returns Infinity)
        double x = 100.0;
        double y = 0.0;
        System.out.println("\nNote: 100.0 / 0.0 = " + (x / y) + " (No exception!)");

        // ✅ Prevention
        System.out.println("\n✅ Prevention: Check divisor before division");
        int divisor = 0;
        if (divisor != 0) {
            System.out.println("   Result: " + (100 / divisor));
        } else {
            System.out.println("   Cannot divide by zero!");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 2. NullPointerException - Most Common Exception!
    // ═══════════════════════════════════════════════════════════════
    static void nullPointerExceptionDemo() {

        // ❌ Calling method on null object
        String str = null;
        try {
            System.out.println("Attempting: null.length()");
            int length = str.length(); // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("❌ NullPointerException caught!");
        }

        // ❌ Accessing null array
        String[] names = null;
        try {
            System.out.println("\nAttempting: null[0]");
            System.out.println(names[0]);
        } catch (NullPointerException e) {
            System.out.println("❌ NullPointerException: Array is null");
        }

        // ✅ Prevention techniques
        System.out.println("\n✅ Prevention techniques:");

        // Method 1: Simple null check
        String text = null;
        if (text != null) {
            System.out.println("Length: " + text.length());
        } else {
            System.out.println("   1. Null check: Text is null, skip operation");
        }

        // Method 2: Using ternary operator
        String result = (text != null) ? text : "default";
        System.out.println("   2. Default value: " + result);

        // Method 3: Objects.requireNonNull (for validation)
        try {
            // Objects.requireNonNull(text, "Text cannot be null");
            System.out.println("   3. Objects.requireNonNull() for validation");
        } catch (NullPointerException e) {
            // Would throw with custom message
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 3. ArrayIndexOutOfBoundsException
    // ═══════════════════════════════════════════════════════════════
    static void arrayIndexExceptionDemo() {

        int[] numbers = { 10, 20, 30, 40, 50 }; // Indices: 0, 1, 2, 3, 4
        System.out.println("Array: [10, 20, 30, 40, 50] (length = 5)");

        // ❌ Index too high
        try {
            System.out.println("\nAttempting: numbers[5]");
            System.out.println(numbers[5]); // Invalid! Max index is 4
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        // ❌ Negative index
        try {
            System.out.println("\nAttempting: numbers[-1]");
            System.out.println(numbers[-1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        // ✅ Prevention: Bounds checking
        System.out.println("\n✅ Prevention: Bounds checking");
        int index = 3;
        if (index >= 0 && index < numbers.length) {
            System.out.println("   numbers[" + index + "] = " + numbers[index]);
        } else {
            System.out.println("   Index out of bounds!");
        }

        // ✅ Safest: Use for-each loop
        System.out.println("\n✅ Safest: Use for-each loop");
        System.out.print("   ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════
    // 4. StringIndexOutOfBoundsException
    // ═══════════════════════════════════════════════════════════════
    static void stringIndexExceptionDemo() {

        String str = "Hello"; // Length: 5, Valid indices: 0-4
        System.out.println("String: \"Hello\" (length = 5)");

        // ❌ Invalid charAt()
        try {
            System.out.println("\nAttempting: str.charAt(10)");
            char ch = str.charAt(10);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ StringIndexOutOfBoundsException: " + e.getMessage());
        }

        // ❌ Invalid substring()
        try {
            System.out.println("\nAttempting: str.substring(2, 10)");
            String sub = str.substring(2, 10); // End index too high
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ StringIndexOutOfBoundsException: " + e.getMessage());
        }

        // ❌ Negative index
        try {
            System.out.println("\nAttempting: str.charAt(-1)");
            char ch = str.charAt(-1);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ StringIndexOutOfBoundsException: " + e.getMessage());
        }

        // ✅ Prevention
        System.out.println("\n✅ Prevention: Validate indices");
        int charIndex = 2;
        if (charIndex >= 0 && charIndex < str.length()) {
            System.out.println("   str.charAt(" + charIndex + ") = '" + str.charAt(charIndex) + "'");
        }

        // ✅ Safe substring
        int start = 0, end = 3;
        if (start >= 0 && end <= str.length() && start <= end) {
            System.out.println("   str.substring(" + start + ", " + end + ") = \"" +
                    str.substring(start, end) + "\"");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 5. NumberFormatException
    // ═══════════════════════════════════════════════════════════════
    static void numberFormatExceptionDemo() {

        // ❌ Invalid number strings
        String[] invalidInputs = { "abc", "123abc", "", "12.34", "  ", "null" };

        System.out.println("Testing invalid inputs:");
        for (String input : invalidInputs) {
            try {
                int num = Integer.parseInt(input);
                System.out.println("   \"" + input + "\" → " + num);
            } catch (NumberFormatException e) {
                System.out.println("   ❌ \"" + input + "\" → NumberFormatException");
            }
        }

        // ✅ Valid number strings
        String[] validInputs = { "123", "-456", "0", "+789" };
        System.out.println("\nTesting valid inputs:");
        for (String input : validInputs) {
            try {
                int num = Integer.parseInt(input);
                System.out.println("   ✅ \"" + input + "\" → " + num);
            } catch (NumberFormatException e) {
                System.out.println("   ❌ \"" + input + "\" → NumberFormatException");
            }
        }

        // ✅ Prevention: Validate before parsing
        System.out.println("\n✅ Prevention: Validate using regex");
        String userInput = "12345";
        if (userInput != null && userInput.matches("-?\\d+")) {
            int num = Integer.parseInt(userInput);
            System.out.println("   Valid integer: " + num);
        } else {
            System.out.println("   Invalid input!");
        }

        // ✅ For doubles
        String doubleInput = "123.456";
        if (doubleInput.matches("-?\\d+(\\.\\d+)?")) {
            double num = Double.parseDouble(doubleInput);
            System.out.println("   Valid double: " + num);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // 6. IllegalArgumentException - For method validation
    // ═══════════════════════════════════════════════════════════════
    static void illegalArgumentExceptionDemo() {

        // ❌ Invalid age
        try {
            System.out.println("Setting age to -5...");
            setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ IllegalArgumentException: " + e.getMessage());
        }

        // ❌ Invalid percentage
        try {
            System.out.println("\nSetting percentage to 150...");
            setPercentage(150.0);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ IllegalArgumentException: " + e.getMessage());
        }

        // ❌ Empty name
        try {
            System.out.println("\nSetting name to empty string...");
            setName("");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ IllegalArgumentException: " + e.getMessage());
        }

        // ✅ Valid inputs
        System.out.println("\n✅ Valid inputs:");
        try {
            setAge(25);
            setPercentage(85.5);
            setName("John Doe");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper methods for IllegalArgumentException demo

    static void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException(
                    "Age must be between 0 and 150. Got: " + age);
        }
        System.out.println("   Age set to: " + age);
    }

    static void setPercentage(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException(
                    "Percentage must be between 0 and 100. Got: " + percent);
        }
        System.out.println("   Percentage set to: " + percent + "%");
    }

    static void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Name cannot be null or empty");
        }
        System.out.println("   Name set to: " + name);
    }
}

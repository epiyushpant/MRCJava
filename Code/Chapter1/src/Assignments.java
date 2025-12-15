import java.util.Scanner;

// java
public class Assignments {

    // 1. Swap using a third variable (prints result)
    public static void swapUsingTemp(int first, int second) {
        int temp = first;
        first = second;
        second = temp;
        System.out.println("Swap using temp: first=" + first + ", second=" + second);
    }

    // 2. Swap without using a third variable (using + and -) (prints result)
    public static void swapWithoutTemp(int first, int second) {
        if (first != second) {
            first = first + second;
            second = first - second;
            first = first - second;
        }
        System.out.println("Swap without temp: first=" + first + ", second=" + second);
    }

    // 3. Count number of even and odd elements in an array (prints result)
    public static void countEvenOdd(int[] numbers) {
        int evenCount = 0, oddCount = 0;
        for (int value : numbers) {
            if (value % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        System.out.println("Even count=" + evenCount + ", Odd count=" + oddCount);
    }

    // 4. Check if a number is prime (prints result)
    public static void isPrime(int number) {
        boolean isPrime;
        if (number <= 1) isPrime = false;
        else if (number <= 3) isPrime = true;
        else {
            isPrime = true;
            for (int i = 2; i * i <= number; i += 1) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(number + " is prime? " + isPrime);
    }

    // 5. Reverse a number (prints result)
    public static void reverseNumber(int number) {
        int reversed = 0;
        while (number != 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        System.out.println("Reversed = " + reversed);
    }

    // 6. Reverse a string (prints result)
    public static void reverseString(String text) {
//        String reversed = "";
//        for (int i = text.length() - 1; i >= 0; i--) {
//            reversed += text.charAt(i);
//        }
//        System.out.println("Reverse of \"" + text + "\" = \"" + reversed + "\"");

        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed.append(text.charAt(i));
        }
        System.out.println("Reverse of \"" + text + "\" = \"" + reversed + "\"");



//        StringBuilder sb = new StringBuilder(text);
//        String reversed = sb.reverse().toString();
//        System.out.println("Reverse of \"" + text + "\" = \"" + reversed + "\"");
    }

    // 7. Convert Celsius to Fahrenheit (prints result)
    public static void celsiusToFahrenheit(double celsius) {
        double fahrenheit = celsius * 9.0 / 5.0 + 32.0;
        System.out.println(celsius + " C = " + fahrenheit + " F");
    }

    // 8b. Average of an array (prints result)
    public static void sumAndAverageOfArray(int[] numbers) {
        if (numbers.length == 0) {
            System.out.println("Average = 0.0");
            return;
        }
        long sum = 0;
        for (int value : numbers) {
            sum += value;
        }

        System.out.println("Sum = " + sum);

        double average = (double) sum / numbers.length;
        System.out.println("Average = " + average);
    }

    // 9. Check if a value is present in an array (prints result)
    public static void containsValue(int[] numbers, int target) {
        boolean found = false;
        for (int value : numbers) {
            if (value == target) { found = true; break; }
        }
        System.out.println("Array contains " + target + "? " + found);
    }

    // 10. Check palindrome number (prints result)
    public static void isPalindromeNumber(int number) {
        if (number < 0) {
            System.out.println(number + " is palindrome? false");
            return;
        }
        int temp = number;
        int reversed = 0;
        while (temp > 0) {
            reversed = reversed * 10 + temp % 10;
            temp /= 10;
        }
        System.out.println(number + " is palindrome? " + (number == reversed));
    }

    // Demonstration
    public static void runAssignments() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int first = sc.nextInt();

        System.out.print("Enter second number: ");
        int second = sc.nextInt();

        swapUsingTemp(first, second);
        swapWithoutTemp(first, second);

        int[] sampleArray = {1, 2, 3, 4, 5, 6};
        countEvenOdd(sampleArray);

        int testNumber = 17;
        isPrime(testNumber);

        int numberToReverse = 12345;
        reverseNumber(numberToReverse);

        String text = "hello";
        reverseString(text);

        double celsius = 37.0;
        celsiusToFahrenheit(celsius);

        sumAndAverageOfArray(sampleArray);
        int searchValue = 3;
        containsValue(sampleArray, searchValue);

        int palCandidate1 = 121, palCandidate2 = 123;
        isPalindromeNumber(palCandidate1);
        isPalindromeNumber(palCandidate2);
    }
}

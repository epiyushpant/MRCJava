public class Problem4_PrimeCheck {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;
        int limit = (int)Math.sqrt(n);
        for (int i = 3; i <= limit; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] tests = { -1, 0, 1, 2, 3, 4, 17, 18, 19, 20 };
        for (int n : tests) {
            System.out.println(n + " is prime? " + isPrime(n));
        }
    }
}

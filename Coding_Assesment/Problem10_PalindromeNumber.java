public class Problem10_PalindromeNumber {
    public static long reverseNumber(long n) {
        long v = Math.abs(n);
        long rev = 0;
        while (v != 0) {
            rev = rev * 10 + (v % 10);
            v /= 10;
        }
        return rev;
    }

    public static boolean isPalindrome(long n) {
        if (n < 0) return false;
        return n == reverseNumber(n);
    }

    public static void main(String[] args) {
        long a = 12321L;
        long b = 12345L;
        System.out.println(a + " is palindrome? " + isPalindrome(a));
        System.out.println(b + " is palindrome? " + isPalindrome(b));
    }
}

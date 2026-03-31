public class Problem5_ReverseNumber {
    public static long reverseNumber(long n) {
        boolean negative = n < 0;
        long v = Math.abs(n);
        long rev = 0;
        while (v != 0) {
            rev = rev * 10 + (v % 10);
            v /= 10;
        }
        return negative ? -rev : rev;
    }

    public static void main(String[] args) {
        long a = 12345L;
        long b = -67890L;
        System.out.println(a + " reversed -> " + reverseNumber(a));
        System.out.println(b + " reversed -> " + reverseNumber(b));
    }
}

public class PalindromeCheck {
    public static void main(String[] args) {
        int n = 121, temp = n, rev = 0;

        while (temp != 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }

        if (n == rev)
            System.out.println(n + " is Palindrome");
        else
            System.out.println(n + " is not Palindrome");
    }
}
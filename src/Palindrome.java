import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        int OriginalNumber = num;
        int reverse = 0;
        while (num != 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }
        if (OriginalNumber == reverse) {
            System.out.println(OriginalNumber + " Is a Plainfrome");
        } else {
            System.out.println(OriginalNumber + " Is not a palindrome");
        }

        sc.close();
    }
}



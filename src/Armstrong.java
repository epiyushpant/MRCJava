import java.util.Scanner;
public class Armstrong {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = sc.nextInt();
        int OriginalNumber = num;
        int digits = 0;
        int sum = 0;
        int temp = num;

        while ( temp !=0){
            digits++;
            temp /= 10;
        }
        temp = num;
        while (temp != 0){
            int digit = temp%10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        if(sum == OriginalNumber){
            System.out.println(OriginalNumber + " Is a Armstrong");
        }
        else {
            System.out.println(OriginalNumber + "Is not an Armstrong");
        }

    }

}

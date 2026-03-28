public class number_reverse {
    public static void main(String[] args){
        int number = 5689794;
        int reversedNumber = 0;
        while(number != 0){
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        System.out.println("Reversed Number: " + reversedNumber);
    }
}
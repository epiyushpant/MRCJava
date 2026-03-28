public class swap_number{
    public static void main(String[] args){
        int a = 5;
        int b = 10;
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        int temp = a;
        a = b;
        b = temp;
        System.out.println("After swapping: a = " + a + ", b = " + b);

    }
}
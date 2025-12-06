public class counting_odd_even{
    public static void main(String[] args){
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        int oddcount = 0;;
        int evencount =0;
        for(int number: numbers){
            System.out.println(number);
            if(number % 2 == 0){
                evencount++;
            }else{
                oddcount++;
            }
        }
        System.out.println("Total even numbers: " + evencount);
        System.out.println("Total odd numbers: " + oddcount);
    }
}

public class prime_number {
    public static void main(String[] args){
        int num = 3;
        boolean isPrime = true;
        if(num <=1){
            isPrime = false;
        }
        for(int i = 2; i*i < num; i++){
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }
        System.out.println("Is " + num + " a prime number? " + isPrime);

    }
}

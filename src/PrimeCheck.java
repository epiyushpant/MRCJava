public class PrimeCheck {
    public static void main(String[] args) {
        int n = 17;
        boolean prime = true;

        if (n <= 1) prime = false;
        else {
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    prime = false;
                    break;
                }
            }
        }

        System.out.println(n + " is prime? " + prime);
    }
}

import java.util.Arrays;

public class Problem3_CountEvenOdd {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int even = 0, odd = 0;
        for (int v : arr) {
            if (v % 2 == 0) even++; else odd++;
        }
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Even count: " + even + ", Odd count: " + odd);
    }
}

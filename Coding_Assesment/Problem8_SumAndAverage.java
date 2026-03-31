import java.util.Arrays;

public class Problem8_SumAndAverage {
    public static double[] sumAndAverage(int[] arr) {
        long sum = 0;
        for (int v : arr) sum += v;
        double avg = arr.length == 0 ? 0.0 : (double)sum / arr.length;
        return new double[] { (double)sum, avg };
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        double[] res = sumAndAverage(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Sum = " + (long)res[0] + ", Average = " + res[1]);
    }
}

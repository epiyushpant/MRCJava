import java.util.Arrays;

public class Problem9_CheckValueInArray {
    public static boolean contains(int[] arr, int value) {
        for (int v : arr) if (v == value) return true;
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2,4,6,8,10,7};
        int value = 7;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Contains " + value + "? " + contains(arr, value));
    }
}

public class ValueCheck {
    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20};
        int value = 15;
        boolean found = false;

        for (int x : arr) {
            if (x == value) {
                found = true;
                break;
            }
        }

        System.out.println("Value found? " + found);
    }
}

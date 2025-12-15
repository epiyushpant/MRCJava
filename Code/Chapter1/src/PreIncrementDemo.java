public class PreIncrementDemo {
    public static void demonstratePreIncrement() {
        int i = 5;
        int j = ++i; // i becomes 6, then 6 is assigned to j
        System.out.println("i: " + i + ", j: " + j); // Output: i: 6, j: 6
    }

    public static void demonstratePostIncrement() {
        int i = 5;
        int j = i++; // asigned to j and i increase to 6
        System.out.println("i: " + i + ", j: " + j); // Output: i: 5, j: 6
    }

}

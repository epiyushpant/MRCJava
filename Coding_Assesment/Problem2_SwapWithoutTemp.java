public class Problem2_SwapWithoutTemp {
    public static void main(String[] args) {
        int a = 15, b = 27;
        System.out.println("Arithmetic swap - before: a=" + a + ", b=" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("Arithmetic swap - after : a=" + a + ", b=" + b);

        a = 100; b = 200;
        System.out.println("XOR swap - before: a=" + a + ", b=" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("XOR swap - after : a=" + a + ", b=" + b);
    }
}

public class Problem7_CelsiusToFahrenheit {
    public static double celsiusToFahrenheit(double c) {
        return c * 9.0 / 5.0 + 32.0;
    }

    public static void main(String[] args) {
        double c = 37.0;
        System.out.println(c + "C -> " + celsiusToFahrenheit(c) + "F");
    }
}

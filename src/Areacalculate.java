public class Areacalculate {
    // Area of circle
    static void area(double radius) {
        double result = 3.14 * radius * radius;
        System.out.println("Area of circule: " + result);
    }
        // Area of rectangle
          static void area(double length, double breadth) {
              double result = length * breadth;
              System.out.println("Area of rectangle: " + result);
          }
            //Area of square
             static void area(int side) {
                 int result = side * side;
                 System.out.println("Area of square: " + result);
             }
        public static void main(String[] args) {
            area(5.0);
            area(4.0, 6.0);
            area(4);
        }
}

public class Problem6_ReverseString {
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println('"' + s + '"' + " reversed -> " + '"' + reverseString(s) + '"');
    }
}

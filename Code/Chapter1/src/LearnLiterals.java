public class LearnLiterals {

    public static void GetLiterals() {
        // decimal-form literal
        int a = 101;
        // octal-form literal
        int b = 0100;
        // Hexa-decimal form literal
        int c = 0xFace;
        // Binary literal
        int d = 0b1111;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static void GetFloatingPointLiterals(){
        // decimal-form literal (float type suffix 'f' or 'F' is required)
        float a = 101.230f;

        // It is a decimal literal despite the leading zero
        float b = 0123.222f;

        // Hexadecimal floating-point literals are NOT supported in Java and will cause an error

        // This line causes compilation error
        // float c = 0x123.222;

        System.out.println(a);
        System.out.println(b);

        // Commented out due to error
        // System.out.println(c);
    }

    public static void GetCharLiterals(){
        // 1. Decimal literal
                char ch1 = 65;        // Decimal → 'A'

                // 2. Octal literal
                char ch2 = 0101;      // Octal 0101 → Decimal 65 → 'A'
                char ch4 = 062;       // Octal 062 → Decimal 50 → '2'

                // 3. Hexadecimal literal
                char ch3 = 0x41;      // Hex 41 → Decimal 65 → 'A'

                // 4. Unicode representation
                char ch5 = '\u0061';  // Unicode → 'a'

                // 5. Escape sequence
                char ch6 = '\n';      // Newline character

                // Print results
                System.out.println("ch1 (Decimal 65): " + ch1);
                System.out.println("ch2 (Octal 0101): " + ch2);
                System.out.println("ch3 (Hex 0x41): " + ch3);
                System.out.println("ch4 (Octal 062): " + ch4);
                System.out.println("ch5 (Unicode \\u0061): " + ch5);
                System.out.println("ch6 (Escape \\n): " + ch6); // prints a newline
    }

    public static void GetBooleanLiterals(){
        boolean b = true;
        boolean c = false;

        // The following lines cause compilation
        // errors and are commented out

        // boolean d = 0;
        // boolean e = 1;

        System.out.println(b);
        System.out.println(c);

        // System.out.println(d);
        // System.out.println(e);
    }


    public static void GetStringLiterals(){
        String s = "Hello";

        // Without double quotes, it is treated as a variable and causes a compiler error
        // String s1 = Hello;

        System.out.println(s);

        // commented out due to error
        // System.out.println(s1);
    }


}

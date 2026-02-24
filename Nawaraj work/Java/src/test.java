public class test{
    public static void main(String[] args){
        // Variables in Java
//        int a = 6;
//        int b = 9;
//        String name = " Nabaraj ";
//        char ch = 'A';
//            System.out.println("This is sun: " + (a + b));

        //Data Types in Java
            //There are two types data types in java: 1) Primitive Data Type and 2) Non-Primitive Data Type
            // Primitive Data Type: It's specify the types of variables and the types of value it can hold
            //Types of Primitive Data Types: Char, byte, long. short, Boolean, double (store 15 - 16 decimal digits), float (store 6-7 decimal digits), int
            // Non-Primitive Data Types: String, Arrays and Classes are the types of non-primitive datat types.

        int myNum = 12;
//        mNum = "Hello";  can't assign string to int;

        String myText = " Hi";
        // myText = 123; can't assign int to String
        System.out.println(myNum + myText);
// Boolean is one of the types of data type that can only have one or two values that is true or false
//        boolean JavaIsFun = true;
//        boolean JavaIsBoaring = false;
//        System.out.println(JavaIsFun);
//        System.out.println(JavaIsBoaring);
//        // The 'var' keyword: The var keyword automatically detects the what kinds of variable it is based on the value assigned to it.
//        var test = "hello ";
//        var num = 123;
//        System.out.println(test + num);

        //Type casting: It is the process of converting one data type into another data type.
        // There are two types of Type Casting in JAVA They are:
        //1. Widening Casting (It is done automatically and it converts the small type to the large type) and
        // 2. Narrowing Casting (It is done manually by placing () this in front of the value and it converts the large type to the small type)
        int num = 11;
        double Num = num;
        System.out.println("Widening Type Casting: " + Num);

        float flo = 99.99f;
        int myInt = (int) flo;
        System.out.println("Narrowing Type Casting: " + myInt);
    }


}
public class PrimitiveTypeCasting {

   public void demoCasting(){

        // --- Widening (Implicit) Type Casting ---
        // Converting a smaller data type to a larger data type.
        // Java automatically handles this as there is no data loss.

        int intValue = 100;
        long longValue = intValue; // int to long
        float floatValue = longValue; // long to float
        double doubleValue = floatValue; // float to double

        System.out.println("--- Widening (Implicit) Casting ---");
        System.out.println("int value: " + intValue);
        System.out.println("long value (from int): " + longValue);
        System.out.println("float value (from long): " + floatValue);
        System.out.println("double value (from float): " + doubleValue);
        System.out.println();

        // --- Narrowing (Explicit) Type Casting ---
        // Converting a larger data type to a smaller data type.
        // This requires explicit casting using the (type) operator,
        // as there might be a loss of data or precision.

        double largeDouble = 123.456;
        int castToInt = (int) largeDouble; // double to int (truncates decimal)

       // long largeLong = 9876543210L;
        long largeLong = 123456;
        int castFromLong = (int) largeLong; // long to int (potential data loss if value exceeds int range)

        float largeFloat = 3.14159f;
        byte castToByte = (byte) largeFloat; // float to byte (truncates decimal and potential overflow)

        System.out.println("--- Narrowing (Explicit) Casting ---");
        System.out.println("Original double: " + largeDouble);
        System.out.println("Cast to int: " + castToInt);
        System.out.println("Original long: " + largeLong);
        System.out.println("Cast to int (from long): " + castFromLong);
        System.out.println("Original float: " + largeFloat);
        System.out.println("Cast to byte: " + castToByte);

    }
}
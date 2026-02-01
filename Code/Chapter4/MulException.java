import java.io.*;  
public class MulException {  
    public static void main(String[] args) {  
        try {  
            // Code that may throw multiple exceptions  
            int[] numbers = {1, 2, 3};  
            System.out.println(numbers[10]); // May throw ArrayIndexOutOfBoundsException  
  
            String str = "abc";  
            int num = Integer.parseInt(str); // May throw NumberFormatException  
  
            FileReader file = new FileReader("test.txt"); // May throw FileNotFoundException  
  
        } catch (ArrayIndexOutOfBoundsException e) {  
            System.out.println("Error: Array index is out of bounds.");  
        } catch (NumberFormatException e) {  
            System.out.println("Error: Invalid number format.");  
        } catch (FileNotFoundException e) {  
            System.out.println("Error: File not found.");  
        } catch (Exception e) {  
            System.out.println("Error: An unexpected exception occurred.");  
        }  
        finally {  
            System.out.println("Finally block executed.");  
        }
  
        System.out.println("Program continues after handling exceptions...");  
    }  
}  
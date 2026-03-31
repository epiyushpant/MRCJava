// swapping two number using 3rd variable

public class SwapNumbers {
 public static void main(String[] args){
  
    int a = 10;
    int b = 20;
    
    System.out.println("before swapping");
    System.out.println("a = "+a);
    System.out.println("b = "+b);
    
    //swapping using the third variable
    int temp = a;
     a = b;
     b = temp;
    
    System.out.println("\nAfter Swapping");
    System.out.println("a ="+a);
    System.out.println("b = "+b);
 }
}
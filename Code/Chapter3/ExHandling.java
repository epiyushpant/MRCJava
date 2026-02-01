public class ExHandling  {
    public static void main(String args[]) {     
        
       try{ 
        int data =100/0 ; 
       }
       catch(ArithmeticException ex){
           System.out.println(ex);    
           //makes easy to debug in application . 
       }
       /* This code is blocked if exception handling is not used */
       System.out.println("Hello World 2!");
       System.out.println("Hello World 3!");
    }   

        
    //    System.out.println("Hello World!");
    //    int data = 100/0;  
      //System.out.println(ex.getMessage());
     //ex.printStackTrace();  // prints the complete error stack trace , method name and line number of exception 
    
}



    

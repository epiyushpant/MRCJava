// Custom Checked Exception
// Custom Runtime Exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Main Class
public class Employee {

    // Method to validate age
    static void validate(int age) {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above.");
        }
        System.out.println("Valid age: " + age);
    }

    public static void main(String[] args) {

        try {
            validate(12);   // Invalid age
        } catch (InvalidAgeException e) {
            System.out.println("Exception Handled: " + e.getMessage());
        }

        System.out.println("Program continues normally...");
    }
}


//use throws to ptropagate exception to caller method
//here in main method the exception is caught and handled 
//InvalidAgeException is a checked exception if  it extends Exception class
//if InvalidAgeException extended RuntimeException then it would be an unchecked exception

//Checked exceptions must be either caught or declared in the method signature using throws keyword
//Unchecked exceptions do not require explicit handling , but its better to handle it 

//here it is unchecked exception as it extends RuntimeException , and hence no need to declare throws in method signature
# 📘 Exception Handling in Java - Complete Notes

---

## 📌 Table of Contents

1. [What is an Exception?](#1-what-is-an-exception)
2. [Why Exception Handling?](#2-why-exception-handling)
3. [Types of Exceptions](#3-types-of-exceptions)
4. [Exception Handling Keywords](#4-exception-handling-keywords)
5. [Basic try-catch Block](#5-basic-try-catch-block)
6. [The finally Block](#6-the-finally-block)
7. [Multiple Exceptions Handling](#7-multiple-exceptions-handling)
8. [throw Keyword](#8-throw-keyword)
9. [throws Keyword](#9-throws-keyword)
10. [Custom Exceptions](#10-custom-exceptions)
11. [Checked vs Unchecked Exceptions](#11-checked-vs-unchecked-exceptions)
12. [Exception Methods](#12-exception-methods)
13. [Best Practices](#13-best-practices)
14. [Code Examples Summary](#14-code-examples-summary)

---

## 1. What is an Exception?

An **exception** is an unwanted or unexpected event that occurs during the execution of a program and disrupts the normal flow of instructions.

### Common Examples:
- **ArithmeticException** - Division by zero
- **ArrayIndexOutOfBoundsException** - Accessing invalid array index
- **NullPointerException** - Accessing null object reference
- **NumberFormatException** - Invalid number conversion
- **FileNotFoundException** - File does not exist

---

## 2. Why Exception Handling?

Without exception handling, if an error occurs, the program terminates abnormally and the remaining code is **not executed**.

### ❌ Without Exception Handling:
```java
System.out.println("Hello World!");
int data = 100/0;              // ⚠️ Program crashes here
System.out.println("Hello World 2!");  // ❌ Never executes
System.out.println("Hello World 3!");  // ❌ Never executes
```

### ✅ With Exception Handling:
```java
try { 
    int data = 100/0; 
} catch(ArithmeticException ex) {
    System.out.println(ex);     // Handles the exception
}

// ✅ These lines WILL execute
System.out.println("Hello World 2!");
System.out.println("Hello World 3!");
```

> **Key Benefit:** Exception handling maintains the **normal flow** of the program!

---

## 3. Types of Exceptions

```
         ┌──────────────────┐
         │    Throwable     │
         └────────┬─────────┘
                  │
        ┌─────────┴─────────┐
        │                   │
   ┌────▼────┐        ┌─────▼────┐
   │  Error  │        │ Exception │
   └─────────┘        └─────┬────┘
   (Unrecoverable)          │
                    ┌───────┴───────┐
                    │               │
          ┌─────────▼──────┐  ┌─────▼─────────────────┐
          │ RuntimeException│  │ Checked Exceptions   │
          │   (Unchecked)   │  │ (IOException, etc.)  │
          └────────────────┘  └──────────────────────┘
```

| Type | Description | Must Handle? |
|------|-------------|--------------|
| **Checked** | Compile-time exceptions (e.g., IOException) | ✅ Yes |
| **Unchecked** | Runtime exceptions (e.g., ArithmeticException) | ❌ No (but recommended) |
| **Error** | System-level errors (e.g., OutOfMemoryError) | ❌ Cannot handle |

---

## 4. Exception Handling Keywords

| Keyword | Purpose |
|---------|---------|
| `try` | Encloses code that might throw an exception |
| `catch` | Handles the exception |
| `finally` | Always executes (cleanup code) |
| `throw` | Manually throws an exception |
| `throws` | Declares exceptions a method might throw |

---

## 5. Basic try-catch Block

### Syntax:
```java
try {
    // Code that may throw exception
} catch (ExceptionType variableName) {
    // Handle the exception
}
```

### Example: ArithmeticException
```java
public class ExHandling {
    public static void main(String args[]) {     
        try { 
            int data = 100/0;   // Throws ArithmeticException
        } catch(ArithmeticException ex) {
            System.out.println(ex);    // Output: java.lang.ArithmeticException: / by zero
        }
        
        // ✅ Code continues normally
        System.out.println("Hello World 2!");
        System.out.println("Hello World 3!");
    }   
}
```

---

## 6. The finally Block

The `finally` block **always executes** regardless of whether an exception occurred or not; it's used for **cleanup operations** like closing files, database connections, etc.

### Syntax:
```java
try {
    // Risky code
} catch (Exception e) {
    // Handle exception
} finally {
    // Cleanup code - ALWAYS executes
}
```

### Example: Division with finally
```java
class MathDemo {
    static int divide(int a, int b) throws ArithmeticException {
        return a / b;  // May throw ArithmeticException
    }

    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero!");
        } finally {
            System.out.println("Finally block executed.");  // ✅ Always prints
        }
    }
}
```

**Output:**
```
Error: Division by zero!
Finally block executed.
```

---

## 7. Multiple Exceptions Handling

### Method 1: Multiple catch Blocks
```java
try {
    int[] numbers = {1, 2, 3};
    System.out.println(numbers[5]);        // ArrayIndexOutOfBoundsException
    
    String str = "abc";
    int num = Integer.parseInt(str);       // NumberFormatException
    
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Error: Array index is out of bounds.");
} catch (NumberFormatException e) {
    System.out.println("Error: Invalid number format.");
} finally {
    System.out.println("Finally block executed.");
}
```

### Method 2: Parent Exception Class
```java
try {
    // Multiple risky operations
} catch (Exception e) {
    System.out.println("Error: An unexpected exception occurred.");
} finally {
    System.out.println("Finally block executed.");
}
```

> ⚠️ **Note:** When catching multiple exceptions, always catch **specific** exceptions before **general** ones!

---

## 8. throw Keyword

The `throw` keyword is used to **explicitly throw** an exception (either built-in or custom).

### Syntax:
```java
throw new ExceptionType("Error message");
```

### Example: Throwing Custom Exception
```java
static void validate(int age) {
    if (age < 18) {
        throw new InvalidAgeException("Age must be 18 or above.");
    }
    System.out.println("Valid age: " + age);
}
```

### Example: Bank Withdrawal
```java
static void withdraw(double balance, double amount) 
        throws InsufficientBalanceException {
    if (amount > balance) {
        throw new InsufficientBalanceException(
            "Withdrawal amount exceeds available balance."
        );
    }
    System.out.println("Withdrawal successful.");
    System.out.println("Remaining Balance: " + (balance - amount));
}
```

---

## 9. throws Keyword

The `throws` keyword is used in method signature to **declare** that a method might throw an exception. It **propagates** the exception to the caller method.

### Syntax:
```java
returnType methodName() throws ExceptionType1, ExceptionType2 {
    // Method body
}
```

### Example:
```java
static void withdraw(double balance, double amount) 
        throws InsufficientBalanceException {  // ⬅️ Declares the exception
    
    if (amount > balance) {
        throw new InsufficientBalanceException("...");  // ⬅️ Throws exception
    }
}

public static void main(String[] args) {
    try {
        withdraw(5000, 7000);   // ⬅️ Caller handles the exception
    } catch (InsufficientBalanceException e) {
        System.out.println("Exception Handled: " + e.getMessage());
    }
}
```

### throw vs throws

| `throw` | `throws` |
|---------|----------|
| Used inside a method | Used in method signature |
| Throws an exception | Declares possible exceptions |
| Can throw only one | Can declare multiple |
| Followed by exception instance | Followed by exception class |

---

## 10. Custom Exceptions

You can create your own exception classes by extending:
- **`Exception`** → Checked Exception
- **`RuntimeException`** → Unchecked Exception

### Custom Checked Exception:
```java
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

### Custom Unchecked Exception:
```java
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}
```

### Complete Example: Custom Checked Exception
```java
// Custom Checked Exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class BankAccount {
    
    // Method that declares a checked exception
    static void withdraw(double balance, double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Withdrawal amount exceeds available balance."
            );
        }

        System.out.println("Withdrawal successful.");
        System.out.println("Remaining Balance: " + (balance - amount));
    }

    public static void main(String[] args) {
        try {
            withdraw(5000, 7000);   // Invalid withdrawal
        } catch (InsufficientBalanceException e) {
            System.out.println("Exception Handled: " + e.getMessage());
        }

        System.out.println("Program continues normally...");
    }
}
```

**Output:**
```
Exception Handled: Withdrawal amount exceeds available balance.
Program continues normally...
```

### Complete Example: Custom Unchecked Exception
```java
// Custom Runtime Exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Employee {

    // Method to validate age - No throws required!
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
```

**Output:**
```
Exception Handled: Age must be 18 or above.
Program continues normally...
```

---

## 11. Checked vs Unchecked Exceptions

### Checked Exceptions
- Extend `Exception` class
- **Must** be caught or declared with `throws`
- Checked at **compile-time**
- Examples: IOException, SQLException, FileNotFoundException

```java
class MyCheckedException extends Exception {  // ✅ Checked
    // ...
}

void myMethod() throws MyCheckedException {  // ⬅️ throws is REQUIRED
    throw new MyCheckedException("Error");
}
```

### Unchecked Exceptions
- Extend `RuntimeException` class
- **Do not require** explicit handling
- Checked at **runtime**
- Examples: NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException

```java
class MyUncheckedException extends RuntimeException {  // ✅ Unchecked
    // ...
}

void myMethod() {  // ⬅️ No throws required
    throw new MyUncheckedException("Error");
}
```

### 📊 Comparison Table

| Feature | Checked Exception | Unchecked Exception |
|---------|------------------|---------------------|
| Extends | `Exception` | `RuntimeException` |
| Checked at | Compile-time | Runtime |
| Must handle? | ✅ Yes | ❌ No (but recommended) |
| `throws` required? | ✅ Yes | ❌ No |
| Use case | External failures | Programming errors |

---

## 12. Exception Methods

| Method | Description |
|--------|-------------|
| `getMessage()` | Returns the error message |
| `toString()` | Returns class name + message |
| `printStackTrace()` | Prints complete error stack trace |

### Example:
```java
try {
    int data = 100/0;
} catch(ArithmeticException ex) {
    System.out.println(ex.getMessage());     // "/ by zero"
    System.out.println(ex.toString());       // "java.lang.ArithmeticException: / by zero"
    ex.printStackTrace();                    // Complete stack trace with line numbers
}
```

---

## 13. Best Practices

### ✅ Do:
1. Use **specific** exception types instead of generic `Exception`
2. Always include **meaningful error messages**
3. Use `finally` for **cleanup operations**
4. **Log exceptions** properly for debugging
5. Handle exceptions at the **appropriate level**

### ❌ Don't:
1. Don't catch exceptions and do nothing (empty catch blocks)
2. Don't catch `Throwable` or `Error`
3. Don't use exceptions for **flow control**
4. Don't ignore checked exceptions

---

## 14. Code Examples Summary

| File | Concept | Key Learning |
|------|---------|--------------|
| `ExHandling.java` | Basic try-catch | Prevents program termination |
| `MathDemo.java` | try-catch-finally, throws | Cleanup with finally block |
| `MulException.java` | Multiple catch blocks | Handle different exception types |
| `BankAccount.java` | Custom Checked Exception | Must declare with throws |
| `Employee.java` | Custom Unchecked Exception | No throws required |

---

## 🎯 Quick Reference Card

```
┌─────────────────────────────────────────────────────────────┐
│                 EXCEPTION HANDLING SYNTAX                   │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   try {                                                     │
│       // Code that may throw exception                      │
│   } catch (SpecificException e1) {                          │
│       // Handle specific exception                          │
│   } catch (Exception e2) {                                  │
│       // Handle general exception                           │
│   } finally {                                                │
│       // Always executed (cleanup)                          │
│   }                                                         │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                    CUSTOM EXCEPTION                         │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   // Checked: extends Exception                             │
│   class MyException extends Exception {                     │
│       public MyException(String msg) { super(msg); }        │
│   }                                                         │
│                                                             │
│   // Unchecked: extends RuntimeException                    │
│   class MyRuntimeException extends RuntimeException {       │
│       public MyRuntimeException(String msg) { super(msg); } │
│   }                                                         │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│                    throw vs throws                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   throw new Exception("msg");    // Inside method           │
│   void method() throws Exception // Method signature        │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

> 📝 **Created from code examples in Chapter 4**
> 
> Files: `ExHandling.java`, `MathDemo.java`, `MulException.java`, `BankAccount.java`, `Employee.java`

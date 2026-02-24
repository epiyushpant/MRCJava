# 📘 Common Java Exceptions - Complete Reference

---

## 📌 Table of Contents

1. [Exception Hierarchy](#1-exception-hierarchy)
2. [Checked vs Unchecked Summary](#2-checked-vs-unchecked-summary)
3. [Checked Exceptions](#3-checked-exceptions)
   - [IOException](#31-ioexception)
   - [FileNotFoundException](#32-filenotfoundexception)
   - [ClassNotFoundException](#33-classnotfoundexception)
   - [SQLException](#34-sqlexception)
4. [Unchecked (Runtime) Exceptions](#4-unchecked-runtime-exceptions)
   - [ArithmeticException](#41-arithmeticexception)
   - [NullPointerException](#42-nullpointerexception)
   - [ArrayIndexOutOfBoundsException](#43-arrayindexoutofboundsexception)
   - [StringIndexOutOfBoundsException](#44-stringindexoutofboundsexception)
   - [NumberFormatException](#45-numberformatexception)
   - [IllegalArgumentException](#46-illegalargumentexception)
5. [Quick Reference Table](#5-quick-reference-table)
6. [Best Practices](#6-best-practices)

---

## 1. Exception Hierarchy

```
                    ┌──────────────────┐
                    │    Throwable     │
                    └────────┬─────────┘
                             │
              ┌──────────────┴──────────────┐
              │                             │
       ┌──────▼──────┐              ┌───────▼───────┐
       │    Error    │              │   Exception   │
       └─────────────┘              └───────┬───────┘
       (Unrecoverable)                      │
                              ┌─────────────┴─────────────┐
                              │                           │
                    ┌─────────▼─────────┐       ┌─────────▼─────────┐
                    │ RuntimeException  │       │ Checked Exceptions│
                    │   (Unchecked)     │       │                   │
                    └─────────┬─────────┘       └─────────┬─────────┘
                              │                           │
        ┌─────────────────────┼───────────────┐          │
        │                     │               │          │
   ┌────▼────┐  ┌────────────▼──┐  ┌────────▼────┐  ┌───▼────────────┐
   │Arithmetic│  │NullPointer    │  │ArrayIndex   │  │IOException     │
   │Exception │  │Exception      │  │OutOfBounds  │  │FileNotFound    │
   └──────────┘  └───────────────┘  └─────────────┘  │SQLException    │
                                                      │ClassNotFound   │
                                                      └────────────────┘
```

---

## 2. Checked vs Unchecked Summary

| Feature | Checked Exceptions | Unchecked Exceptions |
|---------|-------------------|---------------------|
| **Extends** | `Exception` | `RuntimeException` |
| **Checked at** | Compile-time | Runtime |
| **Must handle?** | ✅ Yes (mandatory) | ❌ No (optional) |
| **Caused by** | External factors | Programming errors |
| **Examples** | IOException, SQLException | NullPointerException, ArithmeticException |

---

## 3. Checked Exceptions

Checked exceptions **must** be either caught or declared with `throws`. They represent recoverable conditions caused by external factors.

---

### 3.1 IOException

**What:** Occurs during Input/Output operations (reading/writing files, network operations).

**When it occurs:**
- Reading from a closed stream
- Writing to a read-only file
- Network connection issues
- Disk full errors

**Example:**
```java
import java.io.*;

public class IOExceptionDemo {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(reader);
            
            String line;
            while ((line = br.readLine()) != null) {  // IOException can occur
                System.out.println(line);
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
    }
}
```

**How to handle:**
```java
// Method 1: try-catch
try {
    // IO operations
} catch (IOException e) {
    e.printStackTrace();
}

// Method 2: Declare with throws
public void readFile() throws IOException {
    // IO operations
}

// Method 3: try-with-resources (Recommended)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // Auto-closes the resource
}
```

---

### 3.2 FileNotFoundException

**What:** A subclass of IOException. Occurs when trying to access a file that doesn't exist.

**When it occurs:**
- Opening a non-existent file for reading
- File path is incorrect
- File was deleted

**Example:**
```java
import java.io.*;

public class FileNotFoundDemo {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("nonexistent.txt");
            System.out.println("File opened successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found: " + e.getMessage());
        }
    }
}
```

**How to prevent:**
```java
import java.io.*;

File file = new File("data.txt");

// Check if file exists before reading
if (file.exists() && file.isFile()) {
    FileReader reader = new FileReader(file);
    // Read file...
} else {
    System.out.println("File does not exist!");
}
```

---

### 3.3 ClassNotFoundException

**What:** Occurs when JVM tries to load a class dynamically but cannot find it.

**When it occurs:**
- Using `Class.forName()` with wrong class name
- Missing JAR file in classpath
- Typo in fully qualified class name

**Example:**
```java
public class ClassNotFoundDemo {
    public static void main(String[] args) {
        try {
            // Try to load a class dynamically
            Class<?> clazz = Class.forName("com.example.NonExistentClass");
            System.out.println("Class loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Class not found: " + e.getMessage());
        }
    }
}
```

**Common scenarios:**
```java
// Loading JDBC driver (older approach)
try {
    Class.forName("com.mysql.cj.jdbc.Driver");  // Must be in classpath
} catch (ClassNotFoundException e) {
    System.out.println("MySQL driver not found!");
}
```

---

### 3.4 SQLException

**What:** Occurs during database operations.

**When it occurs:**
- Invalid SQL syntax
- Database connection failure
- Table or column doesn't exist
- Constraint violations

**Example:**
```java
import java.sql.*;

public class SQLExceptionDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "password";
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            
            // This might fail if table doesn't exist
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Database error!");
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
        }
    }
}
```

**Best practice with try-with-resources:**
```java
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users")) {
    
    ResultSet rs = stmt.executeQuery();
    // Process results...
    
} catch (SQLException e) {
    e.printStackTrace();
}
```

---

## 4. Unchecked (Runtime) Exceptions

Runtime exceptions occur due to **programming errors** and don't require explicit handling (but should be prevented).

---

### 4.1 ArithmeticException

**What:** Occurs during illegal arithmetic operations.

**When it occurs:**
- Division by zero (integers only)
- Modulo by zero

**Example:**
```java
public class ArithmeticExceptionDemo {
    public static void main(String[] args) {
        
        // ❌ Division by zero
        try {
            int a = 100;
            int b = 0;
            int result = a / b;  // Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // ✅ How to prevent
        int a = 100;
        int b = 0;
        if (b != 0) {
            int result = a / b;
        } else {
            System.out.println("Cannot divide by zero!");
        }
        
        // ⚠️ Note: Floating-point division by zero gives Infinity, not exception
        double x = 100.0 / 0;  // Result: Infinity (no exception)
        System.out.println("100.0 / 0 = " + x);
    }
}
```

---

### 4.2 NullPointerException

**What:** The most common exception! Occurs when using a null reference.

**When it occurs:**
- Calling method on null object
- Accessing field of null object
- Getting length of null array
- Throwing null as exception

**Example:**
```java
public class NullPointerExceptionDemo {
    public static void main(String[] args) {
        
        // ❌ Calling method on null
        String str = null;
        try {
            int length = str.length();  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("❌ Null reference: " + e.getMessage());
        }
        
        // ❌ Accessing array element
        String[] names = null;
        try {
            System.out.println(names[0]);  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("❌ Array is null");
        }
        
        // ✅ How to prevent - Null checks
        String text = null;
        if (text != null) {
            System.out.println(text.length());
        } else {
            System.out.println("Text is null!");
        }
        
        // ✅ Using Objects.requireNonNull()
        // Objects.requireNonNull(text, "Text cannot be null");
    }
}
```

**Prevention techniques:**
```java
// 1. Null check
if (obj != null) {
    obj.method();
}

// 2. Optional (Java 8+)
Optional<String> optional = Optional.ofNullable(str);
optional.ifPresent(s -> System.out.println(s.length()));

// 3. Objects utility
Objects.requireNonNull(obj, "Object cannot be null");
```

---

### 4.3 ArrayIndexOutOfBoundsException

**What:** Occurs when accessing an invalid array index.

**When it occurs:**
- Index is negative
- Index >= array length

**Example:**
```java
public class ArrayIndexDemo {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};  // Valid indices: 0-4
        
        // ❌ Index too high
        try {
            System.out.println(numbers[5]);  // Array has only 5 elements (0-4)
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ Invalid index: " + e.getMessage());
        }
        
        // ❌ Negative index
        try {
            System.out.println(numbers[-1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ Negative index not allowed");
        }
        
        // ✅ How to prevent - Bounds checking
        int index = 3;
        if (index >= 0 && index < numbers.length) {
            System.out.println("Value: " + numbers[index]);
        } else {
            System.out.println("Index out of bounds!");
        }
        
        // ✅ Using for-each loop (safest)
        for (int num : numbers) {
            System.out.println(num);
        }
    }
}
```

---

### 4.4 StringIndexOutOfBoundsException

**What:** Occurs when accessing an invalid character index in a String.

**When it occurs:**
- Using `charAt()` with invalid index
- Using `substring()` with invalid range
- Index negative or >= string length

**Example:**
```java
public class StringIndexDemo {
    public static void main(String[] args) {
        String str = "Hello";  // Length: 5, Valid indices: 0-4
        
        // ❌ Invalid charAt()
        try {
            char ch = str.charAt(10);  // Index 10 doesn't exist
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ Invalid char index: " + e.getMessage());
        }
        
        // ❌ Invalid substring()
        try {
            String sub = str.substring(2, 10);  // End index too high
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ Invalid substring range");
        }
        
        // ❌ Negative index
        try {
            char ch = str.charAt(-1);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ Negative index not allowed");
        }
        
        // ✅ How to prevent
        int index = 3;
        if (index >= 0 && index < str.length()) {
            System.out.println("Character: " + str.charAt(index));
        }
        
        // ✅ Safe substring
        int start = 0, end = 3;
        if (start >= 0 && end <= str.length() && start <= end) {
            System.out.println("Substring: " + str.substring(start, end));
        }
    }
}
```

---

### 4.5 NumberFormatException

**What:** Occurs when converting an invalid string to a number.

**When it occurs:**
- `Integer.parseInt("abc")` - not a valid number
- `Double.parseDouble("12.34.56")` - invalid format
- Empty string conversion

**Example:**
```java
public class NumberFormatDemo {
    public static void main(String[] args) {
        
        // ❌ Invalid integer
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid integer: 'abc'");
        }
        
        // ❌ Contains non-numeric characters
        try {
            int num = Integer.parseInt("123abc");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid integer: '123abc'");
        }
        
        // ❌ Empty string
        try {
            int num = Integer.parseInt("");
        } catch (NumberFormatException e) {
            System.out.println("❌ Cannot convert empty string");
        }
        
        // ❌ Floating-point to integer
        try {
            int num = Integer.parseInt("12.34");
        } catch (NumberFormatException e) {
            System.out.println("❌ Cannot parse '12.34' as integer");
        }
        
        // ✅ How to prevent - Validate before parsing
        String input = "12345";
        if (input != null && input.matches("-?\\d+")) {
            int num = Integer.parseInt(input);
            System.out.println("Parsed: " + num);
        } else {
            System.out.println("Invalid number format!");
        }
        
        // ✅ Using try-catch for user input
        String userInput = "42";
        try {
            int value = Integer.parseInt(userInput.trim());
            System.out.println("Valid number: " + value);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
}
```

---

### 4.6 IllegalArgumentException

**What:** Thrown when a method receives an invalid argument.

**When it occurs:**
- Passing negative value when positive expected
- Passing null when non-null expected
- Passing value outside valid range

**Example:**
```java
public class IllegalArgumentDemo {
    
    // Method that validates age
    static void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150. Got: " + age);
        }
        System.out.println("Age set to: " + age);
    }
    
    // Method that validates percentage
    static void setPercentage(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percentage must be 0-100. Got: " + percent);
        }
        System.out.println("Percentage: " + percent + "%");
    }
    
    // Method that validates non-null
    static void processName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        System.out.println("Processing: " + name);
    }
    
    public static void main(String[] args) {
        
        // ❌ Invalid age
        try {
            setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        // ❌ Invalid percentage
        try {
            setPercentage(150.0);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        // ❌ Empty name
        try {
            processName("");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        // ✅ Valid inputs
        setAge(25);
        setPercentage(85.5);
        processName("John Doe");
    }
}
```

**When to throw IllegalArgumentException:**
```java
// Precondition checking at method start
public void transfer(double amount, String recipient) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
    if (recipient == null || recipient.isEmpty()) {
        throw new IllegalArgumentException("Recipient cannot be empty");
    }
    // Proceed with transfer...
}
```

---

## 5. Quick Reference Table

### Checked Exceptions

| Exception | Cause | Prevention/Handling |
|-----------|-------|-------------------|
| **IOException** | File/stream read/write errors | try-catch, try-with-resources |
| **FileNotFoundException** | File doesn't exist | Check `file.exists()` before |
| **ClassNotFoundException** | Class not in classpath | Verify class name & classpath |
| **SQLException** | Database operation fails | Validate SQL, check connection |

### Unchecked Exceptions

| Exception | Cause | Prevention |
|-----------|-------|------------|
| **ArithmeticException** | Division by zero | Check divisor `!= 0` |
| **NullPointerException** | Using null reference | Null checks, Optional |
| **ArrayIndexOutOfBoundsException** | Invalid array index | Check `0 <= i < length` |
| **StringIndexOutOfBoundsException** | Invalid string index | Check `0 <= i < length()` |
| **NumberFormatException** | Invalid number string | Validate format, try-catch |
| **IllegalArgumentException** | Invalid method argument | Validate inputs at method start |

---

## 6. Best Practices

### ✅ For Checked Exceptions:
```java
// 1. Use try-with-resources for auto-closing
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    // Read file...
} catch (IOException e) {
    logger.error("File read error", e);
}

// 2. Catch specific exceptions first
try {
    // Code...
} catch (FileNotFoundException e) {
    // Handle missing file
} catch (IOException e) {
    // Handle other IO errors
}
```

### ✅ For Unchecked Exceptions:
```java
// 1. Validate before using
if (str != null && !str.isEmpty()) {
    int num = Integer.parseInt(str);
}

// 2. Use defensive programming
public void process(String input) {
    Objects.requireNonNull(input, "Input cannot be null");
    // Continue...
}

// 3. Fail fast with meaningful messages
if (amount < 0) {
    throw new IllegalArgumentException(
        "Amount must be non-negative. Received: " + amount);
}
```

### ❌ What NOT to do:
```java
// Don't ignore exceptions
try {
    // Code...
} catch (Exception e) {
    // Empty catch block - BAD!
}

// Don't catch generic Exception when specific is available
try {
    // Code...
} catch (Exception e) {  // Too broad
    // Hard to know what went wrong
}
```

---

## 🎯 Quick Cheat Sheet

```
┌─────────────────────────────────────────────────────────────────┐
│                    CHECKED EXCEPTIONS                           │
│                (Must handle or declare throws)                  │
├─────────────────────────────────────────────────────────────────┤
│  IOException          →  File/Stream I/O errors                │
│  FileNotFoundException →  File doesn't exist                    │
│  ClassNotFoundException→  Class not found (dynamic loading)    │
│  SQLException         →  Database errors                       │
├─────────────────────────────────────────────────────────────────┤
│                   UNCHECKED EXCEPTIONS                          │
│                  (Runtime - programming bugs)                   │
├─────────────────────────────────────────────────────────────────┤
│  ArithmeticException           →  10 / 0                       │
│  NullPointerException          →  null.method()                │
│  ArrayIndexOutOfBoundsException→  arr[arr.length]              │
│  StringIndexOutOfBoundsException→ str.charAt(str.length())     │
│  NumberFormatException         →  Integer.parseInt("abc")      │
│  IllegalArgumentException      →  setAge(-5)                   │
└─────────────────────────────────────────────────────────────────┘
```

---

> 📝 **Common Java Exceptions Reference - Chapter 4**

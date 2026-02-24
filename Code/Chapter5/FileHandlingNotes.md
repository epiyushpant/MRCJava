# 📘 Unit 5: Using I/O in Java - Complete Notes

---

## 📌 Table of Contents

1. [5.1 Console and File I/O](#51-console-and-file-io)
2. [5.2 Opening and Closing Files](#52-opening-and-closing-files)
3. [5.3 Scanner Class](#53-scanner-class)
4. [5.4 Byte Streams and Character Streams](#54-byte-streams-and-character-streams)
5. [5.5 Reading and Writing Byte Streams](#55-reading-and-writing-byte-streams)
6. [5.6 Reading and Writing Character Streams](#56-reading-and-writing-character-streams)
7. [5.7 Random Access Files](#57-random-access-files)
8. [Best Practices](#best-practices)
9. [Code Examples Summary](#code-examples-summary)

---

## 5.1 Console and File I/O

### What is I/O?

**I/O (Input/Output)** is the process of reading data **into** a program (input) and sending data **out** from a program (output).

```
┌──────────────────────────────────────────────────────┐
│                    Java I/O                          │
│                                                      │
│   INPUT (Reading)          OUTPUT (Writing)           │
│   ─────────────            ──────────────             │
│   Keyboard  ──►            ──► Screen (Console)      │
│   File      ──►  Program   ──► File                  │
│   Network   ──►            ──► Network               │
│                                                      │
└──────────────────────────────────────────────────────┘
```

### Two Types of I/O:

| Type | Source / Destination | Example |
|------|---------------------|---------|
| **Console I/O** | Keyboard & Screen | `System.in`, `System.out` |
| **File I/O** | Files on disk | `FileReader`, `FileWriter`, etc. |

---

### 📺 Console I/O (Keyboard & Screen)

Java provides built-in objects for console operations:

| Object | Type | Purpose |
|--------|------|---------|
| `System.in` | `InputStream` | Reads input from **keyboard** |
| `System.out` | `PrintStream` | Writes output to **screen** |
| `System.err` | `PrintStream` | Writes error messages to **screen** (in red) |

#### Console Output:
```java
// print() - prints without newline
System.out.print("Hello ");
System.out.print("World");
// Output: Hello World

// println() - prints with newline
System.out.println("Hello");
System.out.println("World");
// Output:
// Hello
// World

// printf() - formatted output
String name = "Ram";
int age = 20;
double gpa = 3.85;
System.out.printf("Name: %s, Age: %d, GPA: %.2f%n", name, age, gpa);
// Output: Name: Ram, Age: 20, GPA: 3.85
```

#### Console Input (using System.in directly):
```java
import java.io.IOException;

public class ConsoleInputDemo {
    public static void main(String[] args) throws IOException {
        System.out.print("Press any key: ");
        int data = System.in.read();   // Reads one byte (character)
        System.out.println("You pressed: " + (char) data);
    }
}
```

> ⚠️ `System.in.read()` reads only **one byte** at a time — not practical for real input. That's why we use **Scanner** (Section 5.3).

---

### 📁 File I/O

File I/O lets us **read from** and **write to** files on the disk.

| Operation | Purpose | Key Classes |
|-----------|---------|-------------|
| **Read** | Get data from a file | `FileReader`, `FileInputStream`, `Scanner` |
| **Write** | Save data to a file | `FileWriter`, `FileOutputStream`, `PrintWriter` |
| **Append** | Add data to end of file | `FileWriter(file, true)` |
| **Delete** | Remove a file | `File.delete()` |

> 💡 **Key Difference:** Console I/O is **temporary** (data lost when program ends). File I/O is **permanent** (data saved on disk).

---

### The `java.io` Package

All file I/O classes are in the `java.io` package:

```java
import java.io.File;           // File and directory operations
import java.io.FileReader;     // Read characters from file
import java.io.FileWriter;     // Write characters to file
import java.io.FileInputStream;  // Read bytes from file
import java.io.FileOutputStream; // Write bytes to file
import java.io.BufferedReader; // Efficient character reading
import java.io.BufferedWriter; // Efficient character writing
import java.io.IOException;    // Exception for I/O errors
```

---

## 5.2 Opening and Closing Files

### The File Class

The `java.io.File` class represents a **file or directory path**. It does **NOT** read/write data — it only manages the file on disk.

```java
import java.io.File;
```

#### Creating a File Object:
```java
File file = new File("myfile.txt");           // Relative path
File file2 = new File("C:\\data\\info.txt");  // Absolute path (Windows)
```

> ⚠️ Creating a `File` object does **NOT** create the actual file. It only creates a **reference** to the path.

---

### Creating a File on Disk:
```java
import java.io.File;
import java.io.IOException;

public class CreateFileDemo {
    public static void main(String[] args) {
        try {
            File file = new File("test.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Useful Methods of File Class:

| Method | Return Type | Description |
|--------|-------------|-------------|
| `createNewFile()` | `boolean` | Creates a new file, returns `true` if created |
| `exists()` | `boolean` | Checks if the file/directory exists |
| `getName()` | `String` | Returns the name of the file |
| `getAbsolutePath()` | `String` | Returns the full absolute path |
| `length()` | `long` | Returns the file size in bytes |
| `canRead()` | `boolean` | Checks if the file is readable |
| `canWrite()` | `boolean` | Checks if the file is writable |
| `isFile()` | `boolean` | Returns `true` if it's a file |
| `isDirectory()` | `boolean` | Returns `true` if it's a directory |
| `delete()` | `boolean` | Deletes the file/directory |
| `mkdir()` | `boolean` | Creates a directory |
| `list()` | `String[]` | Lists files in a directory |

---

### Opening Files (Streams)

In Java, we "open" a file by creating a **stream** object that connects to the file:

```java
// Opening a file for READING
FileReader reader = new FileReader("data.txt");       // Character stream
FileInputStream fis = new FileInputStream("data.txt"); // Byte stream

// Opening a file for WRITING
FileWriter writer = new FileWriter("data.txt");         // Overwrite mode
FileWriter appender = new FileWriter("data.txt", true); // Append mode
FileOutputStream fos = new FileOutputStream("data.txt"); // Byte stream
```

> 💡 When you create a `FileWriter` or `FileOutputStream`, the file is automatically **created** if it doesn't exist.

---

### Closing Files ⚠️ (Very Important!)

**Every opened stream MUST be closed** after use. If not closed:
- Data may not be fully written (still in buffer)
- System resources (file handles) are wasted
- Other programs may not be able to access the file

#### Method 1: Manual Close
```java
FileWriter writer = new FileWriter("data.txt");
writer.write("Hello");
writer.close();   // ⚠️ Must close manually!
```

#### Method 2: try-with-resources (Recommended ✅)
```java
// Resource is automatically closed when the block ends
try (FileWriter writer = new FileWriter("data.txt")) {
    writer.write("Hello");
}   // ← writer.close() is called automatically here!
```

#### Why try-with-resources is Better:
```
❌ Manual Close Problem:
   FileWriter writer = new FileWriter("data.txt");
   writer.write("Hello");
   // If an exception occurs HERE → writer.close() never runs!
   writer.close();

✅ try-with-resources:
   try (FileWriter writer = new FileWriter("data.txt")) {
       writer.write("Hello");
       // Even if an exception occurs, writer is STILL closed!
   }
```

### Deleting a File:
```java
File file = new File("test.txt");

if (file.exists()) {
    if (file.delete()) {
        System.out.println("Deleted: " + file.getName());
    } else {
        System.out.println("Failed to delete.");
    }
} else {
    System.out.println("File does not exist.");
}
```

---

## 5.3 Scanner Class

The `Scanner` class (from `java.util`) is the **easiest way** to read input — from both **keyboard** and **files**.

```java
import java.util.Scanner;
```

### Scanner for Console Input (Keyboard):
```java
Scanner sc = new Scanner(System.in);   // ← reads from keyboard

System.out.print("Enter name: ");
String name = sc.nextLine();

System.out.print("Enter age: ");
int age = sc.nextInt();

System.out.print("Enter GPA: ");
double gpa = sc.nextDouble();

System.out.println("Name: " + name + ", Age: " + age + ", GPA: " + gpa);
sc.close();
```

### Scanner for File Input:
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileDemo {
    public static void main(String[] args) {
        try {
            File file = new File("output.txt");
            Scanner sc = new Scanner(file);    // ← reads from file

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
```

### Key Scanner Methods:

| Method | Reads | Returns |
|--------|-------|---------|
| `nextLine()` | Entire line | `String` |
| `next()` | One word (token) | `String` |
| `nextInt()` | An integer | `int` |
| `nextDouble()` | A decimal number | `double` |
| `nextBoolean()` | true or false | `boolean` |
| `hasNextLine()` | Checks if more lines exist | `boolean` |
| `hasNext()` | Checks if more tokens exist | `boolean` |
| `hasNextInt()` | Checks if next token is int | `boolean` |

### Scanner: Console vs File — Same Methods, Different Source!

```
┌──────────────────────────────────────────────────┐
│                                                  │
│  Scanner sc = new Scanner(System.in);  // 🎹     │
│           ↕ Same methods work!                   │
│  Scanner sc = new Scanner(new File("f.txt")); 📁│
│                                                  │
│  sc.nextLine()    → reads one line               │
│  sc.nextInt()     → reads one integer            │
│  sc.hasNextLine() → checks for more lines        │
│                                                  │
└──────────────────────────────────────────────────┘
```

---

## 5.4 Byte Streams and Character Streams

Java has **two categories** of streams for reading and writing data:

```
┌──────────────────────────────────────────────────────────────┐
│                      Java I/O Streams                        │
├──────────────────────┬───────────────────────────────────────┤
│                      │                                       │
│   Character Streams  │         Byte Streams                  │
│   (Text Data)        │         (Binary Data)                 │
│                      │                                       │
│   Unit: 1 character  │    Unit: 1 byte (8 bits)              │
│         (2 bytes)    │                                       │
│                      │                                       │
│   ┌──────────┐       │    ┌───────────────┐                  │
│   │  Reader   │       │    │  InputStream   │                 │
│   └────┬─────┘       │    └──────┬────────┘                  │
│        │             │           │                            │
│   FileReader         │    FileInputStream                    │
│   BufferedReader     │    BufferedInputStream                │
│                      │                                       │
│   ┌──────────┐       │    ┌───────────────┐                  │
│   │  Writer   │       │    │  OutputStream  │                 │
│   └────┬─────┘       │    └──────┬────────┘                  │
│        │             │           │                            │
│   FileWriter         │    FileOutputStream                   │
│   BufferedWriter     │    BufferedOutputStream               │
│   PrintWriter        │                                       │
│                      │                                       │
└──────────────────────┴───────────────────────────────────────┘
```

### Comparison Table:

| Feature | Byte Stream | Character Stream |
|---------|-------------|-----------------|
| **Data Unit** | 1 byte (8 bits) | 1 character (2 bytes / 16 bits) |
| **Best For** | Binary files (images, audio, video, PDF) | Text files (.txt, .csv, .java) |
| **Base Classes** | `InputStream` / `OutputStream` | `Reader` / `Writer` |
| **Read Method** | `read()` → returns byte as `int` | `read()` → returns char as `int` |
| **End of File** | Returns `-1` | Returns `-1` (or `null` for `readLine()`) |
| **File Classes** | `FileInputStream` / `FileOutputStream` | `FileReader` / `FileWriter` |
| **Buffered** | `BufferedInputStream` / `BufferedOutputStream` | `BufferedReader` / `BufferedWriter` |

### When to Use What?

| File Type | Use | Example |
|-----------|-----|---------|
| `.txt`, `.csv`, `.java`, `.html` | **Character Stream** | `FileReader` / `FileWriter` |
| `.jpg`, `.png`, `.mp3`, `.pdf` | **Byte Stream** | `FileInputStream` / `FileOutputStream` |
| Any file (copying) | **Byte Stream** | Works for all file types |

> 💡 **Simple Rule:** Text files → Character Streams. Everything else → Byte Streams.

### What is a Buffer?

A **buffer** is a temporary memory area that collects data before reading/writing.

```
┌───────────────────────────────────────────────────────────┐
│   Without Buffer              With Buffer                 │
│                                                           │
│   Disk ←→ Program             Disk ←→ Buffer ←→ Program  │
│   (1 unit at a time)          (chunk at a time)           │
│                                                           │
│   Slow ❌ (many disk          Fast ✅ (fewer disk         │
│   accesses)                   accesses)                   │
└───────────────────────────────────────────────────────────┘
```

> 💡 Always prefer `BufferedReader`/`BufferedWriter` over plain `FileReader`/`FileWriter` for better performance.

---

## 5.5 Reading and Writing Byte Streams

Byte streams handle **raw binary data** — one byte at a time.

### Key Classes:

| Class | Purpose |
|-------|---------|
| `FileInputStream` | Reads bytes from a file |
| `FileOutputStream` | Writes bytes to a file |
| `BufferedInputStream` | Efficient byte reading (with buffer) |
| `BufferedOutputStream` | Efficient byte writing (with buffer) |

### Writing Bytes with FileOutputStream:
```java
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteWriteDemo {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("bytes_output.txt");

            // Write a string as bytes
            String text = "Hello from Byte Stream!\n";
            byte[] data = text.getBytes();   // Convert String to byte array
            fos.write(data);

            // Write individual bytes (ASCII values)
            fos.write(65);   // 'A'
            fos.write(66);   // 'B'
            fos.write(67);   // 'C'
            fos.write(10);   // newline

            fos.close();
            System.out.println("Written using FileOutputStream.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Reading Bytes with FileInputStream:
```java
import java.io.FileInputStream;
import java.io.IOException;

public class ByteReadDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("bytes_output.txt");

            int byteData;
            // read() returns -1 when end of file is reached
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);   // Cast byte to char
            }

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Copying a File (Byte Stream):
```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyByteDemo {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("bytes_output.txt");
            FileOutputStream fos = new FileOutputStream("bytes_copy.txt")
        ) {
            byte[] buffer = new byte[1024];   // Read 1KB at a time
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### FileOutputStream Modes:

| Constructor | Mode |
|-------------|------|
| `new FileOutputStream("file.txt")` | **Overwrite** (erases existing) |
| `new FileOutputStream("file.txt", true)` | **Append** (adds to end) |

### Important FileInputStream / FileOutputStream Methods:

| Method | Description |
|--------|-------------|
| `read()` | Reads one byte, returns `-1` at EOF |
| `read(byte[] b)` | Reads bytes into array, returns count |
| `write(int b)` | Writes one byte |
| `write(byte[] b)` | Writes entire byte array |
| `write(byte[] b, int off, int len)` | Writes `len` bytes from array starting at `off` |
| `available()` | Returns number of bytes available to read |
| `close()` | Closes the stream |

---

## 5.6 Reading and Writing Character Streams

Character streams handle **text data** — one character (2 bytes) at a time. Better for text files because they handle character encoding automatically.

### Key Classes:

| Class | Purpose |
|-------|---------|
| `FileReader` | Reads characters from a file |
| `FileWriter` | Writes characters to a file |
| `BufferedReader` | Efficient character reading (line by line) |
| `BufferedWriter` | Efficient character writing |
| `PrintWriter` | Convenient formatted text writing |

---

### Writing with FileWriter:
```java
import java.io.FileWriter;
import java.io.IOException;

public class CharWriteDemo {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("char_output.txt");

            writer.write("Hello, Character Stream!\n");
            writer.write("This writes text data.\n");
            writer.write("FileWriter handles encoding automatically.\n");
            writer.close();

            System.out.println("Written using FileWriter.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Reading with FileReader:
```java
import java.io.FileReader;
import java.io.IOException;

public class CharReadDemo {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("char_output.txt");

            int character;
            // read() returns -1 when end of file
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

### Writing with BufferedWriter (Efficient ✅):
```java
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class BufferedWriteDemo {
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("buffered_output.txt"));

            bw.write("Line 1: BufferedWriter is efficient.");
            bw.newLine();   // Platform-independent line break
            bw.write("Line 2: Writes data in chunks.");
            bw.newLine();
            bw.write("Line 3: Always close the stream!");

            bw.close();
            System.out.println("Written using BufferedWriter.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Reading with BufferedReader (Efficient ✅):
```java
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BufferedReadDemo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("buffered_output.txt"));

            String line;
            int lineNum = 1;

            // readLine() returns null at end of file
            while ((line = br.readLine()) != null) {
                System.out.println(lineNum + ": " + line);
                lineNum++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Writing with PrintWriter:
```java
import java.io.PrintWriter;
import java.io.IOException;

public class PrintWriterDemo {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter("students.txt");

            pw.println("Name: Ram Sharma");
            pw.println("Age : 20");
            pw.printf("GPA : %.2f%n", 3.85);
            pw.close();

            System.out.println("Written using PrintWriter.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Appending to a File:
```java
// Pass 'true' as second argument to FileWriter for append mode
FileWriter fw = new FileWriter("char_output.txt", true);  // ← APPEND

fw.write("This line is appended!\n");
fw.close();
```

### FileWriter Modes:

| Constructor | Mode | Behavior |
|-------------|------|----------|
| `new FileWriter("file.txt")` | **Overwrite** | Erases existing content |
| `new FileWriter("file.txt", true)` | **Append** | Adds to the end |

---

### 📊 FileReader vs BufferedReader:

| Feature | `FileReader` | `BufferedReader` |
|---------|-------------|-----------------|
| Reads | One character at a time | One line at a time (efficient) |
| Method | `read()` → returns `int` | `readLine()` → returns `String` |
| Speed | Slower ❌ | Faster ✅ (uses buffer) |
| EOF | Returns `-1` | Returns `null` |
| Best for | Single characters | Line-by-line reading |

---

## 5.7 Random Access Files

### What is Random Access?

Normal streams read/write **sequentially** (from beginning to end). **Random Access** lets you **jump to any position** in the file and read/write from there.

```
Sequential Access (FileReader, FileWriter):
  ──────────────────────────►
  Start                    End
  (Must read A, B, C to reach D)

Random Access (RandomAccessFile):
  ┌───┬───┬───┬───┬───┬───┐
  │ A │ B │ C │ D │ E │ F │
  └───┴───┴───┴─▲─┴───┴───┘
                │
          Jump directly to D!
```

### The RandomAccessFile Class

```java
import java.io.RandomAccessFile;
```

### Constructor:

```java
RandomAccessFile raf = new RandomAccessFile("data.txt", mode);
```

| Mode | Meaning |
|------|---------|
| `"r"` | **Read only** — can only read from the file |
| `"rw"` | **Read and Write** — can both read and write |

---

### Key Methods:

| Method | Description |
|--------|-------------|
| `seek(long pos)` | Moves the file pointer to position `pos` (in bytes) |
| `getFilePointer()` | Returns the current position of the file pointer |
| `length()` | Returns the file size in bytes |
| `read()` | Reads one byte |
| `readLine()` | Reads a line of text |
| `readInt()` | Reads 4 bytes as an `int` |
| `readDouble()` | Reads 8 bytes as a `double` |
| `readUTF()` | Reads a UTF-8 encoded string |
| `write(int b)` | Writes one byte |
| `writeInt(int v)` | Writes an `int` (4 bytes) |
| `writeDouble(double v)` | Writes a `double` (8 bytes) |
| `writeUTF(String s)` | Writes a UTF-8 encoded string |
| `close()` | Closes the file |

---

### Example: Writing and Reading at Random Positions

```java
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessDemo {
    public static void main(String[] args) {
        try {
            // ===== WRITING =====
            RandomAccessFile raf = new RandomAccessFile("random_data.dat", "rw");

            // Write data at sequential positions
            raf.writeUTF("Ram");        // Position 0
            raf.writeInt(20);           // After the string
            raf.writeDouble(3.85);      // After the int

            long secondRecordPos = raf.getFilePointer();  // Save position

            raf.writeUTF("Sita");
            raf.writeInt(22);
            raf.writeDouble(3.90);

            System.out.println("Data written successfully.");
            System.out.println("File size: " + raf.length() + " bytes");

            // ===== READING FROM BEGINNING =====
            raf.seek(0);   // Go back to the start
            System.out.println("\n--- Reading Record 1 ---");
            System.out.println("Name: " + raf.readUTF());
            System.out.println("Age : " + raf.readInt());
            System.out.println("GPA : " + raf.readDouble());

            // ===== JUMP TO SECOND RECORD =====
            raf.seek(secondRecordPos);   // Jump directly!
            System.out.println("\n--- Reading Record 2 (Random Access) ---");
            System.out.println("Name: " + raf.readUTF());
            System.out.println("Age : " + raf.readInt());
            System.out.println("GPA : " + raf.readDouble());

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

**Output:**
```
Data written successfully.
File size: 38 bytes

--- Reading Record 1 ---
Name: Ram
Age : 20
GPA : 3.85

--- Reading Record 2 (Random Access) ---
Name: Sita
Age : 22
GPA : 3.9
```

---

### Example: Modifying Data in the Middle of a File

```java
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessModifyDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("numbers.dat", "rw");

            // Write 5 integers
            for (int i = 1; i <= 5; i++) {
                raf.writeInt(i * 10);   // 10, 20, 30, 40, 50
            }
            System.out.println("Written: 10, 20, 30, 40, 50");

            // Modify the 3rd number (index 2 × 4 bytes = position 8)
            raf.seek(8);          // Jump to position of 3rd integer
            raf.writeInt(999);    // Replace 30 with 999

            // Read all numbers back
            raf.seek(0);
            System.out.print("After modification: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();   // Output: 10 20 999 40 50

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

**Output:**
```
Written: 10, 20, 30, 40, 50
After modification: 10 20 999 40 50
```

> 💡 **How seek() position works for integers:**  
> Each `int` is 4 bytes. So: 1st int = position `0`, 2nd = `4`, 3rd = `8`, 4th = `12`, etc.

### Sequential vs Random Access:

| Feature | Sequential Access | Random Access |
|---------|------------------|---------------|
| Reading order | Start to end only | Any position |
| Modify existing data | ❌ Must rewrite entire file | ✅ Jump and overwrite |
| Classes | `FileReader`, `FileWriter`, `FileInputStream` | `RandomAccessFile` |
| Performance | Faster for full file reads | Faster for specific positions |
| Use case | Reading logs, text files | Database-like operations |

---

## Best Practices

### ✅ Do:
1. **Always close** streams after use (or use try-with-resources)
2. **Use BufferedReader/Writer** for better performance with text files
3. **Check if file exists** before reading (`file.exists()`)
4. **Handle exceptions** properly — don't leave empty catch blocks
5. **Use Character Streams** for text, **Byte Streams** for binary data
6. **Flush the buffer** before closing for critical data (`writer.flush()`)

### ❌ Don't:
1. Don't forget to **close** the stream — causes resource leaks!
2. Don't use **Byte Streams** for text files — use Character Streams
3. Don't ignore **IOException** — always handle or propagate
4. Don't use `FileWriter` without `true` flag if you want to **append**

---

## Code Examples Summary

| File | Topic | Key Concepts |
|------|-------|-------------|
| `ConsoleAndFileIO.java` | 5.1 Console & File I/O | `System.in`, `System.out`, basic file create |
| `FileOperationsDemo.java` | 5.2 Opening & Closing | `File` class, create, delete, exists, try-with-resources |
| `ScannerDemo.java` | 5.3 Scanner Class | Scanner for keyboard & file, `nextLine()`, `nextInt()` |
| `ByteStreamDemo.java` | 5.5 Byte Streams | `FileInputStream`, `FileOutputStream`, file copy |
| `CharacterStreamDemo.java` | 5.6 Character Streams | `FileReader/Writer`, `BufferedReader/Writer`, `PrintWriter` |
| `RandomAccessDemo.java` | 5.7 Random Access | `RandomAccessFile`, `seek()`, `readInt()`, `writeInt()` |

---

## 🎯 Quick Reference Card

```
┌──────────────────────────────────────────────────────────────────┐
│                    I/O CHEAT SHEET                                │
├──────────────────────────────────────────────────────────────────┤
│                                                                  │
│  CONSOLE I/O:                                                    │
│    System.out.println("Hello");    // Output to screen           │
│    Scanner sc = new Scanner(System.in);  // Input from keyboard  │
│                                                                  │
│  FILE CREATE/DELETE:                                             │
│    File f = new File("data.txt");                                │
│    f.createNewFile();   f.delete();   f.exists();                │
│                                                                  │
│  BYTE STREAMS (Binary):                                          │
│    FileInputStream fis = new FileInputStream("img.jpg");         │
│    FileOutputStream fos = new FileOutputStream("img.jpg");       │
│                                                                  │
│  CHARACTER STREAMS (Text):                                       │
│    FileWriter fw = new FileWriter("f.txt");       // write       │
│    FileWriter fw = new FileWriter("f.txt", true); // append      │
│    BufferedReader br = new BufferedReader(new FileReader("f"));   │
│    while((line = br.readLine()) != null) { ... }                 │
│                                                                  │
│  SCANNER (File):                                                 │
│    Scanner sc = new Scanner(new File("data.txt"));               │
│    while(sc.hasNextLine()) { sc.nextLine(); }                    │
│                                                                  │
│  RANDOM ACCESS:                                                  │
│    RandomAccessFile raf = new RandomAccessFile("f.dat", "rw");   │
│    raf.seek(position);    // Jump to any position                │
│    raf.readInt();  raf.writeInt(value);                           │
│                                                                  │
│  ALWAYS CLOSE STREAMS:                                           │
│    try (FileWriter fw = new FileWriter("f.txt")) {               │
│        fw.write("auto-close!");                                  │
│    }                                                             │
│                                                                  │
└──────────────────────────────────────────────────────────────────┘
```

---

> 📝 **Unit 5: Using I/O — Chapter 5**
>
> Code Files: `ConsoleAndFileIO.java`, `FileOperationsDemo.java`, `ScannerDemo.java`, `ByteStreamDemo.java`, `CharacterStreamDemo.java`, `RandomAccessDemo.java`

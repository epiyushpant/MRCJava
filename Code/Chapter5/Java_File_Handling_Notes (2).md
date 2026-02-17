# 📂 File Handling in Java

## 1. Introduction

File Handling in Java is used to: - Create files - Read data from
files - Write data to files - Append data to files - Delete files

Java file handling classes are available in the `java.io` package.

------------------------------------------------------------------------

# 2. Creating and Deleting Files

## 2.1 Creating a File (File Class)

### Definition

The `File` class is used to create, check, and delete files.

### Example: Create a File

``` java
import java.io.File;
import java.io.IOException;

public class CreateFileExample {
    public static void main(String[] args) {
        try {
            File file = new File("sample.txt");

            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
```

### Important Points

-   `createNewFile()` returns true if file is created.
-   Returns false if file already exists.
-   Must handle IOException.

------------------------------------------------------------------------

## 2.2 Deleting a File

### Example: Delete File

``` java
import java.io.File;

public class DeleteFileExample {
    public static void main(String[] args) {
        File file = new File("sample.txt");

        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete file.");
        }
    }
}
```

### Important Points

-   `delete()` returns true if deletion is successful.
-   Returns false if file does not exist.

------------------------------------------------------------------------

# 3. Reader Classes in Java

Reader classes are used to read **character (text) data**.

## 3.1 FileReader

### Definition

`FileReader` is used to read text files character by character.

### Example

``` java
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("sample.txt");
            int ch;

            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
```

------------------------------------------------------------------------

## 3.2 BufferedReader

### Definition

`BufferedReader` reads text efficiently using buffer memory.

### Example

``` java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
```

------------------------------------------------------------------------

## 3.3 Scanner

### Definition

`Scanner` is used to read data from files, keyboard, or strings.

### Example

``` java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileExample {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("sample.txt"));

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
```

------------------------------------------------------------------------

# 4. Writer Classes in Java

Writer classes are used to write **character (text) data**.

## 4.1 FileWriter

### Writing (Overwrite Mode)

``` java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("sample.txt");
            fw.write("Hello Students");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}
```

### Appending Mode

``` java
FileWriter fw = new FileWriter("sample.txt", true);
fw.write("\nNew Line Added");
fw.close();
```

------------------------------------------------------------------------

## 4.2 BufferedWriter

``` java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sample.txt", true));
            bw.write("Buffered Append Example");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
```

------------------------------------------------------------------------

## 4.3 PrintWriter

``` java
import java.io.PrintWriter;
import java.io.IOException;

public class PrintWriterExample {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter("sample.txt");
            pw.println("Name: Ram");
            pw.println("Age: 20");
            pw.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
```

------------------------------------------------------------------------

# 5. Best Practice (Try-With-Resources)

``` java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BestPracticeExample {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sample.txt", true))) {
            bw.write("Best Practice Example");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

------------------------------------------------------------------------

# 6. Important Exam Points

-   File class is used to create and delete files.
-   createNewFile() creates a new file.
-   delete() removes the file.
-   FileWriter overwrites by default.
-   Use true parameter to append.
-   BufferedWriter is faster.
-   PrintWriter supports formatted output.
-   Always close the file.
-   Use try-with-resources for best practice.

# 🎓 Student Management System (JDBC + PostgreSQL)

A robust, console-based CRUD (Create, Read, Update, Delete) application built using **Java** and **PostgreSQL**. This project demonstrates the implementation of the **Data Access Object (DAO)** design pattern to manage database operations efficiently.

---

## 🚀 Features

-   **Create**: Add new student records to the database.
-   **Read**: Fetch and display all registered students.
-   **Update**: Modify existing student information using their unique ID.
-   **Delete**: Remove student records from the database.
-   **Data Integrity**: Uses PostgreSQL constraints and `SERIAL` keys for reliable data management.

---

## 🛠️ Project Structure

-   `src/Student.java`: The Model class representing a Student entity.
-   `src/StudentDAO.java`: The Data Access Object handling SQL operations.
-   `src/DatabaseConnection.java`: Singleton-style connection manager.
-   `src/Main.java`: The entry point with an interactive CLI menu.
-   `DatabaseSetup.sql`: SQL script to initialize the schema and sample data.

---

## ⚙️ Setup & Configuration

### 1. Database Initialization
1.  Open your PostgreSQL tool (pgAdmin or psql).
2.  Create a database named `MRCJava`.
3.  Execute the queries provided in `DatabaseSetup.sql` to create the `students` table and insert sample data.

### 2. Configure Credentials
Update the connection details in `src/DatabaseConnection.java`:
```java
private static final String URL = "jdbc:postgresql://localhost:5432/MRCJava";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 3. Add JDBC Driver
To connect Java to PostgreSQL, you need the **JDBC Driver jar file**.
-   **Download**: [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/)
-   **Placement**: Place the `.jar` file in the `lib/` folder of this project.

---

## 💻 How to Run

### Using Command Line
**Compile:**
```bash
javac -d out -cp "lib/*" src/*.java
```

**Run:**
```bash
java -cp "out;lib/*" Main
```
*(Note: Use `:` instead of `;` as a separator on macOS or Linux.)*

---

## 🌟 Best Note: Connection Success!

> **💡 Pro Tip for JDBC Connections:**
> Always use **Try-with-Resources** (available since Java 7) when working with `Connection`, `Statement`, and `ResultSet`. This ensures that database resources are closed automatically, preventing memory leaks and "Too many connections" errors. 
> 
> **Why it works now?**
> Successful connection depends on three pillars:
> 1. **Driver availability**: The `.jar` must be in the classpath.
> 2. **Correct URL**: Protocol (`jdbc:postgresql`), Host, Port, and Database Name must match.
> 3. **Active Service**: The PostgreSQL server must be running and accepting connections.

---

## 📝 License
This project is for educational purposes at MRC Teaching.

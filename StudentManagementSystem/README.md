# Student Management System

A Java JDBC + MySQL mini project for a training institute.

## Modules
- Student Registration
- Course Management
- Course Enrollment
- Attendance Management
- Marks Entry
- Result Generation

## JDBC Concepts Demonstrated
- CRUD operations
- PreparedStatement
- JOIN queries
- Batch operations
- Transactions
- commit()
- rollback()
- InnoDB tables

## Technologies
- Java 17+
- JDBC
- MySQL
- Maven
- IntelliJ IDEA

## Database Setup

1. Open MySQL Workbench.
2. Open `database/student_management.sql`.
3. Run the complete script.
4. The script creates the `student_management_db` database and sample data.

## Configure MySQL Password

Open:

`src/main/java/com/studentmanagement/DBConnection.java`

Change:

```java
private static final String PASSWORD = "root";
```

to your MySQL password.

## Run

Open the project in IntelliJ IDEA as a Maven project.

Run:

`Main.java`

## Transaction Demonstration

The enrollment operation uses a transaction. If the enrollment insert or related operation fails, `rollback()` is executed.

All tables use InnoDB, so transaction rollback is supported.

## GitHub Upload

From the project folder:

```bash
git init
git add .
git commit -m "Initial commit - Student Management System"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/StudentManagementSystem.git
git push -u origin main
```

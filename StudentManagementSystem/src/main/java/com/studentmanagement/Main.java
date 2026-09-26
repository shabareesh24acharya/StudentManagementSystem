package com.studentmanagement;

import com.studentmanagement.dao.AttendanceDAO;
import com.studentmanagement.dao.CourseDAO;
import com.studentmanagement.dao.EnrollmentDAO;
import com.studentmanagement.dao.MarkDAO;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.ResultService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final StudentDAO studentDAO = new StudentDAO();
    private static final CourseDAO courseDAO = new CourseDAO();
    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private static final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private static final MarkDAO markDAO = new MarkDAO();
    private static final ResultService resultService = new ResultService();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> addCourse();
                case 6 -> courseDAO.viewCourses();
                case 7 -> enrollStudent();
                case 8 -> enrollmentDAO.viewEnrollments();
                case 9 -> markAttendance();
                case 10 -> viewAttendance();
                case 11 -> enterMarks();
                case 12 -> viewMarks();
                case 13 -> generateResult();
                case 14 -> {
                    System.out.println("Thank you for using Student Management System.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""

                ========================================
                   STUDENT MANAGEMENT SYSTEM
                ========================================
                1.  Student Registration
                2.  View Students
                3.  Update Student
                4.  Delete Student
                5.  Add Course
                6.  View Courses
                7.  Enroll Student
                8.  View Enrollments
                9.  Mark Attendance
                10. View Attendance
                11. Enter Marks
                12. View Marks
                13. Generate Result
                14. Exit
                ========================================
                """);
    }

    private static void registerStudent() {
        String name = readString("Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone: ");
        studentDAO.addStudent(new Student(name, email, phone));
    }

    private static void viewStudents() {
        System.out.println("\nID | Name | Email | Phone");
        for (Student student : studentDAO.getAllStudents()) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        int id = readInt("Student ID: ");
        String name = readString("New name: ");
        String email = readString("New email: ");
        String phone = readString("New phone: ");
        studentDAO.updateStudent(id, name, email, phone);
    }

    private static void deleteStudent() {
        int id = readInt("Student ID: ");
        studentDAO.deleteStudent(id);
    }

    private static void addCourse() {
        String name = readString("Course name: ");
        int duration = readInt("Duration in months: ");
        double fee = readDouble("Fee: ");
        courseDAO.addCourse(name, duration, fee);
    }

    private static void enrollStudent() {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        enrollmentDAO.enrollStudent(studentId, courseId);
    }

    private static void markAttendance() {
        int enrollmentId = readInt("Enrollment ID: ");
        String date = readString("Date (YYYY-MM-DD): ");
        String status = readString("Status (PRESENT/ABSENT): ");
        attendanceDAO.markAttendance(enrollmentId, Date.valueOf(date), status);
    }

    private static void viewAttendance() {
        int enrollmentId = readInt("Enrollment ID: ");
        attendanceDAO.viewAttendance(enrollmentId);
    }

    private static void enterMarks() {
        int enrollmentId = readInt("Enrollment ID: ");
        String subject = readString("Subject: ");
        int marks = readInt("Marks: ");
        markDAO.addMark(enrollmentId, subject, marks);
    }

    private static void viewMarks() {
        int enrollmentId = readInt("Enrollment ID: ");
        markDAO.viewMarks(enrollmentId);
    }

    private static void generateResult() {
        int enrollmentId = readInt("Enrollment ID: ");
        resultService.generateResult(enrollmentId);
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount.");
            }
        }
    }

    private static String readString(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
}

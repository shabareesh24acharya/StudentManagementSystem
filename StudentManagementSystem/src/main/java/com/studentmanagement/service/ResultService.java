package com.studentmanagement.service;

import com.studentmanagement.DBConnection;

import java.sql.*;

public class ResultService {

    public void generateResult(int enrollmentId) {
        String sql = """
                SELECT s.name, c.course_name, m.subject, m.marks
                FROM marks m
                JOIN enrollments e ON m.enrollment_id = e.enrollment_id
                JOIN students s ON e.student_id = s.student_id
                JOIN courses c ON e.course_id = c.course_id
                WHERE m.enrollment_id=?
                ORDER BY m.subject
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            try (ResultSet rs = ps.executeQuery()) {
                int total = 0;
                int count = 0;
                String student = "";
                String course = "";

                System.out.println("\n========== STUDENT RESULT ==========");

                while (rs.next()) {
                    student = rs.getString("name");
                    course = rs.getString("course_name");
                    String subject = rs.getString("subject");
                    int marks = rs.getInt("marks");

                    System.out.printf("%-20s : %d%n", subject, marks);
                    total += marks;
                    count++;
                }

                if (count == 0) {
                    System.out.println("No marks found.");
                    return;
                }

                double average = (double) total / count;
                String result = average >= 40 ? "PASS" : "FAIL";

                System.out.println("------------------------------------");
                System.out.println("Student : " + student);
                System.out.println("Course  : " + course);
                System.out.println("Total   : " + total);
                System.out.printf("Average : %.2f%n", average);
                System.out.println("Result  : " + result);
                System.out.println("====================================");
            }

        } catch (SQLException e) {
            System.out.println("Result generation failed: " + e.getMessage());
        }
    }
}

package com.studentmanagement.dao;

import com.studentmanagement.DBConnection;

import java.sql.*;

public class EnrollmentDAO {

    public void enrollStudent(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments(student_id,course_id) VALUES(?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

            con.commit();
            System.out.println("Enrollment committed successfully.");

        } catch (SQLException e) {
            System.out.println("Enrollment failed. Transaction rolled back.");
        }
    }

    public void viewEnrollments() {
        String sql = """
                SELECT e.enrollment_id, s.name AS student_name,
                       c.course_name, e.enrollment_date, e.status
                FROM enrollments e
                JOIN students s ON e.student_id = s.student_id
                JOIN courses c ON e.course_id = c.course_id
                ORDER BY e.enrollment_id
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nEnrollment ID | Student | Course | Date | Status");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s | %s%n",
                        rs.getInt("enrollment_id"),
                        rs.getString("student_name"),
                        rs.getString("course_name"),
                        rs.getDate("enrollment_date"),
                        rs.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("Could not fetch enrollments: " + e.getMessage());
        }
    }
}

package com.studentmanagement.dao;

import com.studentmanagement.DBConnection;

import java.sql.*;

public class MarkDAO {

    public void addMark(int enrollmentId, String subject, int marks) {
        String sql = "INSERT INTO marks(enrollment_id,subject,marks) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);
            ps.setString(2, subject);
            ps.setInt(3, marks);
            ps.executeUpdate();

            System.out.println("Marks entered successfully.");

        } catch (SQLException e) {
            System.out.println("Marks entry failed: " + e.getMessage());
        }
    }

    public void viewMarks(int enrollmentId) {
        String sql = "SELECT subject,marks FROM marks WHERE enrollment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\nSubject | Marks");
                while (rs.next()) {
                    System.out.println(rs.getString("subject") + " | " + rs.getInt("marks"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Could not fetch marks: " + e.getMessage());
        }
    }
}

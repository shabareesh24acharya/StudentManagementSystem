package com.studentmanagement.dao;

import com.studentmanagement.DBConnection;

import java.sql.*;

public class AttendanceDAO {

    public void markAttendance(int enrollmentId, Date date, String status) {
        String sql = "INSERT INTO attendance(enrollment_id,attendance_date,status) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);
            ps.setDate(2, date);
            ps.setString(3, status.toUpperCase());
            ps.executeUpdate();

            System.out.println("Attendance marked successfully.");

        } catch (SQLException e) {
            System.out.println("Attendance failed: " + e.getMessage());
        }
    }

    public void viewAttendance(int enrollmentId) {
        String sql = """
                SELECT attendance_date,status
                FROM attendance
                WHERE enrollment_id=?
                ORDER BY attendance_date
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            try (ResultSet rs = ps.executeQuery()) {
                int present = 0;
                int total = 0;

                System.out.println("\nDate | Status");
                while (rs.next()) {
                    total++;
                    String status = rs.getString("status");
                    if ("PRESENT".equals(status)) present++;
                    System.out.println(rs.getDate("attendance_date") + " | " + status);
                }

                double percentage = total == 0 ? 0 : (present * 100.0 / total);
                System.out.printf("Attendance: %.2f%%%n", percentage);
            }

        } catch (SQLException e) {
            System.out.println("Could not fetch attendance: " + e.getMessage());
        }
    }
}

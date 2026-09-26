package com.studentmanagement.dao;

import com.studentmanagement.DBConnection;

import java.sql.*;

public class CourseDAO {

    public void addCourse(String name, int duration, double fee) {
        String sql = "INSERT INTO courses(course_name,duration_months,fee) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, duration);
            ps.setDouble(3, fee);
            ps.executeUpdate();
            System.out.println("Course added successfully.");

        } catch (SQLException e) {
            System.out.println("Course creation failed: " + e.getMessage());
        }
    }

    public void viewCourses() {
        String sql = "SELECT course_id,course_name,duration_months,fee FROM courses ORDER BY course_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nID | Course | Duration | Fee");
            while (rs.next()) {
                System.out.printf("%d | %s | %d months | %.2f%n",
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("duration_months"),
                        rs.getDouble("fee"));
            }

        } catch (SQLException e) {
            System.out.println("Could not fetch courses: " + e.getMessage());
        }
    }
}

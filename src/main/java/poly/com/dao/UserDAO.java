package com.poly.dao;

import com.poly.context.DBContext; // Import lớp DBContext
import com.poly.model.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    // Đã XÓA các hằng số URL, USER, PASSWORD và phương thức getConnection() riêng
    // Giờ đây DAO sẽ phụ thuộc hoàn toàn vào DBContext

    // CREATE
    public void insert(User u) throws SQLException {
        String sql = "INSERT INTO Users(Id, Password, Fullname, Email, Admin) VALUES (?, ?, ?, ?, ?)";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getId());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getEmail());
            ps.setBoolean(5, "Admin".equalsIgnoreCase(u.getRole()));
            ps.executeUpdate();
        }
    }

    // READ - lấy toàn bộ user
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String role = rs.getBoolean("Admin") ? "Admin" : "User";
                list.add(new User(
                        rs.getString("Id"),
                        rs.getString("Password"),
                        rs.getString("Fullname"),
                        rs.getString("Email"),
                        role
                ));
            }
        }
        return list;
    }

    // UPDATE
    public void update(User u) throws SQLException {
        String sql = "UPDATE Users SET Password=?, Fullname=?, Email=?, Admin=? WHERE Id=?";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getFullname());
            ps.setString(3, u.getEmail());
            ps.setBoolean(4, "Admin".equalsIgnoreCase(u.getRole()));
            ps.setString(5, u.getId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Users WHERE Id=?";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    // TÌM USER THEO ID (dùng cho Edit)
    public User findById(String id) throws SQLException {
        String sql = "SELECT * FROM Users WHERE Id=?";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getBoolean("Admin") ? "Admin" : "User";
                return new User(
                        rs.getString("Id"),
                        rs.getString("Password"),
                        rs.getString("Fullname"),
                        rs.getString("Email"),
                        role
                );
            }
        }
        return null;
    }

    // SEARCH theo fullname
    public List<User> searchByName(String name) throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Fullname LIKE ?";
        // Sửa: Gọi DBContext.getConnection()
        try (Connection c = DBContext.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String role = rs.getBoolean("Admin") ? "Admin" : "User";
                list.add(new User(
                        rs.getString("Id"),
                        rs.getString("Password"),
                        rs.getString("Fullname"),
                        rs.getString("Email"),
                        role
                ));
            }
        }
        return list;
    }
}
package poly.com.model;

public class User {
    private String id;
    private String password;
    private String fullname;
    private String email;
    private String role; // "Admin" hoặc "User"

    public User() {}

    public User(String id, String password, String fullname, String email, String role) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Phương thức tiện ích (nếu cần dùng Boolean ở DAO)
    public boolean isAdmin() {
        return "Admin".equalsIgnoreCase(role);
    }
}

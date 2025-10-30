<%@ page isELIgnored="false" %>
<%@ page import="java.util.*, com.poly.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management - CRUD</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<div class="crud-container">
    <h2>User Management - CRUD</h2>
    <p>Fill in the data below.</p>

    <form action="user" method="post">
        <input type="text" name="userID" placeholder="UserID" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="fullname" placeholder="Full-name" required>
        <input type="email" name="email" placeholder="E-mail Address" required>

        <div class="role-group">
            Role:
            <label><input type="radio" name="role" value="Admin"> Admin</label>
            <label><input type="radio" name="role" value="User"> User</label>
        </div>

        <div class="button-group">
            <button type="submit" name="action" value="create" class="btn create">Create</button>
            <button type="submit" name="action" value="update" class="btn update">Update</button>
            <button type="submit" name="action" value="delete" class="btn delete">Delete</button>
            <button type="reset" class="btn reset">Reset</button>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>No</th>
            <th>User ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Role</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<User> list = (List<User>) request.getAttribute("userList");
            if (list != null) {
                int i = 1;
                for (User u : list) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= u.getId() %></td>
            <td><%= u.getPassword() %></td>
            <td><%= u.getFullname() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td>
                <a href="user?action=edit&userID=<%= u.getId() %>">Edit</a> |
                <a href="user?action=remove&userID=<%= u.getId() %>">Remove</a>
            </td>
        </tr>
        <%
                } // đóng for
            } else {
        %>
        <tr><td colspan="7">No users found.</td></tr>
        <%
            } // đóng if
        %>
        </tbody>
    </table>
</div>
</body>
</html>

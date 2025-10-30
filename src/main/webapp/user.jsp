<%@ page isELIgnored="false" %>
<%@ page import="java.util.*, poly.com.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management - CRUD</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<div style="width: 800px; display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
    <h3 style="color: white; margin: 0;">
        Welcome, <span style="color: #0dcaf0;">${sessionScope.user.fullname}</span>!
    </h3>
    <a href="logout" class="btn delete" style="text-decoration: none; margin: 0;">Logout</a>
</div>
<div class="crud-container">
    <h2>User Management - CRUD</h2>
    <p>Fill in the data below.</p>

    <form action="user" method="post">
            <input type="text" name="userID" placeholder="UserID" required
                   value="${editUser.id}" ${not empty editUser ? 'readonly' : ''}>

            <input type="password" name="password" placeholder="Password" required
                   value="${editUser.password}">

            <input type="text" name="fullname" placeholder="Full-name" required
                   value="${editUser.fullname}">

            <input type="email" name="email" placeholder="E-mail Address" required
                   value="${editUser.email}">

            <div class="role-group">
                Role:
                <label>
                    <input type="radio" name="role" value="Admin"
                           ${editUser.role == 'Admin' ? 'checked' : ''}> Admin
                </label>
                <label>
                    <input type="radio" name="role" value="User"
                           ${editUser.role == 'User' or empty editUser ? 'checked' : ''}> User
                </label>
            </div>

            <div class="button-group">
                <button type="submit" name="action" value="create" class="btn create">Create</button>
                <button type="submit" name="action" value="update" class="btn update">Update</button>
                <button type="submit" name="action" value="delete" class="btn delete">Delete</button>
                <button type="reset" class="btn reset">Reset</button>
            </div>
        </form>

        <hr style="border-color: #444; margin: 25px 0;">

            <form action="user" method="get" style="display: flex; gap: 10px; margin-bottom: 20px;">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="Nhập Fullname hoặc Email để tìm..."
                       value="${keyword}" style="flex-grow: 1; margin: 0;">
                <button type="submit" class="btn create" style="margin: 0;">Tìm kiếm</button>
                <a href="user" class="btn reset" style="margin: 0; text-decoration: none;">Reset</a>
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

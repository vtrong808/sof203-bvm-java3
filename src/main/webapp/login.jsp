<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="assets/style.css">
    <style>
        /* CSS riêng cho trang login */
        body {
            align-items: center;
        }
        .crud-container {
            width: 400px; /* Thu nhỏ form */
        }
        .btn-login {
            width: 100%;
            background-color: #007bff;
        }
        .error {
            color: #ffc107;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="crud-container">
    <h2 style="margin-bottom: 20px;">Login</h2>

    <form action="login" method="post">
        <input type="text" name="userID" placeholder="UserID" required>
        <input type="password" name="password" placeholder="Password" required>

        <div class="button-group" style="margin-top: 20px;">
            <button type="submit" class="btn btn-login">Login</button>
        </div>

        <p class="error">${errorMessage}</p>
    </form>
</div>
</body>
</html>
package poly.com.controller;

import poly.com.dao.UserDAO;
import poly.com.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao;

    @Override
    public void init() {
        dao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "edit":
                    // Truyền thông tin user đang chọn vào form
                    String editID = req.getParameter("userID");
                    User editUser = dao.findById(editID);
                    req.setAttribute("editUser", editUser);

                    // ✅ THÊM DÒNG NÀY:
                    // Dù là edit, vẫn phải tải lại list để hiển thị table
                    req.setAttribute("userList", dao.findAll());

                    break; // Giữ break cũng được vì đã thêm dòng trên

                case "remove":
                    String removeID = req.getParameter("userID");
                    dao.delete(removeID);
                    // Sau khi xóa, tải lại list
                    req.setAttribute("userList", dao.findAll());
                    break;

                case "search":
                    String keyword = req.getParameter("keyword");
                    // ✅ Sửa tên phương thức được gọi
                    req.setAttribute("userList", dao.searchByKeyword(keyword));
                    // ✅ Giữ lại keyword trên ô tìm kiếm
                    req.setAttribute("keyword", keyword);
                    break;

                default: // list
                    req.setAttribute("userList", dao.findAll());
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chuyển tới giao diện CRUD
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "";

        String id = req.getParameter("userID");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        User user = new User(id, password, fullname, email, role);

        try {
            switch (action) {
                case "create":
                    dao.insert(user);
                    break;
                case "update":
                    dao.update(user);
                    break;
                case "delete":
                    dao.delete(id);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("user");
    }
}

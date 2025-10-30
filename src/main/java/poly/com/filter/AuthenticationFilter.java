package poly.com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Lọc TẤT CẢ các request
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Lấy URI của request (VD: /login, /user, /assets/style.css)
        String uri = req.getRequestURI();

        // Lấy session
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginRequest = uri.endsWith("login");
        boolean isAssetRequest = uri.contains("/assets/"); // Cho phép truy cập CSS/JS

        if (isLoggedIn || isLoginRequest || isAssetRequest) {
            // (1) Đã login, (2) Đang đi đến trang login, (3) Đang lấy file CSS
            // --> Cho phép đi tiếp
            chain.doFilter(request, response);
        } else {
            // Chưa login MÀ đòi vào các trang khác
            // --> Đẩy về trang login
            resp.sendRedirect("login");
        }
    }
}
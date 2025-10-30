package poly.com.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    // ✅ Thông tin cấu hình SQL Server
    private static final String SERVER_NAME = "LAPTOP-7RD7637C";

    // Sửa tên Database để khớp với file BVM_User_Java3.sql
    private static final String DATABASE_NAME = "BVM_User_Java3";

    private static final String USER = "sa";
    private static final String PASSWORD = "A1234b1234@";
    private static final String PORT = "1433";

    public static Connection getConnection() throws SQLException {
        try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Tạo URL kết nối
            String url = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT + ";databaseName=" + DATABASE_NAME
                    + ";encrypt=false;trustServerCertificate=true";

            // Trả về kết nối
            return DriverManager.getConnection(url, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy Driver SQL Server!", e);
        }
    }
}
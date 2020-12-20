package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/my_db?useUnicode=true&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String pass = "root";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

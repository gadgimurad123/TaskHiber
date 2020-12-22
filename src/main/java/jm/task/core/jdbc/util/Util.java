package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/new_schema123?useUnicode=true&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String pass = "root";

    private static final SessionFactory factory;

    // Work with JDBC
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

    // Work with Hibernate
    static {
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "root");

        prop.setProperty("current_session_context_class", "thread");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("show_sql", "true");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(prop)
                .buildServiceRegistry();

        factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
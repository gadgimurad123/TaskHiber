package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/new_schema123?useUnicode=true&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String pass = "root";

    private static final SessionFactory factory;
    private static final StandardServiceRegistry standardServiceRegistry;

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

    static {
        // Creating StandardServiceRegistryBuilder
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings which is equivalent to hibernate. cfg. xml's properties
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
        dbSettings.put(Environment.USER, "root");
        dbSettings.put(Environment.PASS, "root");
        dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        dbSettings.put(Environment.SHOW_SQL, "true");
        dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        // Apply database settings
        registryBuilder.applySettings(dbSettings);

        // Creating registry
        standardServiceRegistry = registryBuilder.build();

        // Creating MetadataSources
        MetadataSources sources = new MetadataSources(standardServiceRegistry);

        // Creating Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Creating SessionFactory
        factory = metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

//    public static SessionFactory getSessionFactory() throws ClassNotFoundException, SQLException {
//        // TODO: change to "without XML"
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();
//        return sessionFactory;
//    }


}

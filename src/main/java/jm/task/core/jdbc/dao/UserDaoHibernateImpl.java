package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl instance;

    public static UserDaoHibernateImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE USERS (" +
                    "ID        BIGINT       NOT NULL AUTO_INCREMENT," +
                    "NAME      VARCHAR (20) NOT NULL," +
                    "LAST_NAME VARCHAR (20) NOT NULL," +
                    "AGE       TINYINT      NOT NULL," +
                    "PRIMARY KEY ( ID )" +
                    ");").executeUpdate();

            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE USERS").executeUpdate();

            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            User user = new User(name, lastName, age);
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            User user = (User) session.get(User.class, id);

            session.delete(user);
            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            users = session.createQuery("from User").list();

            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        List<User> users;
        SessionFactory factory = null;
        try {
            factory = Util.getHibernateConnection();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            users = session.createQuery("from User").list();
            for (User user : users)
                session.delete(user);

            session.getTransaction().commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl instance;
    private static SessionFactory factory;

    public static UserDaoHibernateImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }

    public UserDaoHibernateImpl() {
    }

    static {
        try {
            factory = Util.getSessionFactory();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            Session session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE USERS (" +
                    "ID        BIGINT       NOT NULL AUTO_INCREMENT," +
                    "NAME      VARCHAR (20) NOT NULL," +
                    "LAST_NAME VARCHAR (20) NOT NULL," +
                    "AGE       TINYINT      NOT NULL," +
                    "PRIMARY KEY ( ID )" +
                    ");").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try {
            Session session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("DROP TABLE USERS").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            Session session = factory.getCurrentSession();
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    public static void saveUser1(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            Session session = factory.openSession();
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            Session session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            User user = (User) session.get(User.class, id);

            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try {
            Session session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            users = session.createQuery("from User").list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        List<User> users;
        try {
            Session session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            users = session.createQuery("from User").list();
            for (User user : users)
                session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

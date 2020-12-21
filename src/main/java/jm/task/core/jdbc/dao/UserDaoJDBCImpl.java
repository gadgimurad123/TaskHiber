package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static UserDaoJDBCImpl instance;
    private static Connection connection = null;

    static {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private UserDaoJDBCImpl() {
    }

    public static UserDaoJDBCImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBCImpl();
        }
        return instance;
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE USERS (" +
                         "ID        BIGINT       NOT NULL AUTO_INCREMENT," +
                         "NAME      VARCHAR (20) NOT NULL," +
                         "LAST_NAME VARCHAR (20) NOT NULL," +
                         "AGE       TINYINT      NOT NULL," +
                         "PRIMARY KEY ( ID )" +
                     ");";

        try {
            defaultSQLQuery(sql);
        } catch (SQLException e) {
            System.out.println("Table not created. Table 'users' already exists");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE USERS";

        try {
            defaultSQLQuery(sql);
        } catch (SQLException throwables) {
            System.out.println("Removal failed. Table 'users' does not exist");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO USERS (NAME, LAST_NAME, AGE) VALUES(?, ?, ?)";

        PreparedStatement statement = null;

        connection.setAutoCommit(false);

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();

            connection.commit();

            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    System.out.println("Statement can't be closing");
                    throwables.printStackTrace();
                }
            }
            connection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE ID = " + id;

        try {
            defaultSQLQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LAST_NAME, AGE FROM USERS";

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            try {
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getLong("ID"));
                    user.setName(resultSet.getString("NAME"));
                    user.setLastName(resultSet.getString("LAST_NAME"));
                    user.setAge(resultSet.getByte("AGE"));

                    usersList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException throwables) {
                        System.out.println("ResultSet can't be closing");
                        throwables.printStackTrace();
                    }
                }
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    System.out.println("Statement can't be closing");
                    throwables.printStackTrace();
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USERS";

        try {
            defaultSQLQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void defaultSQLQuery(String sql) throws SQLException {
        PreparedStatement statement = null;

        connection.setAutoCommit(false);

        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Statement can't be closing");
                    e.printStackTrace();
                }
            }
            connection.setAutoCommit(true);
        }
    }
}

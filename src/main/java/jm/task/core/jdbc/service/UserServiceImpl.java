package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBCImpl;

    public UserServiceImpl() {
        this.userDaoJDBCImpl = UserDaoJDBCImpl.getInstance();
    }

    @Override
    public void createUsersTable() {
        userDaoJDBCImpl.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBCImpl.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBCImpl.saveUser(name, lastName, age);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBCImpl.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBCImpl.cleanUsersTable();
    }
}

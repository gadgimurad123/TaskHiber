package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBC;

    public UserServiceImpl() {
        this.userDaoJDBC = UserDaoJDBCImpl.getInstance();
    }

    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBC.saveUser(name, lastName, age);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }

}

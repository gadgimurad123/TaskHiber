package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBCImpl;
    private UserDaoHibernateImpl userDaoHibernateImpl;

    public UserServiceImpl() {
        this.userDaoHibernateImpl = UserDaoHibernateImpl.getInstance();
//        this.userDaoJDBCImpl = UserDaoJDBCImpl.getInstance();
    }

    @Override
    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();
//        userDaoJDBCImpl.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();
//        userDaoJDBCImpl.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.saveUser(name, lastName, age);
//        userDaoJDBCImpl.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);
//        userDaoJDBCImpl.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();
//        return userDaoJDBCImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();
//        userDaoJDBCImpl.cleanUsersTable();
    }

}

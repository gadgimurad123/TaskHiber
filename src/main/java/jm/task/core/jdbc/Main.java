package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBCImpl = UserDaoJDBCImpl.getInstance();

        // 1 - Создание таблицы User(ов)
        userDaoJDBCImpl.createUsersTable();

        // 2 - Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )
        userDaoJDBCImpl.saveUser("Mix", "Google", (byte) 100);
        userDaoJDBCImpl.saveUser("Stack", "OverFlow", (byte) 77);
        userDaoJDBCImpl.saveUser("Taxi", "Yandex", (byte) 19);
        userDaoJDBCImpl.saveUser("Bob", "Marley", (byte) 30);

        // 3 - Получение всех User из базы и вывод в консоль
        // (должен быть переопределен toString в классе User)
        List<User> userList = userDaoJDBCImpl.getAllUsers();
        for (User u : userList) {
            System.out.println(u.toString());
        }

        // 4 - Очистка таблицы User(ов)
        userDaoJDBCImpl.cleanUsersTable();

        // 5 - Удаление таблицы
        userDaoJDBCImpl.dropUsersTable();
    }
}
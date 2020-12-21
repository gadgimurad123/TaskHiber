package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new  UserServiceImpl();

        // 1 - Создание таблицы User(ов)
        userServiceImpl.createUsersTable();

        // 2 - Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )
        userServiceImpl.saveUser("Mix", "Google", (byte) 100);
        userServiceImpl.saveUser("Stack", "OverFlow", (byte) 77);
        userServiceImpl.saveUser("Taxi", "Yandex", (byte) 19);
        userServiceImpl.saveUser("Bob", "Marley", (byte) 30);

        // 3 - Получение всех User из базы и вывод в консоль
        // (должен быть переопределен toString в классе User)
        List<User> userList = userServiceImpl.getAllUsers();
        for (User u : userList) {
            System.out.println(u.toString());
        }

        // 4 - Очистка таблицы User(ов)
        userServiceImpl.cleanUsersTable();

        // 5 - Удаление таблицы
        userServiceImpl.dropUsersTable();
    }
}
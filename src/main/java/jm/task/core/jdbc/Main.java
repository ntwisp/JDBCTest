package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Alex", "Sagamoto",(byte) 102);
        userService.saveUser("Bobrik", "Polyakovich", (byte) 33);
        userService.saveUser("Эдвард", "Каллен", (byte) 77);
        userService.saveUser("Ali", "Muhammad", (byte) 74);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

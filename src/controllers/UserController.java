package controllers;

import entities.User;
import repositories.UserRepository;

import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private UserRepository ur;
    private Connection connection;

    public UserController(UserRepository ur, Connection connection) {
        this.ur = ur;
        this.connection = connection;
    }

    public void addUser(Scanner scanner) {
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();

        System.out.print("Enter id: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();


        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setRole(role);

        if (Objects.equals(role, "Student")) {
            System.out.print("Enter group: ");
            String group = scanner.nextLine();
            user.setGroup(group);
        }





        ur.addUser(user);
    }
}

package controllers;

import entities.Student;
import entities.User;
import repositories.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private UserRepository ur;
    private Connection connection;

    public UserController() throws SQLException {
        ur = UserRepository.getInstance();
    }
    public void showUsers(){
        ur.printUsers();
    }

    public void addUser(Scanner scanner) {
        String group=null;
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();

        System.out.print("Enter id: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        if (Objects.equals(role, "Student")) {
            System.out.print("Enter group: ");
            group = scanner.nextLine();
            Student student = new Student.Builder()
                    .id(id)
                    .name(name)
                    .group(group)
                    .build();
        }
        User user = new User.Builder()
                .id(id)
                .name(name)
                .group(group)
                .role(role)
                .build();
        ur.addUser(user);
        System.out.println("User created successfully.");
    }
}

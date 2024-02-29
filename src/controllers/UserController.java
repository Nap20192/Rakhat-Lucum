package controllers;

import entities.Student;
import entities.User;
import repositories.StaffRepository;
import repositories.StudentRepository;
import repositories.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private UserRepository ur;
    private StudentRepository studentRepository;
    private StaffRepository staffRepository;
    private Connection connection;

    public UserController() throws SQLException {
        ur = UserRepository.getInstance();
        studentRepository = StudentRepository.getInstance();
        staffRepository = StaffRepository.getInstance();
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
            User student = User.UserFactory.createUser(role, id, name, group);
            studentRepository.addUser(student);
        }

        else if (Objects.equals(role, "Staff")) {
            group = "_STAFF_";
            User staff = User.UserFactory.createUser(role, id, name, group);
            staffRepository.addUser(staff);
        }

        else { System.out.print("There is no such role."); }




        System.out.println("User created successfully.");
    }
}

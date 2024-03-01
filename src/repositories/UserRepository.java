package repositories;

import entities.Staff;
import entities.Student;
import entities.User;

import java.sql.*;

public class UserRepository {
    private static BookRepository lib;
    private static UserRepository instance;
     static Connection connection;
    protected UserRepository()
    {
        connection= DatabaseManager.getConnection();
    }

    public static synchronized UserRepository getInstance(){
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    public void addUser(String id, String name, String group, String role){
        if(role=="Student"){
            User student = User.UserFactory.createUser(role, id, name, group);
        } else if (role=="Staff") {
            User staff = User.UserFactory.createUser(role, id, name, group);
        }
    }
    public void printUsers(){
    }
}




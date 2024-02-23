package repositories;

import entities.Student;
import repositories.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository extends UserRepository {
    private static StudentRepository instance;

    private StudentRepository() {
        super();
    }

    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public void addStudent(Student student) {
        String addstudent = "INSERT INTO users (id, name, user_group, role_id) VALUES (?, ?, ?, (SELECT role_id FROM roles WHERE name = ?))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addstudent)) {
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getGroup());
            preparedStatement.setString(4, student.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    public void printStudents() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        try {
            Statement statement = connection.createStatement();
            String getUsersQuery = "SELECT users.id, users.name, roles.name FROM users JOIN roles ON roles.role_id = users.role_id";
            ResultSet rs = statement.executeQuery(getUsersQuery);

            while (rs.next()) {
                System.out.println(ANSI_YELLOW + "\"" + rs.getString(1) + "\"" + ANSI_RESET + " " + rs.getString(2) + " " + rs.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



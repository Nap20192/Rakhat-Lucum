package repositories;

import entities.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffRepository extends UserRepository {
    @Override
    public void addUser(String id, String name, String group, String role) {
        super.addUser(id, name, group, role);

        String addstudent = "INSERT INTO users (id, name, user_group, role_id) VALUES (?, ?, ?, (SELECT role_id FROM roles WHERE name = ?))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addstudent)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, group);
            preparedStatement.setString(4, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }
@Override
    public void printUsers() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        try {
            Statement statement = connection.createStatement();
            String getUsersQuery = "SELECT users.id, users.name, roles.name FROM users JOIN roles ON roles.role_id = users.role_id WHERE roles.name = 'STUDENT'";
            ResultSet rs = statement.executeQuery(getUsersQuery);

            while (rs.next()) {
                System.out.println(ANSI_YELLOW + "\"" + rs.getString(1) + "\"" + ANSI_RESET + " " + rs.getString(2) + " " + rs.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



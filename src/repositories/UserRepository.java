package repositories;

import entities.User;

import java.sql.*;

public class UserRepository {
    private BookRepository lib;
    private Connection connection;


    public UserRepository(Connection connection) {
        this.connection = connection;
    }
    public void addUser(User user) {
        String addBookQuery = "INSERT INTO users (id, name, user_group, role_id) VALUES (?, ?, ?, (SELECT role_id FROM roles WHERE name = ?))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addBookQuery)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getGroup());
            preparedStatement.setString(4, user.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printUsers() {
        final String ANSI_BLUE = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        try {
            Statement statement = connection.createStatement();
            String getUsersQuery = "SELECT users.id, users.name, roles.name FROM users JOIN roles ON roles.role_id = users.role_id";
            ResultSet rs = statement.executeQuery(getUsersQuery);

            while (rs.next()) {
                System.out.println(ANSI_BLUE + "\"" + rs.getString(1) + "\"" + ANSI_RESET + " " + rs.getString(2) + " " + rs.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

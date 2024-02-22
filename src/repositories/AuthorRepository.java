package repositories;

import entities.Book;

import java.sql.*;

import java.sql.Connection;

public class AuthorRepository {
    private Connection connection;

    public AuthorRepository(Connection connection) {this.connection = connection;}
    public void addAuthor(String authorName) {
        String insertAuthorQuery = "INSERT INTO authors (name) VALUES (?) ON CONFLICT (name) DO NOTHING";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorQuery)) {
            preparedStatement.setString(1, authorName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

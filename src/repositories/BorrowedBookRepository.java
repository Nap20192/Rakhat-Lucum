package repositories;

import entities.Book;
import entities.User;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
public class BorrowedBookRepository {
    private Connection connection;

    private UserRepository userRepository;
    private BookRepository bookRepository;

    public BorrowedBookRepository(Connection connection) {
        this.connection = connection;
        this.userRepository = new UserRepository(connection);
        this.bookRepository = new BookRepository(connection);
    }

    public void giveBook(String title, String author, String id, int book_quantity) {
        String addBookQuery = "INSERT INTO book_borrowings (book_id, user_id, quantity) " +
                "VALUES ((SELECT book_id FROM books WHERE title = ? " +
                "AND author = (SELECT author_id from authors WHERE name = ?)), (SELECT user_id FROM users WHERE id = ?), ?)" +
                "ON CONFLICT (book_id, user_id) DO UPDATE SET " +
                "quantity = book_borrowings.quantity + ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addBookQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, id);
            preparedStatement.setInt(4, book_quantity);
            preparedStatement.setInt(5, book_quantity);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void returnBook(String title, String author, String id, int book_quantity) {
        String addBookQuery = "UPDATE book_borrowings SET quantity = book_borrowings.quantity - ? " +
                "WHERE book_id = (SELECT book_id FROM books WHERE title = ? AND author = (SELECT author_id from authors WHERE name = ?)) AND user_id = (SELECT user_id FROM users WHERE id = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addBookQuery)) {
            preparedStatement.setInt(1, book_quantity);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, id);



            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkClearance(String title, String id) {
        int book_clearance = bookRepository.getBookClearance(title);
        int user_clearance = userRepository.getUserClearance(id);

        return book_clearance <= user_clearance;
    }


    public void removeIfZero(String title, String author, String id) {
        String UpdateTableQuery = "DELETE FROM book_borrowings " +
                "WHERE book_id = (SELECT book_id FROM books WHERE title = ? " +
                "AND author = (SELECT author_id FROM authors WHERE name = ?)) " +
                "AND user_id = (SELECT user_id FROM users WHERE id = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UpdateTableQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfZero(String title, String author, String id) {
        int quantity = 0;
        String getUserClearanceQuery = "SELECT quantity FROM book_borrowings " +
                "WHERE book_id = (SELECT book_id FROM books WHERE title = ? AND author = (SELECT author_id FROM authors WHERE name = ?)) " +
                "AND user_id = (SELECT user_id FROM users WHERE id = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserClearanceQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity == 0;
    }

    public boolean checkIfNotEnoughBooks(String title, String author, String id, int quantity) {
        return quantity <= getBookQuantity(title, author, id);
    }

    public int getBookQuantity(String title, String author, String id) {
        int quantity = 0;
        String getUserClearanceQuery = "SELECT quantity FROM book_borrowings " +
                "WHERE book_id = (SELECT book_id FROM books WHERE title = ? AND author = (SELECT author_id FROM authors WHERE name = ?)) " +
                "AND user_id = (SELECT user_id FROM users WHERE id = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserClearanceQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }
}

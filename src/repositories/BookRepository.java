package repositories;

import entities.Book;

import java.sql.*;

public class BookRepository {
    private Connection connection;
    private AuthorRepository authorRepository;

    public BookRepository(Connection connection) {
        this.connection = connection;
        this.authorRepository = new AuthorRepository(connection);
    }

    public void addBook(Book book) {
        String addBookQuery = "INSERT INTO books (title, isbn, year, quantity, clearance, author) VALUES (?, ?, ?, ?, ?, (SELECT author_id FROM authors WHERE name = ?))";
        authorRepository.addAuthor(book.getAuthor());
        try (PreparedStatement preparedStatement = connection.prepareStatement(addBookQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setString(6, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getClearance());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printBooks() {
        final String ANSI_BLUE = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        try {
            Statement statement = connection.createStatement();
            String getBooksQuery = "SELECT books.title, authors.name, books.quantity FROM books JOIN authors ON authors.author_id = books.author";
            ResultSet rs = statement.executeQuery(getBooksQuery);
            while (rs.next()) {
                System.out.println(ANSI_BLUE + "\"" + rs.getString(1) + "\"" + ANSI_RESET + " by " + rs.getString(2) + " : " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subQuantity(String title, String author, int quantity) {
        String UpdateTableQuery = "UPDATE books SET quantity = books.quantity - ? WHERE title = ? " +
                "AND author = (SELECT author_id FROM authors WHERE name = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UpdateTableQuery)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addQuantity(String title, String author, int quantity) {
        String UpdateTableQuery = "UPDATE books SET quantity = books.quantity + ? WHERE title = ? " +
                "AND author = (SELECT author_id FROM authors WHERE name = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UpdateTableQuery)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfNotEnoughBooks(String title, String author, int quantity) {
        return quantity <= getBookQuantity(title, author);
    }
    public int getBookQuantity(String title, String author) {
        int quantity = 0;
        String getUserClearanceQuery = "SELECT quantity FROM books " +
                "WHERE book_id = (SELECT book_id FROM books WHERE title = ? AND author = (SELECT author_id FROM authors WHERE name = ?));";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserClearanceQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public int getBookClearance(String title) {
        int book_clearance = 0;
        String getBookClearanceQuery = "SELECT clearance FROM books WHERE books.title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getBookClearanceQuery)) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                book_clearance = rs.getInt("clearance");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book_clearance;
    }
}

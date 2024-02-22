package repositories;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class GenreRepository {
    private Connection connection;
    private static GenreRepository instance;
    private GenreRepository()  {
        connection=Database.getConnection();
    }
    public static synchronized GenreRepository getInstance(){
        if (instance == null) {
            instance = new GenreRepository();
        }
        return instance;
    }

    public void getUserSelectedGenres(List<Integer> selectedGenres) {
        try {
            Scanner scanner = new Scanner(System.in);
            String getGenresQuery = "SELECT genre_id, name FROM genres";
            System.out.println("Select genres from the list (enter genre numbers separated by spaces):");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getGenresQuery);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + ". " + rs.getString(2));
            }

            String input = scanner.nextLine();
            String[] selectedGenreNumbers = input.split("\\s+");
            for (String number : selectedGenreNumbers) {
                selectedGenres.add(Integer.parseInt(number));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGenresIntoDatabase(List<Integer> genres, String bookTitle) {
        String insertGenresQuery = "INSERT INTO book_genres(book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = ?), ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertGenresQuery)) {
            for (int genre : genres) {
                preparedStatement.setString(1, bookTitle);
                preparedStatement.setInt(2, genre);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

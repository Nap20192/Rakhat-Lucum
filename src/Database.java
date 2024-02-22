import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
public class Database {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/real_library";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load PostgreSQL JDBC driver", e);
        }
    }
    public static void init() {
        try (Connection connection = connect()) {
            createAuthorsTable(connection);
            createBooksTable(connection);
            createGenresTable(connection);
            createBookGenresTable(connection);
            createRolesTable(connection);
            createUsersTable(connection);
            createBookBorrowingsTable(connection);

            fillRolesTable(connection);
            fillGenres(connection);

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
}



    private static void createBooksTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS books ("
                    + "book_id SERIAL PRIMARY KEY,"
                    + "title VARCHAR(100) NOT NULL UNIQUE,"
                    + "isbn VARCHAR(100) NOT NULL,"
                    + "author INT REFERENCES authors(author_id),"
                    + "year INT NOT NULL,"
                    + "quantity INT CHECK (quantity >= 0),"
                    + "clearance INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createAuthorsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS authors ("
                    + "author_id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100) UNIQUE)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createGenresTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS genres ("
                    + "genre_id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100) UNIQUE)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createBookGenresTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS book_genres ("
                    + "book_id INT REFERENCES books(book_id),"
                    + "genre_id INT REFERENCES genres(genre_id),"
                    + "PRIMARY KEY (book_id, genre_id))";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void fillGenres(Connection connection) throws SQLException{
        try (Statement statement = connection.createStatement()) {
            String insertGenresQuery = "INSERT INTO genres (name) VALUES " +
                    "('Fantasy')," +
                    "('Sci-fi')," +
                    "('Non-fiction')," +
                    "('Literary fiction')," +
                    "('Self-help')," +
                    "('Academic')," +
                    "('High fantasy')," +
                    "('Low fantasy')," +
                    "('Dystopian')," +
                    "('Romance')," +
                    "('Thriller')," +
                    "('Comedy')," +
                    "('Poetry')," +
                    "('Horror') ON CONFLICT (name) DO NOTHING;";

            statement.executeUpdate(insertGenresQuery);
        }
    }

    private static void createUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "user_id SERIAL PRIMARY KEY,"
                    + "id VARCHAR(100) NOT NULL UNIQUE,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "user_group VARCHAR(100),"
                    + "role_id INT NOT NULL REFERENCES roles(role_id))";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void createRolesTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS roles ("
                    + "role_id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100) UNIQUE,"
                    + "clearance INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    private static void fillRolesTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String insertGenresQuery = "INSERT INTO roles (name, clearance) VALUES " +
                    "('Staff', 2)," +
                    "('Student', 1) ON CONFLICT (name) DO NOTHING;";

            statement.executeUpdate(insertGenresQuery);
        }
    }

    private static void createBookBorrowingsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS book_borrowings ("
                    + "book_id INT REFERENCES books(book_id),"
                    + "user_id INT REFERENCES users(user_id),"
                    + "quantity INT NOT NULL CHECK (quantity >= 0),"
                    + "PRIMARY KEY (book_id, user_id))";

            statement.executeUpdate(createTableQuery);
        }
    }
}


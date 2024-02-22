//import entities.Staff;
//import entities.Student;
import repositories.*;
import controllers.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = Database.connect();
        Database.init();
        BookRepository bookRepository = new BookRepository(connection);
        UserRepository ur = new UserRepository(connection);
        UserController userController = new UserController(ur, connection);
        GenreRepository genreRepository = new GenreRepository(connection);
        BookController bookController = new BookController(bookRepository, connection, genreRepository);
        BorrowedBookController borrowedBookController = new BorrowedBookController(connection);
        UserRepository userRepository = new UserRepository(connection);
        Scanner scanner = new Scanner(System.in);
        whileLoop:while (true) {
            {
                System.out.println("1) To add a new book;");
                System.out.println("2) To show all available books;");
                System.out.println("3) To add a new user;");
                System.out.println("4) To give a certain book to a certain user;");
                System.out.println("5) To return a book back to the library from user.");
                System.out.println("6) To show all available users");
                System.out.println("7) Exit");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    bookController.addBook(scanner);
                    break;
                }
                case 2: {
                    bookRepository.printBooks();
                    break;
                }
                case 3: {
                    userController.addUser(scanner);
                    break;
                }
                case 4: {
                    borrowedBookController.giveBook(scanner);
                    break;
                }
                case 5: {
                    borrowedBookController.returnBook(scanner);
                    break;
                }
                case 6: {
                    userRepository.printUsers();
                    break;
                }
                case 7: {
                    break whileLoop;
                }
            }
        }
    }
}
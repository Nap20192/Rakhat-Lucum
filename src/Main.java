//import entities.Staff;
//import entities.Student;
import repositories.*;
import controllers.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseManager.getInstance();
        Connection connection = DatabaseManager.getConnection();
        DatabaseManager.init();
        UserController userController = new UserController();
        BookController bookController = new BookController();
        BorrowedBookController borrowedBookController = new BorrowedBookController();
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
                    bookController.showBooks();
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
                    userController.showUsers();
                    break;
                }
                case 7: {
                    break whileLoop;
                }
            }
        }
    }
}
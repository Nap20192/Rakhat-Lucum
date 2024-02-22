package controllers;

import java.sql.*;
import java.util.Scanner;

import repositories.BookRepository;
import repositories.BorrowedBookRepository;

public class BorrowedBookController {
    private Connection connection;
    private BorrowedBookRepository borrowedBookRepository;
    private BookRepository bookRepository;


    public BorrowedBookController() {
        borrowedBookRepository=BorrowedBookRepository.getInstance();
        bookRepository=BookRepository.getInstance();
    }

    public void giveBook(Scanner scanner) {


        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();


        if (bookRepository.checkIfNotEnoughBooks(title, author, quantity)) {
            if (borrowedBookRepository.checkClearance(title, id)) {
                borrowedBookRepository.giveBook(title, author, id, quantity);
                bookRepository.subQuantity(title, author, quantity);
                System.out.println("Book successfully given.");
            }

            else {
                System.out.println("Sorry, but this book is not available to your role");
            }

        }
        else {
            System.out.println("There aren't enough books.");
        }



    }

    public void returnBook(Scanner scanner) {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (borrowedBookRepository.checkIfNotEnoughBooks(title, author, id, quantity)) {
            borrowedBookRepository.returnBook(title, author, id, quantity);
            bookRepository.addQuantity(title, author, quantity);
            if (borrowedBookRepository.checkIfZero(title, author, id)) {borrowedBookRepository.removeIfZero(title, author, id);}
            System.out.println("Book successfully returned.");
        }
        else {
            System.out.println("There aren't enough books.");
        }



    }
}

package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.User;
import repositories.GenreRepository;
import repositories.BookRepository;
import entities.Book;

public class BookController {
    private BookRepository lib;
    private Connection connection;
    private GenreRepository genreRepository;

    public BookController(BookRepository lib, Connection connection, GenreRepository genreRepository) {
        this.lib = lib;
        this.connection = connection;
        this.genreRepository = genreRepository;
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        List<Integer> selectedGenres = new ArrayList<>();
        genreRepository.getUserSelectedGenres(selectedGenres);

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter clearance: ");
        int clearance = scanner.nextInt();
        Book book = new Book.Builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .year(year)
                .quantity(quantity)
                .clearance(clearance)
                .build();
        lib.addBook(book);
        System.out.println("User created successfully.");
    }




}

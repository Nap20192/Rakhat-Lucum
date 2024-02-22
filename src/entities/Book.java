package entities;

public class Book {
    private int id, year, quantity, clearance;
    private String title, isbn, genre, author;

    public Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.isbn = builder.isbn;
        this.genre = builder.genre;
        this.author = builder.author;
        this.year = builder.year;
        this.quantity = builder.quantity;
        this.clearance = builder.clearance;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getClearance() {
        return clearance;
    }

    public static class Builder {
        private int id, year, quantity, clearance;
        private String title, isbn, genre, author;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder clearance(int clearance) {
            this.clearance = clearance;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}

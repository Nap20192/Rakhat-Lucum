package entities;

public class Book {
   private String title, isbn, genre, author;
   private int id, year, quantity,clearance;

   public Book () {
       this.id = id;
       this.title = title;
       this.isbn = isbn;
       this.genre = genre;
       this.author = author;
       this.year = year;
       this.quantity = quantity;
       this.clearance = clearance;
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
    public int getClearance(){
       return clearance;
    }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setClearance(int clearance){
        this.clearance = clearance;
    }

}

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM public.books");


            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);

        Library lib = new Library();

        User p1=new User(2310,"Rakhat", "IT-2307");
        p1.getInfo();

        {
            lib.addBook(new Book("TKM", "1234567890", "Classic", "Harper Lee", 1960, 5,1));
            lib.addBook(new Book("1984", "2345678901", "Dystopian", "George Orwell", 1949, 4,2));
            lib.addBook(new Book("The Great Gatsby", "3456789012", "Classic", "F. Scott Fitzgerald", 1925, 6,1));
            lib.addBook(new Book("One Hundred Years of Solitude", "4567890123", "Magical Realism", "Gabriel Garcia Marquez", 1967, 7,2));
            lib.addBook(new Book("A Brief History of Time", "5678901234", "Science", "Stephen Hawking", 1988, 5,1));
            lib.addBook(new Book("The Catcher in the Rye", "6789012345", "Classic", "J.D. Salinger", 1951, 4,1));
            lib.addBook(new Book("The Hobbit", "7890123456", "Fantasy", "J.R.R. Tolkien", 1937, 8,1));
            lib.addBook(new Book("Pride and Prejudice", "8901234567", "Classic Romance", "Jane Austen", 1813, 3,1));
            lib.addBook(new Book("Harry Potter and the Sorcerer's Stone", "9012345678", "Fantasy", "J.K. Rowling", 1997, 10,1));

        }

        {lib.addUser(new Student(1, "John Doe", "Group A"));
        lib.addUser(new Staff(2, "Jane Smith", "Group B"));
        lib.addUser(new Student(3, "Alice Johnson", "Group C"));
        lib.addUser(new Staff(4, "Bob Brown", "Group A"));}
        while (true){
            {System.out.println("1) To add a new book;");
            System.out.println("2) To show all available books;");
            System.out.println("3) To add a new user;");
            System.out.println("4) To give a certain book to a certain user;");
            System.out.println("5) To return a book back to the library from user.");
            System.out.println("6) To show all available users");}
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                {System.out.println("Title");
                    String title = in.nextLine();

                    System.out.println("Isbn");
                    String isbn = in.nextLine();

                    System.out.println("Genre");
                    String genre = in.nextLine();

                    System.out.println("Author");
                    String author = in.nextLine();

                    System.out.println("Year");
                    int year = in.nextInt();
                    in.nextLine();

                    System.out.println("Quantity");
                    int quantity = in.nextInt();
                    in.nextLine();

                    System.out.println("Quantity");
                    int clearance = in.nextInt();
                    in.nextLine();

                    lib.addBook(new Book(title,isbn,genre,author,year,quantity,clearance));}
                    break;
                case 2:
                    lib.printbooks();
                    break;
                case 3:
                {System.out.println("ID:");
                    int id = in.nextInt();
                    in.nextLine();
                    boolean idExists;
                    do {
                        idExists = false;
                        for(User user: lib.getUsers()){
                            if(id == user.getId()){
                                System.out.println("insert another ID");
                                id = in.nextInt();
                                idExists = true;
                                in.nextLine();
                                break;
                            }
                        }
                    } while (idExists);
                    System.out.println("Name:");
                    String name = in.nextLine();

                    System.out.println("Group:");
                    String group = in.nextLine();

                    System.out.println("Rank:");
                    System.out.println("Option 1: add student");
                    System.out.println("Option 2: add staff");
                    int option = in.nextInt();
                    switch(option){
                        case 1:
                            lib.addStudent(new Student(id, name, group));
                            break;
                        case 2:
                            lib.addStaff(new Staff(id, name, group));
                            break;
                        default:
                            lib.addUser(new User(id, name, group));
                            break;
                    }

                    }
                    break;
                case 4:

                    System.out.println("title");
                    String title1=in.nextLine();
                    System.out.println("ID");
                    int id1=in.nextInt();
                    lib.giveBook(title1,id1);
                    break;
                case 5:

                    System.out.println("title");
                    String title = in.nextLine();
                    System.out.println("ID");
                    int id=in.nextInt();
                    lib.getBook(title,id);
                    break;
                case 6:
                    lib.printusers();
                    break;

                default:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}

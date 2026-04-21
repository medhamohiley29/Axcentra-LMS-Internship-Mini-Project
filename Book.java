import java.util.ArrayList;

public class Book {
     private String title;
    private String author;
    private boolean isIssued;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }
    public static void main(String[] args) {

        Library lib = new Library();

        Book b1 = new Book("Java Basics", "James Gosling");
        Book b2 = new Book("OOP Concepts", "Bjarne Stroustrup");

        lib.addBook(b1);
        lib.addBook(b2);

        lib.showBooks();

        StudentUser user = new StudentUser("Medha");

        user.borrowBook(b1);
        user.borrowBook(b1);
        lib.showBooks();
        user.returnBook(b1);
        lib.showBooks();
    }
}
abstract class User {
    protected String name;

    User(String name) {
        this.name = name;
    }

    abstract void borrowBook(Book book);
    abstract void returnBook(Book book);
}
class StudentUser extends User {

    StudentUser(String name) {
        super(name);
    }

    @Override
    void borrowBook(Book book) {
        if (!book.isIssued()) {
            book.setIssued(true);
            System.out.println(name + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Book already issued!");
        }
    }

    @Override
    void returnBook(Book book) {
        if (book.isIssued()) {
            book.setIssued(false);
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println("Book was not issued!");
        }
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Add book
    void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Display all books
    void showBooks() {
        System.out.println("\nLibrary Books:");
        for (Book b : books) {
            System.out.println(b.getTitle() + " by " + b.getAuthor() +
                    (b.isIssued() ? " [Issued]" : " [Available]"));
        }
    }

    // Find book by title
    Book findBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }
}
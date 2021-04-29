package mini_project;

public class Library {

    private String title;
    private String author;
    private String edition;
    private String publisher;
    private int year_published;
    private String ISBN;
    private int price;
    private int quantity;
    private String genre;
    private String book_id;

    Library(
            String title,
            String author,
            String edition,
            String publisher,
            int year_published,
            String ISBN,
            int price,
            int quantity,
            String genre,
            String book_id) {

        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.year_published = year_published;
        this.ISBN = ISBN;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.book_id = book_id;
        System.out.println("Book added. ");
    }

    void display() {
    }

    void display_short() {
    }

    String get_title() {
        return title;
    }

    String get_author() {
        return author;
    }

    String get_publisher() {
        return publisher;
    }

    int get_price() {
        return price;
    }

    int get_quantity() {
        return quantity;
    }

    String get_bookid() {
        return book_id;
    }

    String get_genre() {
        return genre;
    }
    
    String get_edition()    {
        return edition;
    }

    String get_isbn()   {
        return ISBN;
    }
    
    int get_year()   {
        return year_published; 
    }
    void set_quantity(int quantity) {
        this.quantity = quantity;
    }

}

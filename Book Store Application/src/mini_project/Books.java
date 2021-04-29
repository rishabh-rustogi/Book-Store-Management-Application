package mini_project;

public class Books extends Library {

    public Books(String title, String author, String edition, String publisher, int year_published, String ISBN, int price, int quantity, String genre, String book_id) {
        super(title, author, edition, publisher, year_published, ISBN, price, quantity, genre, book_id);
    }

    void display() {
        System.out.println(get_bookid() + "\t" + get_title() + "\t" + get_author() + "\t" + get_edition()+ "\t" + get_publisher()  + "\t\t" + get_isbn() + "\t" + get_genre() + "\t" + get_quantity() + "\t" + get_price());
    }

    void display_cust() {
         System.out.println(get_bookid() + "\t" + get_title() + "\t" + get_author() + "\t" + get_edition()+ "\t" + get_publisher() + "\t" + get_genre() + "\t" + get_quantity() + "\t" + get_price());
    }

}

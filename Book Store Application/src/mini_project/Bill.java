package mini_project;

public class Bill {

    private String title, author, book_id,c_id;
    private int price, quantity;
    private float tax;

    Bill(String c_id,String title, String author, String book_id, int price, int quantity, float tax) {
        this.title = title;
        this.author = author;
        this.book_id = book_id;
        this.price = price;
        this.quantity = quantity;
        this.tax = tax;
        this.c_id=c_id;
    }
    
    String get_cid()    {
        return c_id;
    }

    String get_title() {
        return title;
    }

    String get_author() {
        return author;
    }

    String get_bookid() {
        return book_id;
    }

    int get_price() {
        return price;
    }

    int get_quantity() {
        return quantity;
    }

    void display_bill() {
        System.out.println(get_bookid() + "\t" + get_title() + "\t" + get_author() + "\t" + get_price() + "\t" + get_quantity());
    }
}

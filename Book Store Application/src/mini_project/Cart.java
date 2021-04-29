package mini_project;

import java.util.Scanner;
import static mini_project.Admin.book_store;

public class Cart {

    private String c_id;
    private String bookid;
    private String title;
    private String author;
    private int quantity;
    private int price;

    Cart(String c_id,String bookid, String title, String author, int quantity, int price) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.c_id=c_id;
        System.out.println("Book added to your cart. ");
    }

    void display_cart() {
    }

    String get_c_id()   {
        return c_id;
    }
    
    String get_title() {
        return title;
    }

    String get_author() {
        return author;
    }

    int get_price() {
        return price;
    }

    int get_quantity() {
        return quantity;
    }

    String get_bookid() {
        return bookid;
    }

    void set_quantity(int quantity) {
        this.quantity = quantity;
    }

}

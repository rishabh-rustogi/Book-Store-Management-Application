package mini_project;

import static mini_project.Admin.book_store;

public class Customer_cart extends Cart {

    public Customer_cart(String c_id,String bookid, String title, String author, int quantity, int price) {
        super(c_id,bookid, title, author, quantity, price);
    }

    void display_cart() {
        System.out.println(get_bookid() + "\t" + get_title() + "\t" + get_author() + "\t" + get_price() + "\t" + get_quantity());
    }

}

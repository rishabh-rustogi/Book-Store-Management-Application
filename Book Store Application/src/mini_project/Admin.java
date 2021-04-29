package mini_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static mini_project.Mini_Project.admin_list;
//import static mini_project.Admin.book_list;

public class Admin {

    static ArrayList<Library> book_store = new ArrayList<Library>();
    static String title, author, edition, publisher, isbn, genre, book_id;
    static int year_published, price, quantity;
    String cust_id;
    String pass;
    
    void create_admin() throws IOException {
        String id;
                int counter = 0;
        String pass1, pass2;
        System.out.print("Enter new id : ");
        id = Mini_Project.input.readLine();
        for (Admin obj : admin_list) {
            if (id.equals(obj.cust_id)) {
                System.out.println("Sorry, The username already exists. ");
                return;
            }
        }
        do {
            System.out.print("Enter your password : ");
            pass1 = Mini_Project.input.readLine();
            System.out.print("Again enter the same password : ");
            pass2 = Mini_Project.input.readLine();
            if (pass2.equals(pass1)) {
                System.out.println("Passwords Matched");
                counter = 0;
            } else {
                System.out.println("Passwords do not match.");
                counter = 1;
            }
        } while (counter == 1);
        System.out.println("Your new admin id is successfully created.");

        cust_id = id;
        pass = pass1;
    }

    void enter_details() throws IOException {
        boolean continue_input = true;
        //String fake;

        do {
            try {
                System.out.println("Enter the DETAILS of the book.");
                System.out.print("Name -> ");
                title = Mini_Project.input.readLine();
                System.out.print("Author-> ");
                author = Mini_Project.input.readLine();
                System.out.print("Publisher -> ");
                publisher = Mini_Project.input.readLine();
                System.out.print("Year Published -> ");
                year_published = Integer.parseInt(Mini_Project.input.readLine());
                System.out.print("Edition -> ");
                edition = Mini_Project.input.readLine();
                System.out.print("ISBN -> ");
                isbn = Mini_Project.input.readLine();
                System.out.print("Price -> ");
                price = Integer.parseInt(Mini_Project.input.readLine());
                System.out.print("Quantity -> ");
                quantity = Integer.parseInt(Mini_Project.input.readLine());
                System.out.print("Genre [ Can enter many without spaces ] -> ");
                genre = Mini_Project.input.readLine();
                System.out.print("Book ID -> ");
                book_id = Mini_Project.input.readLine();
                continue_input = false;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter the correct type of arguments \n[Strings for Name, Author, Publisher, Edition, ISBN, Genre, Book Id]\n[Integer for Price, Quantity] \n");
                //fake = Mini_Project.input.readLine();
            }
        } while (continue_input);
        
        add_genre(genre);

    }
    
     void add_genre(String name) {
        String genre_name=name;
        for(Favourites obj : Customer.interest1) {
            if(genre_name.equalsIgnoreCase(obj.get_name()))   
            {
                return;
            }
        }
            Favourites f = new Favourites(name,1);
             Customer.interest1.add(f);
             System.out.println("GENRE ADDED");
        
    }
    
    void count(int book_n,int threshold,String tite,String book_id) throws LessBook_Exception {
        if((book_n-threshold)<0)  {
            throw new LessBook_Exception(book_id,title,book_n);
        }
    }

    void short_books() throws LessBook_Exception, IOException {
        boolean con_in = true;
        int threshold = 0, counter = 0;
        //String fake;

        do {
            try {
                System.out.println("Enter the threshold quantity you want to see : ");
                threshold = Integer.parseInt(Mini_Project.input.readLine());
                counter = 0;
                con_in = false;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a numerical value.");
                //fake=Mini_Project.input.readLine();
            }
        } while (con_in);

        System.out.println("Book ID\tTitle\tQuantity");
        for (Library obj : book_store) {
            try {
                count(obj.get_quantity(),threshold,obj.get_title(),obj.get_bookid());
            }   catch(LessBook_Exception ex)
            {
                System.out.println("Less book found"+ex);
                counter++;
            }
           /* if (obj.get_quantity() < threshold) {
                obj.display();
                counter++;
            }*/
        }
        if (counter == 0) {
            System.out.println("No books found less than your threshold value.");
        } else {
            System.out.println(counter + " Book(s) found less than your threshold value.");
        }
    }

    void admin_menu() throws IOException {
        int opt=0;
        boolean con_in = true;
        String fake;

        do {
            try {
                System.out.println("1. Add Books 2. Threshold value 3. See books 4. Log Out ");
                opt = Integer.parseInt(Mini_Project.input.readLine());
                switch (opt) {
                    case 1:
                        enter_details();
                        Books temp = new Books(title, author, edition, publisher, year_published, isbn, price, quantity, genre, book_id);
                        book_store.add(temp);
                        System.out.println("Your book has been added.");
                        for (Favourites obj : Customer.interest1) {
                            if (genre.equalsIgnoreCase(obj.get_name())) {
                                return;
                            }
                        }
                        Favourites f = new Favourites(genre, 1);
                        Customer.interest1.add(f);
                        System.out.println("GENRE ADDED");
                        //con_in=false;
                        break;
                    case 2:
                        short_books();
                        //con_in=false;
                        break;
                    case 3:
                        display_books();
                        //con_in=false;
                        break;
                    case 4:
                        //con_in=false;
                        break;
                    default:
                        System.out.println("Enter a valid option. ");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a numerical entry.");
                //fake = Mini_Project.input.readLine();
            }
        } while (opt!=4);
    }


    void display_books() {
        if (book_store.isEmpty() == true) {
            System.out.println("No books have been added");
            return;
        }
        System.out.println("Here is the available list of BOOKS : ");
        System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tISBN\tGenre\tQuant\tPrice");
        for (Library obj : book_store) {
            obj.display();
        }
    }
    
     public int get_top()  {
        int max=0;
        for(Favourites obj:Customer.interest1)  {
            if(max<obj.get_count())
                max=obj.get_count();
        }
        return max;
    }
    
    void display_fav()  {
        int max;
        max = get_top();
        
        if(Customer.interest1.isEmpty() == false)   {
            System.out.println("Here is the available list of BOOKS : ");
        System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tISBN\tGenre\tQuant\tPrice");
        for (Favourites obj : Customer.interest1) {
            if(obj.get_count()==max)    {
            for(Library obj1 : book_store)  {
                if(obj.get_name().equalsIgnoreCase(obj1.get_genre()))
                    obj1.display();
            }
            } 
        }
        }
    }
    
    

}

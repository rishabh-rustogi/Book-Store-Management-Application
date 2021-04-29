package mini_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import static mini_project.Admin.book_store;
import static mini_project.Mini_Project.cust_list;

public class Customer {

    static String cust_id;
    String pass;
    static ArrayList<ArrayClass> ar = new ArrayList<ArrayClass>();
    static ArrayList<Cart> cust_cart_list = new ArrayList<Cart>();
    static ArrayList<Bill> cust_bill = new ArrayList<Bill>();
    static ArrayList<Address> cust_address = new ArrayList<Address>();
    public static ArrayList<Favourites> interest1 = new ArrayList<Favourites>();
    static ArrayList<Customer_Fav> cust_interest = new ArrayList<Customer_Fav>();
    //ArrayList<Integer> del = new ArrayList<Integer>();

    String id, author = "", title = "";
    int quan = 0;
    int price = 0;
    int book_quantity = 0, flag;

    float total;

    int ship1;
    String ship2;

    String street, city, state, country;
    long pc;

    String payment;

    private float tax=(float)0.18;
    
    void customer(String id, String pass)
    {
        this.id=id;
        this.pass=pass;
    }
    void create_cust() throws IOException {
        String id;
        int counter = 0;
        String pass1, pass2;
        System.out.print("Enter new id : ");
        id = Mini_Project.input.readLine();
        for (Customer obj : cust_list) {
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
                System.out.println("Enter your HOME ADDRESS Details : ");
                //create_address();
            } else {
                System.out.println("Passwords do not match.");
                counter = 1;
            }
        } while (counter == 1);
        System.out.println("Your new id is successfully created.");
        Mini_Project.c_id_u=id;
        cust_id = id;
        pass = pass1;
       
        /*ar.add(new ArrayClass(id,cust_bill));
        ar.add(new ArrayClass(id,cust_cart_list));
        ar.add(new ArrayClass(id,cust_interest));*/

    }

    void create_address() throws IOException {
        boolean con_in=true;
        String a_id=Mini_Project.c_id_u;
        //a_id= new String(cust_id);
        System.out.println("Id->"+Mini_Project.c_id_u);

        System.out.print("Street -> ");
        street = Mini_Project.input.readLine();
        System.out.print("City -> ");
        city = Mini_Project.input.readLine();
        System.out.print("State -> ");
        state = Mini_Project.input.readLine();
        System.out.print("Country -> ");
        country = Mini_Project.input.readLine();
        do {
            try {
        System.out.print("Postal Code -> ");
        pc = Integer.parseInt(Mini_Project.input.readLine());
        con_in=false;
            }   catch(NumberFormatException ex)    {
                System.out.println("Enter a numerical data.");
                //String fake=Mini_Project.input.readLine();
            }
        } while (con_in);

        Address a = new Address(a_id,street, city, state, country, pc);
        cust_address.add(a);
        System.out.println("Information Addded Successfully");
    }

    void buy_book() throws IOException {
        flag = 0;
        System.out.print("Enter the Book ID you want to BUY : ");
        id = Mini_Project.input.readLine();
        for (Library obj : book_store) {
            if (id.equalsIgnoreCase(obj.get_bookid())) {
                price = obj.get_price();
                book_quantity = obj.get_quantity();
                title = obj.get_title();
                author = obj.get_author();
                flag = 1;
            }
        }

        if (flag == 1) {
            System.out.print("How many books do you want to buy [Available Books = " + book_quantity + " ] : ");
            quan = Integer.parseInt(Mini_Project.input.readLine());
            if(quan>book_quantity)  {
                System.out.println("Sorry , We dont have so many books.");
                return;
            }
            Customer_cart temp = new Customer_cart(Mini_Project.c_id_u,id, title, author, quan, price);
            cust_cart_list.add(temp);
            System.out.println("Succesfully added to your cart.");
        } else {
            System.out.println("Wrong BOOK ID.");
        }

    }

    int check_cart() {
        int temp=0;
        for(Cart obj: cust_cart_list)   {
            if(obj.get_c_id().equals(Mini_Project.c_id_u)) {
                temp=1;
            }
        }
        if(temp==1) {
        System.out.println("Book Id\tTitle\tAuthor\tPrice\tQuant\tTotal Price");
        for (Cart obj : cust_cart_list) {
            if(obj.get_c_id().equals(Mini_Project.c_id_u))
            System.out.println(obj.get_bookid() + "\t" + obj.get_title() + "\t" + obj.get_author() + "\t" + obj.get_price() + "\t" + obj.get_quantity() + "\t" + (obj.get_price() * obj.get_quantity()));
        }
    }
        return temp;
    }

    void generate_bill() throws IOException {
        total = 0;
        int counter=0,flag=0;
        float temp;
        
        for (Cart obj : cust_cart_list) {

            for (Library obj1 : book_store) {

                if ((obj.get_bookid().equals(obj1.get_bookid()))&&obj.get_c_id().equals(Mini_Project.c_id_u)) {
                   // System.out.println("Matched");
                    if (obj1.get_quantity() > obj.get_quantity()) {
                        temp=(obj.get_price() * obj.get_quantity())*tax;
                        total+=total+temp+(obj.get_price() * obj.get_quantity());
                        Bill b = new Bill(Mini_Project.c_id_u,obj.get_title(), obj.get_author(), obj.get_bookid(), obj.get_price(), obj.get_quantity(), tax);
                        cust_bill.add(b);
                        System.out.println("here");
                        b.display_bill();
                        if(cust_bill.isEmpty()==true)
                            System.out.println("Error");
                        obj1.set_quantity(obj1.get_quantity() - obj.get_quantity());
                        for(Favourites obj2 : interest1) {
                            if(obj1.get_genre().equalsIgnoreCase(obj2.get_name())) {
                                obj2.set_count(obj2.get_count()+1);
                            }
                        }
                        for(Customer_Fav obj3 : cust_interest)    {
                            if(obj3.get_genre().equals(obj1.get_genre())&&obj3.get_cid().equals(Mini_Project.c_id_u)) {
                                flag++;
                            }
                        }
                        if(flag==0)
                            cust_interest.add(new Customer_Fav(Mini_Project.c_id_u,obj1.get_genre()));
                            //break;
                    } else {
                        System.out.println("Book ID -> " + obj.get_bookid() + " could not be bought because of less stock. \n Error is deeply regretted.");
                    }
                }
            }
        }
       
        /*for(Cart obj:cust_cart_list)    {
            if(obj.get_c_id().equals(Mini_Project.c_id_u))  {
                del.add(counter);
                System.out.println(counter);
            }
            counter++;
        }
        
        if(del.isEmpty()==false)    {
            for(Integer obj:del)    {
                cust_cart_list.remove(obj);
                System.out.println(obj+" deleted");
            }
        }*/
        
        System.out.println("The total price of your bill is : " + total);
        generate_address();
    }

    void generate_address() throws IOException {
        int ship, index = 1, flag = 0;
        int opt;
        String ad_id,ad_pass;
        /*
        System.out.print("Enter your id : ");
        ad_id = Mini_Project.input.readLine();
        System.out.print("Enter your password : ");
        ad_pass = Mini_Project.input.readLine();
        for (Customer obj : cust_list) {
            if (obj.cust_id.equals(ad_id)) {
                flag = 1;
                if (obj.pass.equals(ad_pass)) {
                } else {
                    System.out.println("Username and password do not match. ");
                    return;
                }
            }
        }
        */
        do {
            try {
            System.out.print("Give your Shipping Address : \n1. Want to use your previous saved address? \n0. Enter a new Address : ");
            opt = Integer.parseInt(Mini_Project.input.readLine());
            switch (opt) {
                case 1:
                    do {
                        try {
                            for (Address obj : cust_address) {
                                if (obj.get_cid().equals(Mini_Project.c_id_u)) {
                                    System.out.print("Address Number " + index + " -> ");
                                    obj.display();
                                    index++;
                                }
                            }
                            System.out.print("Enter the address you want to ship these orders. ");
                            ship = Integer.parseInt(Mini_Project.input.readLine());

                        index = 1;
                        //Address obj1;
                        for (Address obj : cust_address) {
                            if(obj.get_cid().equals(Mini_Project.c_id_u))   {
                                if(index==ship)
                                {
                                    street = obj.get_street();
                                    city = obj.get_city();
                                    state = obj.get_state();
                                    country = obj.get_country();
                                    pc = obj.get_pc();
                                    flag = 1;
                                    break;
                                }
                                else 
                                {
                                    index++;
                                }
                            
                            }
                        }
                            if (flag == 0) {
                                System.out.println("Please choose correctly.");
                            }
                        } catch (NullPointerException ex) {
                            System.out.print("Enter your id : ");
                          //  Mini_Project.c_id_u = Mini_Project.input.readLine();
                            //generate_address();
                            flag = 0;
                        }
                    } while (flag == 0);
                    break;

                case 0:
                    System.out.println("Enter the new address : ");
                    create_address();
                    break;
                default:
                    System.out.println("Please choose correctly.");
            }
        } catch(NumberFormatException ex)   {
        System.out.println(ex+" Please enter correct input");
        }
        }while (flag == 0);
        

        shipping();
        create_payment();
        return;
    }

    void shipping() throws IOException {
        int opt;
        boolean con_in=true;

        System.out.print("SHIPPING\nEnter when you want your books :");
        do {
           try  {
               System.out.println("1. One day Delivery [Rs. 200] 2. Two-Three Delivery [Rs. 100] 3. Normal Delivey [Rs. 0] ");
               opt=Integer.parseInt(Mini_Project.input.readLine());
               switch(opt)  {
                   case 1:
                       ship1=200;
                       ship2="One Day";
                       con_in=false;
                       break;
                   case 2:
                       ship1=100;
                       ship2="Two-Three Day";
                       con_in=false;
                       break;
                   case 3:
                       ship1=0;
                       ship2="One Week";
                       con_in=false;
                       break;
                   default: System.out.println("Enter a correct option.");
               }
           } catch(NumberFormatException ex)   {
               System.out.println("Enter a numerical value");
               //String fake=Mini_Project.input.readLine();
           }
        } while (con_in);

    }

    void create_payment() throws IOException {
        int opt;
        boolean con_in = true;

        do {
            try {
                System.out.println("Enter your method of payment \n1. COD 2. NET BANKING 3. DEBIT CARD ");
                opt = Integer.parseInt(Mini_Project.input.readLine());
                switch (opt) {
                    case 1:
                        payment = "COD";
                        con_in = false;
                        break;
                    case 2:
                        payment = "NET BANKING";
                        con_in = false;
                        break;
                    case 3:
                        payment = "DEBIT CARD";
                        con_in = false;
                        break;
                    default:
                        System.out.println("Enter the correct option.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Enter the correct type of input. ");
            }

        } while (con_in);
        System.out.println("Thank you for all your information.");

        final_bill();
        return;
    }

    void final_bill() {
        System.out.println("Check your final order.\nYour order is of total Rs." + (total + ship1)+ " .");
        System.out.println("The shipping address is " + street + ", " + city + ", " + state + ", " + country + ", " + pc + ".");
        System.out.println("Your order will be deivered within "+ship2+".");
        System.out.println("Your mode of Payment is " + payment);
        System.out.println("Thank you for your ORDER\nHope to serve you like this FOREVER.");
        
    }

    void update_quan_cart() throws IOException {
        int new_quan, max_quan=0, flag = 0;
        System.out.print("Enter the Book Id : ");
        String id = Mini_Project.input.readLine();
        for(Library obj : book_store)   {
            if(obj.get_bookid().equals(id)) {
                max_quan=obj.get_quantity();
            }
        }
        for (Cart obj : cust_cart_list) {
            if (id.equals(obj.get_bookid())&&obj.get_c_id().equals(Mini_Project.c_id_u)) {
                System.out.print("Enter the new quantity [Previous Quantity " + obj.get_quantity() + " ] : ");
                new_quan = Integer.parseInt(Mini_Project.input.readLine());
                if(new_quan>max_quan)   {
                    System.out.println("Sorry, We dont have so many stocks.");
                    return;
                }
                obj.set_quantity(new_quan);
                System.out.println("Updation Successful");
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Please enter the book id carefully. ");
        }
    }

    void cust_menu() throws IOException {
        int opt=0, opt_c=0;
        boolean con_in=true;
        //String fake;

        do {
            try {
            System.out.println("1. Buy a new Book 2. Open Cart 3 Previous Bill 4. Log out");
            opt = Integer.parseInt(Mini_Project.input.readLine());
            switch (opt) {
                case 1:
                    //boolean con_in2=true;
                   // do {
                        try {
                        System.out.println("1. See All Books 2. Search 3. Recommended 4. Back : ");
                        opt_c = Integer.parseInt(Mini_Project.input.readLine());
                        switch (opt_c) {
                            case 1:
                                System.out.println("Here is the available list of all BOOKS : ");
                                System.out.println("Book Id\tTitle\tAuthor\tPrice");
                                new Admin().display_books();
                                buy_book();
                                //con_in2=false;
                                break;
                            case 2:
                                new Search().search();
                                //con_in2=false;
                                break;
                            case 3:
                               if (interest1.isEmpty() == false) {
                                    recommendation();
                                } else {
                                    System.out.println("Sorry Nothing available.");
                                }
                                //con_in2=false;*/
                                break;
                            case 4 :
                                //con_in2=false;
                                break;
                            default:
                                System.out.print("Choose your option correctly.");
                        }
                    }
                        catch (NumberFormatException ex) {
                            System.out.println("Enter a numerical value.");
                            //fake = Mini_Project.input.readLine();
                        }
                   // }   while (opt_c!=4);
                    //con_in=false;
                    break;
                case 2:
                    //boolean con_in1 = true;
                    //do {
                    int temp=0;
                    int del=0,detect=0;
                        try {
                            
                            if (cust_cart_list.isEmpty() == false) {
                                temp=check_cart();
                                
                                if(temp==1) {
                                System.out.println("1. Buy all books 2. Delete a book 3. Update the quantity of a book 4. Back : ");
                                opt_c = Integer.parseInt(Mini_Project.input.readLine());
                                switch (opt_c) {
                                    case 1:
                                        generate_bill();/*
                                        while(detect==0)
                                        {
                                            del=0;
                                        for(Cart obj : cust_cart_list)
                                        {
                                            if(obj.get_c_id().equals(Mini_Project.c_id_u))
                                            {
                                                detect=0;
                                                break;
                                            }
                                            else
                                            {
                                                detect=1;
                                            }
                                            del++;
                                        }
                                        if(detect==0)
                                        {*/
                                        //cust_cart_list.remove(del);
                                        //}
                                        //}
                                        //con_in1=false;
                                        break;
                                    case 2:
                                        deletion();
                                        System.out.println("");
                                        //con_in1=false;
                                        break;
                                    case 3:
                                        update_quan_cart();
                                        //con_in1=false;
                                        break;
                                    case 4:
                                        //con_in1=false;
                                        break;
                                    default:
                                        System.out.println("");
                                }
                            }
                            }else {
                                System.out.println("Your Shopping Cart is Empty.");
                                //con_in1=false;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter a numerical value.");
                            //fake = Mini_Project.input.readLine();
                        }
                   // } while (opt!=4);
                    //con_in=false;
                    break;
                case 3:
                    if (cust_bill.isEmpty() == false) {
                        System.out.println("Book Id\tTitle\tAuhtor\tPrice\tQuantity");
                        for (Bill obj : cust_bill) {
                            if(obj.get_cid().equals(Mini_Project.c_id_u))
                            obj.display_bill();
                        }
                    } else {
                        System.out.println("No records found.");
                    }
                    //con_in=false;
                    break;
                case 4:
                    //con_in=false;
                    break;
                default:
                    System.out.println("Enter a valid option. ");

            }
        }
        catch(NumberFormatException ex)    {
            System.out.println("Enter appropriate type answer.");
           // fake=Mini_Project.input.readLine();
        }
        } while (opt!=4);
    }

    void deletion() throws IOException {
        String b_id;
        int ind = 0;

        System.out.print("Enter the Book Id : ");
        b_id = Mini_Project.input.readLine();

        for (Cart obj : cust_cart_list) {
            ind++;
            if (b_id.equals(obj)) {
                break;
            }
        }

        cust_cart_list.remove(ind - 1);
    }

    public int get_top()  {
        int max=0;
        for(Favourites obj:Customer.interest1)  {
            if(max<obj.get_count())
                max=obj.get_count();
        }
        return max;
    }


    void recommendation() throws IOException {
        int flag = 0;

        int max;
        max = get_top();

       /* if(interest1.isEmpty()==false)  {
            System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tGenre\tQuantity\tPrice");
            for(Favourites obj:interest1)   {
                if(obj.get_count()==max)    {
                    for(Library obj1 : book_store)  {
                        if(obj.get_name().equalsIgnoreCase(obj1.get_genre())) {
                            obj1.display();
                            flag=1;
                        }
                    }
                }
            }
        }
        else
            System.out.println("Nothing is here.");*/
       if(interest1.isEmpty()==false)   {
           //System.out.println("YESS");
           new Admin().display_fav();
       }
       else
           System.out.println("NoO");
       //System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tGenre\tQuantity\tPrice");
       
        for (Customer_Fav obj : cust_interest) {
            for (Library obj1 : book_store) {
                if (obj.get_genre().equals(obj1.get_genre())&&obj.get_cid().equals(Mini_Project.c_id_u)) {
                    if(flag==0) {
                    System.out.println("\nBooks found on your interest.");
                }
                    obj1.display();
                    flag = 1;
                }
            }
        }

        if (flag == 1) {
            buy_book();
        } else {
            System.out.println("Sorry , No books found on your interest.");
        }
    }

}

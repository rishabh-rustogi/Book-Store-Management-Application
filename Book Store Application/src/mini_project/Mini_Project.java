package mini_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Mini_Project {

    //static Scanner input = new Scanner(System.in);
    static ArrayList<Customer> cust_list = new ArrayList<Customer>();
    static ArrayList<Admin> admin_list = new ArrayList<Admin>();
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static String c_id_u="";
    static String u_id_u;

    public static void main(String[] args) throws IOException {

        int opt = 0, opt_c = 0, flag;
        String pass_u;
        boolean con_in = true;

        System.out.println("Welcome to Ayush & Rishabh Book Store.");
        do {
            try {
                System.out.println("1. Admin 2. Customer 3. Exit ");
                opt = Integer.parseInt(input.readLine());
                switch (opt) {
                    case 1:
                        boolean con_in1 = true;
                        do {
                            try {
                                System.out.println("1. Admin Sign Up 2. Admin Log in 3.Back ");
                                System.out.print("Enter option : ");
                                opt_c = Integer.parseInt(input.readLine());
                                System.out.println(opt_c);
                                Admin temp = new Admin();
                                switch (opt_c) {
                                    case 1:
                                        temp.create_admin();
                                        admin_list.add(temp);
                                        con_in1 = false;
                                        break;
                                    case 2:
                                        flag = 0;
                                        System.out.print("Enter your admin id : ");
                                        u_id_u = input.readLine();
                                        System.out.print("Enter your Password : ");
                                        pass_u = input.readLine();
                                        try {
                                            for (Admin obj : admin_list) {

                                                if (obj.cust_id.equals(u_id_u)) {
                                                    flag = 1;
                                                    if (obj.pass.equals(pass_u)) {
                                                        System.out.println("Admin ID found and password matched.");
                                                        System.out.println("Welcome to the ADMIN MENU Page : ");
                                                        temp.admin_menu();
                                                    } else {
                                                        System.out.println("Username found but password do not match. ");
                                                    }
                                                }
                                            }
                                        } catch (NullPointerException ex) {
                                            opt_c = 3;
                                        }
                                        if (flag == 0) {
                                            System.out.println("Admin ID not found");
                                        }
                                        con_in1 = false;
                                        break;
                                    case 3:
                                        con_in1 = false;
                                        break;
                                    default:
                                        System.out.println("Enter the option carefully");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("Enter a numerical value.");
                                //String fake = input.readLine();
                            }
                        } while (con_in1 && (opt_c != 3));
                        con_in = false;
                        break;
                    case 2:
                        boolean con_in2 = true;
                        do {
                            try {
                                System.out.println("1. Customer Sign Up 2. Customer Log in  3. Back ");
                                System.out.print("Enter option : ");
                                opt_c = Integer.parseInt(Mini_Project.input.readLine());
                                flag = 0;
                                Customer temp1 = new Customer();

                                switch (opt_c) {
                                    case 1:
                                        temp1.create_cust();
                                        temp1.create_address();
                                        cust_list.add(temp1);
                                        
                                        con_in2 = false;
                                        break;
                                    case 2:
                                        System.out.print("Enter your id : ");
                                        c_id_u = input.readLine();
                                        System.out.print("Enter your Password : ");
                                        pass_u = input.readLine();
                                        for (Customer obj : cust_list) {
                                            try {
                                            if (obj.cust_id.equals(c_id_u)) {
                                                flag = 1;
                                                if (obj.pass.equals(pass_u)) {
                                                    System.out.println("User found and password matched.");
                                                    System.out.println("Welcome to the USER MENU Page : ");
                                                    temp1.cust_menu();
                                                } else {
                                                    System.out.println("Username found but password do not match. ");
                                                }
                                            }
                                            }   catch(NullPointerException ex)  {
                                                opt_c=3;
                                            }
                                        }
                                        if (flag == 0) {
                                            System.out.println("User not found");
                                        }
                                        con_in2 = false;
                                        break;
                                    case 3:
                                        con_in2 = false;
                                        break;
                                    default:
                                        System.out.println("Choose your option coorectly.");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("Enter a numerical value.");
                                //String fake = input.readLine();
                            }
                        } while (con_in2);
                        con_in = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter the option carefully.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Enter a numerical value.");
                // String fake = input.readLine();
            }
        } while (opt != 3);
    }
}

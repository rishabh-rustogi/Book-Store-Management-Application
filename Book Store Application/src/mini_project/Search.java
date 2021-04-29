package mini_project;

import java.io.IOException;
import java.util.Scanner;
import static mini_project.Admin.book_store;

public class Search {

    void search() throws IOException {
        int opt, flag;
        String check;
        do {
            flag = 0;
            System.out.print("1. By name 2. By author 3. By genre 4. Back : ");
            opt = Integer.parseInt(Mini_Project.input.readLine());
            switch (opt) {
                case 1:
                    System.out.print("Enter the name of the book : ");
                    check = Mini_Project.input.readLine();
                    System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tGenre\tQuantity\tPrice");
                    for (Library obj : book_store) {
                        if (obj.get_title().contains(check)) {
                            obj.display();
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Not found in our database.");
                        return;
                    }
                    new Customer().buy_book();
                    opt = 4;
                    break;
                case 2:
                    System.out.print("Enter the name of the author : ");
                    check = Mini_Project.input.readLine();
                    System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tGenre\tQuantity\tPrice");
                    for (Library obj : book_store) {
                        if (obj.get_author().contains(check)) {
                            obj.display();
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Not found in our database.");
                        return;
                    }
                    new Customer().buy_book();
                    opt = 4;
                    break;
                case 3:
                    System.out.print("Enter the Genre : ");
                    check = Mini_Project.input.readLine();
                    System.out.println("Book ID\tTitle\tAuthor\tEdition\tPublisher\tGenre\tQuantity\tPrice");
                    for (Library obj : book_store) {
                        if (obj.get_genre().contains(check)) {
                            obj.display();
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Not found in our database.");
                        return;
                    }
                    new Customer().buy_book();
                    opt = 4;
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Enter the options carefully.");
            }
        } while (opt != 4);
    }
}

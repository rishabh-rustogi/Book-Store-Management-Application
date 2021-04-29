/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini_project;

/**
 *
 * @author Ayush
 */
public class LessBook_Exception extends RuntimeException   {
    private int quantity;
    private String book_id,title;
    
    public LessBook_Exception (String book_id,String title,int quantity)   {
        //this.threshold=threshold;
        this.book_id=book_id;
        this.quantity=quantity;
        this.title=title;
        System.out.println(book_id+"\t"+title+"\t"+quantity);
    }
}

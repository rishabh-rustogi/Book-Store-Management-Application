package mini_project;

public class Customer_Fav {
    private String id;
    private String genre;
    
    public Customer_Fav(String id,String genre) {
        this.id=id;
        this.genre=genre;
    }
    
    String get_genre()  {
        return genre;
    }
    
    String get_cid()    {
        return id;
    }
}

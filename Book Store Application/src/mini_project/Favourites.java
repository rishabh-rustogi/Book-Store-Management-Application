package mini_project;

public class Favourites {
    private String name;
    private int count;
    
    public Favourites(String name,int count)  {
        this.name=name;
        this.count=count;
    }
    
    void update_genre(String name) {
        for(Favourites obj : Customer.interest1) {
            if(obj.name.equalsIgnoreCase(name))
                count++;
        }
    }
    
    String get_name()   {
        return name;
    }
    
    int get_count() {
        return count;
    }
    
    void set_count(int x)    {
        this.count=x;
    }

    public void add_genre(String name) {
        
        for(Favourites obj : Customer.interest1) {
            if(name.equalsIgnoreCase(obj.get_name()))   
            {
                return;
            }
        }
        
            Customer.interest1.add(new Favourites(name,1));
        
    }
}

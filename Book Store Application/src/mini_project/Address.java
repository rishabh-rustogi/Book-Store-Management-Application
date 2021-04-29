package mini_project;

public class Address {

    private String Street, City, State, Country, c_id;
    private long PostalCode;

    Address(String c_id,String Street, String City, String State, String Country, long PostalCode) {
        this.City = City;
        this.Country = Country;
        this.PostalCode = PostalCode;
        this.State = State;
        this.Street = Street;
        this.c_id=c_id;
    }

    String get_city() {
        return City;
    }

    String get_country() {
        return Country;
    }

    long get_pc() {
        return PostalCode;
    }

    String get_state() {
        return State;
    }

    String get_street() {
        return Street;
    }
    
    String get_cid()    {
        return c_id;
    }

    void display() {
        System.out.println(get_street() + ", " + get_city() + ", " + get_state() + ", " + get_country() + ", " + get_pc() + ".");
    }
}

package patterns.coffee_machine;

import java.util.UUID;

public class User{
    private final String name;
    private final String id;
//    private final int amount_paid;

    public User(String name, int amount_paid){
        this.name = name;
        this.id = UUID.randomUUID().toString();
//        this.amount_paid = amount_paid;
    }

    public String getName(){
        return name;
    }

    public String getUserId(){
        return id;
    }

//    public int getAmount_paid(){
//        return amount_paid;
//    }
}
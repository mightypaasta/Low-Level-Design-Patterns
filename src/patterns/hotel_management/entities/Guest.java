package patterns.hotel_management.entities;

import java.util.UUID;

public class Guest {
    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    public Guest(String name, String email, String phoneNumber){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId(){ return id; }
    public String getName(){ return id; }
    public String getEmail(){ return email; }
    public String getPhoneNumber(){ return phoneNumber; }
}

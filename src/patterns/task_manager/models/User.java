package patterns.task_manager.models;

import java.util.UUID;

public class User{
    private final String id;
    private final String username;
    private final String email;

    public User(String username, String email){
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
    }

    public String getUserId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }
}
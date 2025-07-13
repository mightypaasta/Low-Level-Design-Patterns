package patterns.stack_overflow;

import java.util.UUID;

class User{
    private final String id;
    private final String name;
    private final String email;
    private int reputation;

    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.reputation = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getReputation() {
        return reputation;
    }

    public void updateReputation(int points) {
        this.reputation += points;
    }
}
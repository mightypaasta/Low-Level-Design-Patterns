package patterns.coffee_machine;

public class Payment {
    private final User user;
    private final int amount_paid;

    public Payment(User user, int amount_paid){
        this.user = user;
        this.amount_paid = amount_paid;
    }

    public User getUser(){
        return user;
    }

    public int getAmount_paid() {
        return amount_paid;
    }
}
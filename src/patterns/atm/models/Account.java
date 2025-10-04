package patterns.atm.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Account {
    private final String accountNumber;
    private final Map<String,Card> cards = new HashMap<>();
    private double balance;

    public Account(String accountNumber, double amount){
        this.accountNumber = accountNumber;
        balance = amount;
    }

    public synchronized boolean withdraw(double amount){
        if(amount>0 && balance>=amount){
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized void deposit(double amount){
        if(amount>0){
            balance += amount;
        }
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public synchronized Double getBalance(){
        return balance;
    }

    public void addCard(Card card){
        cards.put(card.getCardNumber(),card);
    }

}

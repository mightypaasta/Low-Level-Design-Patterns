package patterns.atm.models;

import java.util.HashMap;
import java.util.Map;

/**
 * linkCardToAccount
 * getBalance
 * depositMoney
 * createAccount
 * createCard
 * getCard
 * withdrawMoney
 * authenticate
 * **/

public class BankService {
    private final Map<String,Account> accounts = new HashMap<String,Account>();
    private final Map<String,Card> cards = new HashMap<String,Card>();
    private final Map<Card,Account> cardAccountMap = new HashMap<Card,Account>();

    public BankService(){
        Account account1 = createAccount("1234567890",1000.0);
        Card card1 = createCard("1234-5678-1234","1234");
        linkCardToAccount(card1,account1);

        Account account2 = createAccount("9876543210",500.0);
        Card card2 = createCard("1234-1234-1234","4321");
        linkCardToAccount(card2,account2);
    }

//    private boolean isInvalidCard(Card card){
//        boolean noCard = !cards.containsKey(card.getCardNumber());
//        boolean noAccount = !cardAccountMap.containsKey(card);
//
//        return noCard || noAccount;
//    }

    public boolean authenticate(Card card, String pin){
        return card.checkPin(pin);
    }

    public Card createCard(String cardNumber, String pin){
        Card newCard =  new Card(cardNumber,pin);
        cards.put(cardNumber,newCard);
        return newCard;
    }

    public Account createAccount(String accountNumber, Double amount){
        Account newAccount = new Account(accountNumber,amount);
        accounts.put(accountNumber,newAccount);
        return newAccount;
    }

    public Card getCard(String number){
        return cards.getOrDefault(number,null);
    }

    public Double getBalance(Card card){

//        if(!authenticate(card,pin)){
//            throw new IllegalArgumentException("Incorrect pin");
//        }

        return cardAccountMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, Double amount){
//        if(isInvalidCard(card)){
//            throw new IllegalArgumentException("Invalid card");
//        }

        cardAccountMap.get(card).withdraw(amount);
    }

    public void depositMoney(Card card, Double amount){
//        if(isInvalidCard(card)){
//            throw new IllegalArgumentException("Invalid card.");
//        }

        cardAccountMap.get(card).deposit(amount);
    }

    public void linkCardToAccount(Card card, Account account){
        account.addCard(card);
        cardAccountMap.put(card,account);
    }

}

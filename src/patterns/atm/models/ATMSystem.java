package patterns.atm.models;

import patterns.atm.chainofresponsibility.*;
import patterns.atm.enums.OperationType;
import patterns.atm.states.ATMState;
import patterns.atm.states.IdleState;

import java.util.Map;

public class ATMSystem {
    private static ATMSystem instance;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private final Map<String,Transaction> transactionCounter;
    private ATMState currentState;
    private Card currentCard;

    private ATMSystem(){
        currentState = new IdleState();
        bankService = new BankService();
        DispenserChain c1 = new NoteDispenser100(10);
        DispenserChain c2 = new NoteDispenser50(20);
        DispenserChain c3 = new NoteDispenser20(30);
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        cashDispenser = new CashDispenser(c1);
        transactionCounter = new java.util.HashMap<>();
    }

    public boolean authenticate(String pin){
        return bankService.authenticate(currentCard,pin);
    }

    public Card getCard(String cardNumber){
        return bankService.getCard(cardNumber);
    }

    public void insertCard(String cardNumber){
        currentState.insertCard(this,cardNumber);
    }

    public void enterPin(String pin){
        currentState.enterPIN(this,pin);
    }

    public void performOperation(OperationType type, int... args){
        currentState.performOperation(this,type,args);
    }

    public void changeState(ATMState state){
        currentState = state;
    }

    public Card getCurrentCard(){
        return currentCard;
    }

    public void setCurrentCard(Card card){
        currentCard = card;
    }

    public void withdrawCash(int amount){
        if(!cashDispenser.canDispenseCash(amount)){
            throw new IllegalArgumentException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(currentCard,amount*1.0);
        try{
            cashDispenser.dispenseCash(amount);
        }catch(Exception e){
            System.out.println("Error occurred during cash dispensing. Reverting the balance to original.");
            bankService.depositMoney(currentCard,amount*1.0);
        }
    }

    public void depositCash(double amount){
        bankService.depositMoney(currentCard, amount);
    }

    public static ATMSystem getInstance(){
        if(instance==null){
            instance = new ATMSystem();
        }
        return instance;
    }

    public void checkBalance(){
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is $%.2f%n",balance);
    }

    public double getBalance(){
        return bankService.getBalance(currentCard);
    }

}

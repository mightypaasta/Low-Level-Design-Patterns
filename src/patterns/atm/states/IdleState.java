package patterns.atm.states;

import patterns.atm.enums.OperationType;
import patterns.atm.models.ATMSystem;
import patterns.atm.models.Card;

public class IdleState implements ATMState{

    @Override
    public void insertCard(ATMSystem system, String cardNumber) {
        Card card = system.getCard(cardNumber);
        if(card==null) {
            System.out.println("Error: Card not found.");
            ejectCard(system);
        }else{
            System.out.println("Card inserted successfully.");
            system.setCurrentCard(card);
            system.changeState(new HasCardState());
        }
    }

    @Override
    public void enterPIN(ATMSystem system, String pin) {
        System.out.println("Error: Insert card first before entering pin.");
    }

    @Override
    public void performOperation(ATMSystem system, OperationType type, int... args) {
        System.out.println("Error: Insert card first before performing any operation.");
    }

    @Override
    public void ejectCard(ATMSystem system) {
        System.out.println("Card ejected.");
    }
}

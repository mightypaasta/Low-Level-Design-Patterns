package patterns.atm.states;

import patterns.atm.enums.OperationType;
import patterns.atm.models.ATMSystem;

public class HasCardState implements ATMState{

    @Override
    public void insertCard(ATMSystem system, String cardNumber) {
        System.out.println("Error: Card already inserted.");
    }

    @Override
    public void enterPIN(ATMSystem system, String pin) {
        boolean authenticated = system.authenticate(pin);
        if(authenticated){
            System.out.println("Successfully authenticated.");
            system.changeState(new AuthenticatedState());
        }
        else{
            System.out.println("Error: Incorrect pin.");
            ejectCard(system);
        }
    }

    @Override
    public void performOperation(ATMSystem system, OperationType type, int... args) {
        System.out.println("Error: Cannot perform operations before authentication.");
    }

    @Override
    public void ejectCard(ATMSystem system) {
        System.out.println("Ejecting card");
        system.setCurrentCard(null);
        system.changeState(new IdleState());
    }
}

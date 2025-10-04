package patterns.atm.states;

import patterns.atm.enums.OperationType;
import patterns.atm.models.ATMSystem;
import patterns.atm.models.Card;

public interface ATMState {
    void insertCard(ATMSystem system, String cardNumber);
    void enterPIN(ATMSystem system, String pin);
    void performOperation(ATMSystem system, OperationType type, int... args);
    void ejectCard(ATMSystem system);
}


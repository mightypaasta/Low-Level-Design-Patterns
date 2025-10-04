package patterns.atm.models;

import patterns.atm.enums.OperationType;

public abstract class Transaction {
    private final Card card;
    private final OperationType type;
    private final double amount;

    public Transaction(Card card, OperationType type, double amount){
        this.card = card;
        this.type = type;
        this.amount = amount;
    }
}

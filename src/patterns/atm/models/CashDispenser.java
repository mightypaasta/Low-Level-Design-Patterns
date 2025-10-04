package patterns.atm.models;

import patterns.atm.chainofresponsibility.DispenserChain;

public class CashDispenser {
    private final DispenserChain dispenserChain;

    public CashDispenser(DispenserChain chain){
        dispenserChain = chain;
    }

    // Adding synchronized is redundant as it's already applied in the chain.
    public void dispenseCash(int amount){
        dispenserChain.dispense(amount);
    }

    // Adding synchronized is redundant as it's already applied in the chain.
    public boolean canDispenseCash(int amount){
        if(amount%10!=0)    return false;
        return dispenserChain.canDispense(amount);
    }
}

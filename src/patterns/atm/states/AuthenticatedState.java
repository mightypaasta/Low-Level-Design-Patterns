package patterns.atm.states;

import patterns.atm.enums.OperationType;
import patterns.atm.models.ATMSystem;

public class AuthenticatedState implements ATMState{

    @Override
    public void insertCard(ATMSystem system, String cardNumber) {
        System.out.println("Error: Card already inserted.");
    }

    @Override
    public void enterPIN(ATMSystem system, String pin) {
        System.out.println("Error: Already authenticated.");
    }

    @Override
    public void performOperation(ATMSystem system, OperationType type, int... args) {
        switch (type){
            case CHECK_BALANCE -> {
                system.checkBalance();
            }
            case DEPOSIT_CASH -> {
                if(args.length==0 || args[0]<=0){
                    System.out.println("Invalid deposit amount.");
                }else{
                    int depositAmt = args[0];
                    System.out.println("Processing deposit of "+depositAmt);
                    system.depositCash(depositAmt);
                }
            }
            case WITHDRAW_CASH -> {
                if(args.length==0 || args[0]<=0){
                    System.out.println("Invalid withdraw amount");
                    break;
                }
                int withdrawAmt = args[0];
                double balance = system.getBalance();
                if(withdrawAmt > balance){
                    System.out.println("Insufficient funds.");
                }else{
                    System.out.println("Withdrawing "+withdrawAmt+" funds from the account.");
                    system.withdrawCash(withdrawAmt);
                }
            }
        }
        System.out.println("Transaction complete.");
        ejectCard(system);
    }

    @Override
    public void ejectCard(ATMSystem system) {
        System.out.println("Ejecting card");
        system.setCurrentCard(null);
        system.changeState(new IdleState());
    }
}

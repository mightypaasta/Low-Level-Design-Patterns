package patterns.atm;

import patterns.atm.enums.OperationType;
import patterns.atm.models.ATMSystem;

public class ATMDemo {
    public static void demo(){
        ATMSystem atmSystem = ATMSystem.getInstance();

        atmSystem.insertCard("1234-5678-1234");
        atmSystem.enterPin("1234");
        atmSystem.performOperation(OperationType.CHECK_BALANCE);

        System.out.println();

        atmSystem.insertCard("1234-5678-1234");
        atmSystem.enterPin("1234");
        atmSystem.performOperation(OperationType.DEPOSIT_CASH,1000);

        System.out.println();

        atmSystem.insertCard("1234-5678-1234");
        atmSystem.enterPin("1234");
        atmSystem.performOperation(OperationType.WITHDRAW_CASH,200);

        System.out.println();

        atmSystem.insertCard("1234-5678-1234");
        atmSystem.enterPin("1234");
        atmSystem.performOperation(OperationType.CHECK_BALANCE);

        System.out.println();

        atmSystem.insertCard("1234-5678-1234");
        atmSystem.enterPin("4321");

//        atmSystem.insertCard();
    }

}

package patterns.airline_management_system.entities;

import patterns.airline_management_system.enums.PaymentMethod;
import patterns.airline_management_system.enums.PaymentStatus;

import java.util.UUID;

public class Payment {
    private final String id;
    private final PaymentMethod method;
    private final double amount;
    private PaymentStatus status;

    public Payment(PaymentMethod method, Double amount){
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    void processPayment(){
        if(status == PaymentStatus.CONFIRMED){
            throw new RuntimeException("Payment already processed. ");
        }

        if(method==PaymentMethod.CARD){
            System.out.println("Processed card payment of "+amount);
            status = PaymentStatus.CONFIRMED;
        }else if(method == PaymentMethod.WIRE){
            System.out.println("Processed wire payment of "+amount);
            status = PaymentStatus.CONFIRMED;
        }else{
            System.out.println("Processed cash payment of "+amount);
            status = PaymentStatus.CONFIRMED;
        }
    }
}

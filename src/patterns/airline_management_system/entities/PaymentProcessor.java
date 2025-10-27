package patterns.airline_management_system.entities;

public class PaymentProcessor {
    private static PaymentProcessor instance;

    public static PaymentProcessor getInstance(){
        if(instance==null){
            instance = new PaymentProcessor();
        }
        return instance;
    }

    public void processPayment(Payment payment){
        payment.processPayment();
    }
}

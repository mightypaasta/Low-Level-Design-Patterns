package patterns.hotel_management.entities;


public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processed card payment of "+amount);
        return true;
    }
}

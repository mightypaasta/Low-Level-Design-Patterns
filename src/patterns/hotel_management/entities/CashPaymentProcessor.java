package patterns.hotel_management.entities;

public class CashPaymentProcessor implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processed cash payment of $"+amount);
        return true;
    }
}

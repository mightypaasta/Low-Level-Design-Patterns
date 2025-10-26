package patterns.car_rental.entities;

public class PaypalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processed $"+amount+" by Paypal.");
        return true;
    }
}

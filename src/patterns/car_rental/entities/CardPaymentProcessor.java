package patterns.car_rental.entities;

public class CardPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processed $"+amount+" by card.");
        return true;
    }
}

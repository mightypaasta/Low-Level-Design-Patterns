package patterns.car_rental.entities;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}

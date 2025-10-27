package patterns.hotel_management.entities;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}

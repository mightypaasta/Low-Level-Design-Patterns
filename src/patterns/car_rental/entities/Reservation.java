package patterns.car_rental.entities;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Reservation {
    private final String id;
    private Customer customer;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    
    public Reservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate){
        this.customer = customer;
        this.id = UUID.randomUUID().toString();
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = computeTotalPrice(startDate,endDate);
    }

    public String getId(){ return id; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getEndDate(){ return endDate; }
    public Car getCar(){ return car; }
    public double getTotalPrice(){ return totalPrice; }

    private double computeTotalPrice(LocalDate startDate, LocalDate endDate){
        long daysRented = ChronoUnit.DAYS.between(startDate,endDate)+1;
        return daysRented*car.getRentPerDay();
    }
}

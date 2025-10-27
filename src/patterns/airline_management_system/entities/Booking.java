package patterns.airline_management_system.entities;

import patterns.airline_management_system.enums.BookingStatus;
import patterns.airline_management_system.enums.SeatStatus;

import java.util.UUID;

public class Booking {
    private final String id;
    private final Flight flight;
    private final Passenger passenger;
    private final Seat seat;
    private BookingStatus status;
    private final double price;

    public Booking(Flight flight, Passenger passenger, Seat seat, double price){
        this.id = UUID.randomUUID().toString();
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.status = BookingStatus.PENDING;
        this.price = price;
    }

    public void confirm(){
        status = BookingStatus.CONFIRMED;
    }

    public void cancel(){
        status = BookingStatus.CANCELLED;
    }

    public String getId(){ return id; }
    public Flight getFlight(){ return flight; }
    public Passenger getPassenger(){ return passenger; }
    public Seat getSeat(){ return seat; }
    public BookingStatus getStatus(){ return status; }
}

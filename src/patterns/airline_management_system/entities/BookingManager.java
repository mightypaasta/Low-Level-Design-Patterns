package patterns.airline_management_system.entities;

import java.util.HashMap;
import java.util.Map;

public class BookingManager {
    private static BookingManager instance;
    private final Map<String,Booking> bookings;

    private BookingManager(){
        this.bookings = new HashMap<>();
    }

    public static BookingManager getInstance(){
        if(instance==null){
            instance = new BookingManager();
        }
        return instance;
    }

    public Booking createBooking(Flight flight, Passenger passenger, Seat seat, double price){
        Booking booking = new Booking(flight, passenger, seat, price);
        bookings.put(booking.getId(),booking);
        System.out.println("Booking created with id "+booking.getId()+" for passenger "+passenger.getName());
        return booking;
    }

    public void cancelBooking(String bookingId){
        if(!bookings.containsKey(bookingId)){
            throw new RuntimeException("Booking not found for the id "+bookingId);
        }
        System.out.println("Cancelling booking with id "+bookingId+" for passenger "+bookings.get(bookingId).getPassenger().getName());
        bookings.get(bookingId).cancel();
    }
}

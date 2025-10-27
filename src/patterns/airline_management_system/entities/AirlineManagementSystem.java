package patterns.airline_management_system.entities;

import patterns.airline_management_system.enums.PaymentMethod;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirlineManagementSystem {
    private static AirlineManagementSystem instance;
    private final Map<String,Flight> flights;
    private final Map<String,Aircraft> aircrafts;
    private final Map<String,Passenger> passengers;
    private FlightSearch flightSearch;
    private BookingManager bookingManager;
    private PaymentProcessor paymentProcessor;

    private AirlineManagementSystem(){
        flights = new HashMap<>();
        aircrafts = new HashMap<>();
        passengers = new HashMap<>();
        flightSearch = FlightSearch.getInstance();
        bookingManager = BookingManager.getInstance();
        paymentProcessor = PaymentProcessor.getInstance();
    }

    public static AirlineManagementSystem getInstance(){
        if(instance == null){
            instance = new AirlineManagementSystem();
        }
        return instance;
    }

    public void addAircraft(Aircraft aircraft){
        if(aircrafts.containsKey(aircraft.getTailNumber())){
            throw new IllegalArgumentException("Aircraft already added. ");
        }
        aircrafts.put(aircraft.getTailNumber(), aircraft);
    }

    public void addFlight(Flight flight){
        if(flights.containsKey(flight.getFlightNumber())){
            throw new IllegalArgumentException("Flight is already added. ");
        }
        flightSearch.addFlight(flight);
        flights.put(flight.getFlightNumber(),flight);
    }

    public void addPassenger(Passenger passenger){
        if(passengers. containsKey(passenger.getId())){
            throw new IllegalArgumentException("Passenger is already added. ");
        }
        passengers.put(passenger.getId(), passenger);
    }

    public Booking makeBooking(Flight flight, Passenger passenger, Seat seat, double price, PaymentMethod method){
        Payment payment = new Payment(method, price);
        payment.processPayment();
        return bookingManager.createBooking(flight,passenger,seat,price);
    }

    public void cancelBooking(String bookingId){
        bookingManager.cancelBooking(bookingId);
    }

    public void processPayment(String bookingId, Payment payment){
        paymentProcessor.processPayment(payment);
    }

    public List<Flight> searchFlights(String source, String destination, LocalDate date){
        return flightSearch.searchFlight(source,destination,date);
    }
}

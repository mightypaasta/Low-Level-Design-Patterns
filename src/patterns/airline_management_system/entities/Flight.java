package patterns.airline_management_system.entities;

import patterns.airline_management_system.enums.FlightStatus;
import patterns.airline_management_system.enums.SeatStatus;
import patterns.airline_management_system.enums.SeatType;

import java.time.LocalDateTime;
import java.util.*;

public class Flight {
    private final String flightNumber;
    private final Aircraft aircraft;
    private FlightStatus status;
    private final String source;
    private final String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private final Map<String, Seat> seats;
    private final List<Seat> availableSeats;

    public Flight(Aircraft aircraft, FlightStatus status, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime){
        this.flightNumber = UUID.randomUUID().toString();
        this.aircraft = aircraft;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = getDefaultSeats();
        this.seats = new HashMap<>();
        for(Seat seat: availableSeats){
            seats.put(seat.getSeatNumber(),seat);
        }
    }

    public synchronized void reserveSeat(String seatNumber){
        Seat seat = seats.get(seatNumber);
        availableSeats.remove(seat);
        seat.setStatus(SeatStatus.BOOKED);
    }

    public synchronized void releaseSeat(String seatNumber){
        Seat seat = seats.get(seatNumber);
        seat.setStatus(SeatStatus.AVAILABLE);
        availableSeats.add(seat);
    }

    public synchronized boolean isSeatAvailable(String seatNumber){
        return availableSeats.contains(seats.get(seatNumber));
    }

    public String getFlightNumber(){ return flightNumber; }
    public String getSource(){ return source; }
    public String getDestination(){ return destination; }
    public LocalDateTime getDepartureTime(){ return departureTime; }
    public LocalDateTime getArrivalTime(){ return arrivalTime; }
    public List<Seat> getAvailableSeats(){  return availableSeats; }

    private List<Seat> getDefaultSeats(){
        List<Seat> defaultSeats = new ArrayList<>();
        for(int i=0; i<10; i++){
            Seat seat = null;
            if(i<2){
                seat = new Seat(Integer.toString(i), SeatType.FIRST_CLASS);
            }else if(i<4){
                seat = new Seat(Integer.toString(i), SeatType.BUSINESS);
            }else if(i<6) {
                seat = new Seat(Integer.toString(i), SeatType.PREMIUM_ECONOMY);
            }else{
                seat = new Seat(Integer.toString(i), SeatType.ECONOMY);
            }
            defaultSeats.add(seat);
        }
        return defaultSeats;
    }
}

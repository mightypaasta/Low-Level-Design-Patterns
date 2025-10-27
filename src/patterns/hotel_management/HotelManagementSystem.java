package patterns.hotel_management;


import patterns.hotel_management.entities.*;
import patterns.hotel_management.enums.PaymentType;
import patterns.hotel_management.enums.ReservationStatus;
import patterns.hotel_management.enums.RoomStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private final Map<String, Guest> guests;
    private final Map<String, Room> rooms;
    private final Map<String, Reservation> reservations;

    public HotelManagementSystem(){
        guests = new HashMap<>();
        rooms = new HashMap<>();
        reservations = new HashMap<>();
    }

    public static synchronized HotelManagementSystem getInstance(){
        if(instance == null){
            instance = new HotelManagementSystem();
        }
        return instance;
    }

    public void addGuest(Guest guest){
        guests.put(guest.getId(),guest);
    }

    public Guest getGuest(String id){
        return guests.get(id);
    }

    public void addRoom(Room room){
        rooms.put(room.getId(),room);
    }

    public Room getRoom(Room room){
        return rooms.get(room.getId());
    }

    private void addReservation(Reservation reservation){
        reservations.put(reservation.getId(),reservation);
    }

    public synchronized Reservation book(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        if(room.getStatus()==RoomStatus.BOOKED){
            throw new RuntimeException("Room is already booked.");
        }
        Reservation reservation = new Reservation(guest,room,checkInDate,checkOutDate);
        reservations.put(reservation.getId(),reservation);
        return reservation;
    }

    public synchronized boolean confirmBooking(String reservationId, PaymentType type){
        if(!reservations.containsKey(reservationId)){
            throw new IllegalArgumentException("Reservation not found.");
        }

        Reservation reservation = reservations.get(reservationId);

        if(reservation.getStatus()==ReservationStatus.CONFIRMED && reservation.getRoom().getStatus()==RoomStatus.BOOKED){
            throw new RuntimeException("Room is already booked.");
        }

        long bookedDays = ChronoUnit.DAYS.between(reservation.getCheckInDate(),reservation.getCheckOutDate());
        double totalPrice = bookedDays*reservation.getRoom().getPricePerNight();
        if(type== PaymentType.CARD){
            PaymentProcessor card = new CardPaymentProcessor();
            card.processPayment(totalPrice);
            reservation.getRoom().book();
            return true;
        }else if(type == PaymentType.CASH){
            PaymentProcessor cash = new CardPaymentProcessor();
            cash.processPayment(totalPrice);
            reservation.getRoom().book();
            return true;
        }else{
            throw new IllegalArgumentException("Invalid payment type.");
        }

    }

    public synchronized void cancelReservation(String reservationId){
        if(!reservations.containsKey(reservationId)){
            throw new IllegalArgumentException("Reservation not found.");
        }
        Reservation reservation = reservations.get(reservationId);
        reservation.cancel();
    }

    public synchronized void checkIn(String reservationId){
        if(!reservations.containsKey(reservationId)){
            throw new IllegalArgumentException("Reservation not found.");
        }

        Reservation reservation = reservations.get(reservationId);
        if(reservation.getStatus()== ReservationStatus.CONFIRMED && reservation.getRoom().getStatus()== RoomStatus.BOOKED){
            reservation.getRoom().checkIn();
        }else{
            throw new IllegalArgumentException("Reservation is not confirmed.");
        }
    }

    public synchronized void checkOut(String reservationId){
        if(!reservations.containsKey(reservationId)){
            throw new IllegalArgumentException("Reservation not found.");
        }

        Reservation reservation = reservations.get(reservationId);
        if(reservation.getStatus() == ReservationStatus.CONFIRMED && reservation.getRoom().getStatus()==RoomStatus.OCCUPIED){
            reservation.getRoom().checkOut();
        }else{
            throw new IllegalArgumentException(reservation.getStatus()==ReservationStatus.CONFIRMED ? "Room is not occupied ": "Reservation is not confirmed.");
        }
    }

}

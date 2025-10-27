package patterns.hotel_management.entities;

import patterns.hotel_management.enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {
    private final String id;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private ReservationStatus status;

    public Reservation(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        this.id = java.util.UUID.randomUUID().toString();
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED;
    }

    public synchronized void cancel(){
        if(status == ReservationStatus.CONFIRMED){
            status = ReservationStatus.CANCELLED;
            room.cancel();
            System.out.println("Reservation id: "+id+" is cancelled.");
        }else{
            throw new IllegalStateException("Only booked reservations can be cancelled.");
        }
    }

    public String getId(){ return id; }
    public Guest getGuest(){ return guest; }
    public Room getRoom(){ return room; }
    public LocalDate getCheckInDate(){ return checkInDate; }
    public LocalDate getCheckOutDate(){ return checkOutDate; }
    public ReservationStatus getStatus(){ return status; }
}

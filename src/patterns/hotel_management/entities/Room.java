package patterns.hotel_management.entities;

import patterns.hotel_management.enums.RoomStatus;
import patterns.hotel_management.enums.RoomType;

public class Room {
    private final String id;
    private final RoomType type;
    private RoomStatus status;
    private final double pricePerNight;

    public Room(RoomType type, double pricePerNight){
        this.id = java.util.UUID.randomUUID().toString();
        this.type = type;
        this.status = RoomStatus.AVAILABLE;
        this.pricePerNight = pricePerNight;
    }

    public synchronized void book(){
        if(status == RoomStatus.AVAILABLE){
            status = RoomStatus.BOOKED;
            System.out.println("Room " + id + " is booked.");
        }else{
            throw new RuntimeException("Room is not available for booking.");
        }
    }

    public synchronized void cancel(){
        if(status == RoomStatus.BOOKED){
            status = RoomStatus.AVAILABLE;
            System.out.println("Room " + id + " booking is cancelled.");
        }else{
            throw new RuntimeException("Room is not booked before cancel.");
        }
    }

    public synchronized void checkIn(){
        if(status == RoomStatus.BOOKED){
            status = RoomStatus.OCCUPIED;
            System.out.println("Room " + id + " is checked in.");
        }else{
            throw new RuntimeException("Room must be booked before check-in.");
        }
    }

    public synchronized void checkOut(){
        if(status == RoomStatus.OCCUPIED){
            status = RoomStatus.AVAILABLE;
            System.out.println("Room " + id + " is checked out.");
        }else{
            throw new RuntimeException("Room must be occupied before check-out.");
        }
    }

    public String getId(){ return id; }
    public RoomType getType(){ return type; }
    public RoomStatus getStatus(){ return status; }
    public double getPricePerNight(){ return pricePerNight; }
}

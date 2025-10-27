package patterns.airline_management_system.entities;

import patterns.airline_management_system.enums.SeatStatus;
import patterns.airline_management_system.enums.SeatType;

public class Seat {
    private final String seatNumber;
    private final SeatType type;
    private SeatStatus status;

    public Seat(String seatNumber, SeatType type){
        this.seatNumber = seatNumber;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber(){ return seatNumber; }
    public SeatType getType(){ return type; }
    public SeatStatus getStatus(){ return status; }

    public void setStatus(SeatStatus status){
        this.status = status;
    }
}

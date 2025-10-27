package patterns.hotel_management;

import patterns.hotel_management.entities.Guest;
import patterns.hotel_management.entities.Reservation;
import patterns.hotel_management.entities.Room;
import patterns.hotel_management.enums.PaymentType;
import patterns.hotel_management.enums.RoomType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HotelManagementSystemDemo {
    public static void run(){
        HotelManagementSystem system = HotelManagementSystem.getInstance();

        Guest Bob = new Guest("Bob","bob@gmail.com","1234567890");
        system.addGuest(Bob);
        Room room101 = new Room( RoomType.SINGLE,30.0);
        Room room201 = new Room( RoomType.DOUBLE,50.0);
        Room room301 = new Room( RoomType.DELUX,80.0);
        Room room400 = new Room( RoomType.SUITE,120.0);
        system.addRoom(room101);
        system.addRoom(room201);
        system.addRoom(room301);
        system.addRoom(room400);

        Reservation bobReservation = system.book(Bob,room201, LocalDate.now().plusDays(1),LocalDate.now().plusDays(5));

        try {
            system.confirmBooking(bobReservation.getId(), PaymentType.CARD);
        } catch (Exception e) {
            System.out.println("Error occurred during booking confirmation: " + e.getMessage());
        }

        try {
            system.confirmBooking(bobReservation.getId(), PaymentType.CARD);
        } catch (Exception e) {
            System.out.println("Error occurred during booking confirmation: " + e.getMessage());
        }

        Guest Alice = new Guest("Alice","alice@gmail.com","1223456789");


        try{
            Reservation aliceReservation = system.book(Alice, room201, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3));
            system.confirmBooking(aliceReservation.getId(),PaymentType.CASH);
        }catch (Exception e){
            System.out.println("Error occurred during booking confirmation: "+e.getMessage());
        }
    }
}

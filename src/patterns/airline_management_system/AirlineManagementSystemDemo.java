package patterns.airline_management_system;

import patterns.airline_management_system.entities.*;
import patterns.airline_management_system.enums.FlightStatus;
import patterns.airline_management_system.enums.PaymentMethod;

import java.time.LocalDateTime;

public class AirlineManagementSystemDemo {
    public static void demo(){
        AirlineManagementSystem system = AirlineManagementSystem.getInstance();

        Passenger Bob = new Passenger("Bob","bob@gmail.com");
        Passenger Alice = new Passenger("Alice","alice@gmail.com");

        Aircraft aircraft1 = new Aircraft("N12345","Boeing 737",180);
        Aircraft aircraft2 = new Aircraft("N54321","Airbus A320",150);

        Flight flight1 = new Flight(aircraft1, FlightStatus.ON_TIME,"New Delhi","San Fransisco", LocalDateTime.now().plusHours(14),LocalDateTime.now().plusHours(26));
        Flight flight2 = new Flight(aircraft2, FlightStatus.DELAYED,"New York","London", LocalDateTime.now().plusHours(5),LocalDateTime.now().plusHours(13));

        system.addPassenger(Bob);
        system.addPassenger(Alice);

        system.addFlight(flight1);
        system.addFlight(flight2);

        Booking booking1 = system.makeBooking(flight1,Bob,flight1.getAvailableSeats().get(0),500.0, PaymentMethod.CARD);
        Booking booking2 = system.makeBooking(flight2,Alice,flight2.getAvailableSeats().get(1),300.0, PaymentMethod.CASH);

        system.cancelBooking(booking2.getId());
    }
}

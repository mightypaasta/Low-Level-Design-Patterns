package patterns.airline_management_system.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightSearch {
    private static FlightSearch instance;
    private final List<Flight> flights;

    private FlightSearch(){
        this.flights = new ArrayList<>();
    }

    public static FlightSearch getInstance(){
        if(instance==null){
            instance = new FlightSearch();
        }
        return instance;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public List<Flight> searchFlight(String source, String destination, LocalDate date){
        List<Flight> result = new ArrayList<>();
        for(Flight flight: flights){
            boolean sameLocation = flight.getSource().equals(source) && flight.getDestination().equals(destination);
            boolean sameDate = flight.getDepartureTime().toLocalDate().equals(date);
            if(sameLocation && sameDate){
                result.add(flight);
            }
        }
        return result;
    }
}

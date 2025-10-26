package patterns.car_rental.filters;

import patterns.car_rental.entities.Car;
import patterns.car_rental.entities.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateCriteria implements CarSearchCriteria<Reservation>{
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<Car> cars;

    public DateCriteria(LocalDate startDate, LocalDate endDate, List<Car> cars){
        this.startDate = startDate;
        this.endDate = endDate;
        this.cars = cars;
    }

    @Override
    public List<Car> meetCriteria(List<Reservation> reservations) {
        List<Reservation>  reservationList = reservations.stream().filter(reservation -> checkStartDate(reservation) || checkEndDate(reservation)).toList();
        List<Car> reservedCars = reservationList.stream().map(Reservation::getCar).toList();
        return cars.stream().filter(car-> !reservedCars.contains(car)).toList();    // filter out reserved cars.
    }

    // If the reservation is made before the search dates.
    private boolean checkStartDate(Reservation reservation){
        return reservation.getStartDate().isBefore(startDate) && reservation.getEndDate().isBefore(startDate);
    }

    // If the reservation is made after the search dates.
    private boolean checkEndDate(Reservation reservation){
        return reservation.getStartDate().isAfter(endDate) && reservation.getEndDate().isAfter(endDate);
    }

}

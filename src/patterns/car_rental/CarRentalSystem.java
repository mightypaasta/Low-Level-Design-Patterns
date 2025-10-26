package patterns.car_rental;

import patterns.car_rental.entities.*;
import patterns.car_rental.enums.PaymentType;
import patterns.car_rental.filters.CarSearchCriteria;
import patterns.car_rental.filters.MakerCriteria;
import patterns.car_rental.filters.ModelCriteria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRentalSystem {
    private static CarRentalSystem instance;
    private final List<Car> cars;
    private final List<Reservation> reservations;
    private PaymentProcessor paymentProcessor;

    public CarRentalSystem() {
        this.cars = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public static synchronized CarRentalSystem getInstance(){
        if(instance==null){
            instance = new CarRentalSystem();
        }
        return instance;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void removeCar(String licenseNumber){
        cars.removeIf(car -> car.getLicensePlate().equals(licenseNumber));
    }

    public synchronized Reservation makeReservation(Car car, Customer customer, LocalDate startDate, LocalDate endDate){
        if(isCarAvailable(car,startDate,endDate)){
            return new Reservation(customer, car, startDate, endDate);
        }
        return null;
    }

    public synchronized boolean processPayment(Reservation reservation, PaymentType paymentType){
        PaymentProcessor processor = getPaymentProcessor(paymentType);
        double totalCost = reservation.getTotalPrice();
        if(processor.processPayment(totalCost)){
            reservations.add(reservation);
//            System.out.println("Payment successful, reservation confirmed.");
            return true;
        }else{
//            System.out.println("Payment failed, reservation unsuccessful.");
            return false;
        }
    }

    public synchronized Optional<Reservation> getReservation(String reservationId){
        return reservations.stream().filter(reservation -> reservation.getId().equals(reservationId)).findFirst();
    }

    public synchronized boolean cancelReservation(String reservationId){
        Optional<Reservation> result = getReservation(reservationId);
        if(result.isPresent()){
            reservations.remove(result);
            return true;
        }else{
            return false;
        }
    }

    public synchronized List<Car> searchCars(String maker, String model, LocalDate startDate, LocalDate endDate){
        List<Car> result = new ArrayList();
        if(!maker.isEmpty()){
            CarSearchCriteria makerCriteria = new MakerCriteria(maker);
            List<Car> makerFilteredCars = makerCriteria.meetCriteria(cars);
            result = new ArrayList<Car>(makerFilteredCars);
        }
        if(!model.isEmpty()){
            CarSearchCriteria modelCriteria = new ModelCriteria(model);
            List<Car> modelFilteredCars = modelCriteria.meetCriteria(result.isEmpty() ? cars : result);
            result = new ArrayList<Car>(modelFilteredCars);
        }
//        if(!startDate.equals(LocalDate.MIN) && !endDate.equals(LocalDate.MIN)){
//            CarSearchCriteria startDateCriteria = new DateCriteria(startDate,endDate,result.isEmpty() ? cars : result);
//            List<Car> dateFilteredCars = startDateCriteria.meetCriteria(reservations);
//            result = dateFilteredCars;
//        }

        List<Car> searchList = result.isEmpty() ? cars : result;
        result = searchList.stream().filter(car -> isCarAvailable(car,startDate,endDate)).toList();

        return result;
    }

    public synchronized boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate){
        for(Reservation reservation: reservations) {
            if (reservation.getCar().equals(car)) {
                boolean startDateReserved = startDate.isAfter(reservation.getStartDate()) && startDate.isBefore(reservation.getEndDate());
                boolean endDateReserved = endDate.isAfter(reservation.getStartDate()) && endDate.isBefore(reservation.getEndDate());
                if (startDateReserved || endDateReserved) {
                    return false;
                }
            }
        }
        return true;
    }

    private PaymentProcessor getPaymentProcessor(PaymentType type){
        if(type.equals(PaymentType.CARD)){
            return new CardPaymentProcessor();
        }else{
            return new PaypalPaymentProcessor();
        }
    }
}

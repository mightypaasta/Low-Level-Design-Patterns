package patterns.car_rental;

import patterns.car_rental.entities.Car;
import patterns.car_rental.entities.Customer;
import patterns.car_rental.enums.PaymentType;

import java.time.LocalDate;
import java.util.List;

public class CarRentalDemo {
    public static void run(){
        CarRentalSystem system = CarRentalSystem.getInstance();

        system.addCar(new Car("Toyota", "Camry", 2020, "ABC123",40.0));
        system.addCar(new Car("Honda", "Civic", 2019, "XYZ789",35.0));
        system.addCar(new Car("Ford", "Mustang", 2021, "DEF456",70.0));
        system.addCar(new Car("Chevrolet", "Malibu", 2018, "GHI012",30.0));

        Customer Bob = new Customer("Bob Smith","123 Main St","D1234567");

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);


        List<Car> customer1searchCars = system.searchCars("Toyota", "Camry",null,null);
        if(!customer1searchCars.isEmpty()){
            Car selectedCar = customer1searchCars.get(0);
            var reservation = system.makeReservation(selectedCar,Bob,startDate,endDate);
            if(reservation!=null){
                boolean paymentSuccess = system.processPayment(reservation, PaymentType.CARD);
                if(paymentSuccess){
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getId());
                }else{
                    System.out.println("Payment failed. Reservation canceled.");
                }
            }else{
                System.out.println("Selected car is not available for the given dates.");
            }
        }

        Customer Alice = new Customer("Alice Johnson","456 Oak Ave","D7654321");

        startDate = LocalDate.now().plusDays(2);
        endDate = startDate.plusDays(4);

        List<Car> customer2searchCars = system.searchCars("Toyota", "Camry", startDate, endDate);
        if(!customer2searchCars.isEmpty()){
            Car selectedCar = customer2searchCars.get(0);
            var reservation = system.makeReservation(selectedCar,Alice,startDate,endDate);
            if(reservation!=null){
                boolean paymentSuccess = system.processPayment(reservation, PaymentType.CARD);
                if(paymentSuccess){
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getId());
                }else{
                    System.out.println("Payment failed. Reservation canceled.");
                }
            }else{
                System.out.println("Selected car is not available for the given dates.");
            }
        }else {
            System.out.println("No available cars found for the given criteria.");
        }

        customer2searchCars = system.searchCars("Honda", "Camry", startDate, endDate);
        if(!customer2searchCars.isEmpty()){
            Car selectedCar = customer2searchCars.get(0);
            var reservation = system.makeReservation(selectedCar,Alice,startDate,endDate);
            if(reservation!=null){
                boolean paymentSuccess = system.processPayment(reservation, PaymentType.CARD);
                if(paymentSuccess){
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getId());
                }else{
                    System.out.println("Payment failed. Reservation canceled.");
                }
            }else{
                System.out.println("Selected car is not available for the given dates.");
            }
        }else {
            System.out.println("No available cars found for the given criteria.");
        }
    }
}

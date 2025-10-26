package patterns.car_rental.entities;

import java.util.Date;

public class Car {
    private final String make;
    private final String model;
    private final int year;
    private final double rentPerDay;
    private final String licensePlate;
    private boolean isAvailable;

    public Car(String make, String model, int year, String licensePlate, double rentPerDay){
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
    }

    public void setAvailable(boolean available){
        this.isAvailable = available;
    }

    public String getMake(){ return make; }

    public String getModel(){ return model; }

    public int getYear(){ return year; }

    public String getLicensePlate(){ return licensePlate; }

    public double getRentPerDay(){ return rentPerDay; }

}

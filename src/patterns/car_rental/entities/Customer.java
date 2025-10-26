package patterns.car_rental.entities;

public class Customer {
    private final String name;
    private final String address;
    private final String licenseNumber;

    public Customer(String name, String address, String licenseNumber){
        this.name = name;
        this.address = address;
        this.licenseNumber = licenseNumber;
    }

    public String getName(){ return name; }

    public String getAddress(){ return address; }

    public String getLicenseNumber(){ return licenseNumber; }
}

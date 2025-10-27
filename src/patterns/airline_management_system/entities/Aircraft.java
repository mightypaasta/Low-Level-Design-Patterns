package patterns.airline_management_system.entities;

public class Aircraft {
    private final String tailNumber;
    private final String model;
    private final int totalSeats;

    public Aircraft(String tailNumber, String model, int totalSeats){
        this.tailNumber = tailNumber;
        this.model = model;
        this.totalSeats = totalSeats;
    }

    public String getTailNumber(){ return tailNumber; }
    public String getModel(){ return model; }
    public int getTotalSeats(){ return totalSeats; }
}

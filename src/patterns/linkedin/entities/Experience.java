package patterns.linkedin.entities;

import java.time.LocalDate;

public class Experience {
    private final String companyName;
    private final String position;
    private final LocalDate startYear;
    private LocalDate endYear;

    public Experience(String companyName, String position, LocalDate startYear){
        this.companyName = companyName;
        this.position = position;
        this.startYear = startYear;
    }

    public Experience(String companyName, String position, LocalDate startYear, LocalDate endYear){
        this.companyName = companyName;
        this.position = position;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @Override
    public String toString(){
        return String.format("%s at %s (%s to %s)", position, companyName, startYear, endYear == null ? "Present" : endYear);
    }
}

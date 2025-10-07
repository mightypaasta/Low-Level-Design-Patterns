package patterns.linkedin.entities;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private final List<Education> educations = new ArrayList<>();
    private final List<Experience> experiences = new ArrayList<>();
    private String summary;

    public Profile(String summary, List<Education> educations, List<Experience> experiences){
        this.summary = summary;
        this.educations.addAll(educations);
        this.experiences.addAll(experiences);
    }

    public void addEducation(Education education){
        educations.add(education);
    }

    public void addExperience(Experience experience){
        experiences.add(experience);
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public void display(){
        System.out.println("Summary: "+ (summary == null ? "N/A" : summary));

        System.out.println("Educations: ");
        if(educations.isEmpty()){
            System.out.println(" - None");
        }
        educations.forEach(edu -> System.out.println("    - "+edu.toString()));

        System.out.println("Experiences: ");
        if(experiences.isEmpty()){
            System.out.println("   - None");
        }
        experiences.forEach(exp -> System.out.println("   - "+exp.toString()));
    }
}

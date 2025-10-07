package patterns.linkedin;

import patterns.linkedin.entities.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class LinkedInDemo {
    public static void Demo(){

        LinkedInSystem linkedInSystem = LinkedInSystem.getInstance();

        List<Education> bobEducation = new ArrayList<>();
        bobEducation.add(new Education("MIT","Bachelor's",2019,2023));
        bobEducation.add(new Education("MIT","Master's",2024,2026));

        List<Experience> bobExperience = new ArrayList<>();
        bobExperience.add(new Experience("Google","Software Intern", LocalDate.of(2022, Month.JULY,12),LocalDate.of(2023,Month.JANUARY,12)));

        Member Bob = new Member("Bob","bob@gmail.com",new Profile("Software Engineer @Google",bobEducation,bobExperience));

        List<Education> aliceEducation = new ArrayList<>();
        aliceEducation.add(new Education("MIT","Bachelor's",2019,2023));
        aliceEducation.add(new Education("MIT","Master's",2024,2026));

        List<Experience> aliceExperience = new ArrayList<>();
        aliceExperience.add(new Experience("Apple","Software Intern", LocalDate.of(2022, Month.JULY,12),LocalDate.of(2023,Month.JANUARY,12)));

        Member Alice = new Member("Alice","alice@apple.com", new Profile("Software Engineer @Apple",aliceEducation,aliceExperience));

        linkedInSystem.registerMember(Bob);
        linkedInSystem.registerMember(Alice);

        Member alice = linkedInSystem.getMember("Alice");
        System.out.println("Found alice: "+alice.getName());

        String bobRequestId = linkedInSystem.sendRequest(Bob,Alice);
        Alice.viewNotifications();
        linkedInSystem.acceptRequest(bobRequestId);

        if(Alice.getConnections().contains(Bob)){
            System.out.println("Alice and Bob are connected to each other.");
        }

        linkedInSystem.createPost(Bob.getId(),"Designing a reliable and robust microservice is challenging.");
        linkedInSystem.createPost(Bob.getId(),"Design patterns are time-tested tools to solve some common problems in software designs.");

        linkedInSystem.viewNewsFeed(Alice.getId());

        Post latestPostByBob = linkedInSystem.getLatestPostByMember(Bob.getId());
        latestPostByBob.addLike(new Like(Alice));
        latestPostByBob.addComment(new Comment("Thanks for sharing this wonderful insight Bob.",Alice));

        Bob.viewNotifications();
    }
}

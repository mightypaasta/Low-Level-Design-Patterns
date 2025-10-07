package patterns.linkedin.entities;

import patterns.linkedin.enums.NotificationType;
import patterns.linkedin.observers.NotificationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member implements NotificationObserver {
    private final Profile profile;
    private final List<Member> connections;
    private final List<Notification> notifications;
    private final String id;
    private String email;
    private String name;

    public Member(String name, String email, Profile profile){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.connections = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void addConnection(Member member){
        connections.add(member);
    }

    @Override
    public void update(Notification notification) {
//        NotificationType type = notification.getNotificationType();
//        String memberId = notification.getMemberId();
//        String createdAt = notification.getCreatedAt().toString();
//        String displayText = String.format("%s by %s at %s",type,memberId,createdAt);
//        System.out.println(displayText);
        String content = notification.getContent();
        System.out.println("Notification pushed to: "+name);
        notifications.add(notification);
//        System.out.println("Content: "+content);
    }

    public void viewNotifications(){
        System.out.println("Notifications: ");

        notifications.stream()
                        .filter(n -> !n.isRead())
                                .forEach(notification -> {
                                    System.out.println(" - "+notification.getContent());
                                    notification.markAsRead();
                                });
    }

    public void displayProfile(){
        profile.display();
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public List<Member> getConnections() { return connections; }

    public void setEmail(String email){
        this.email = email;
    }
}

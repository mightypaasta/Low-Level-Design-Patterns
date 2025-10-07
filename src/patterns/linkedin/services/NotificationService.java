package patterns.linkedin.services;

import patterns.linkedin.entities.Member;
import patterns.linkedin.entities.Notification;

public class NotificationService {
    public void sendNotification(Member member, Notification notification){
        member.update(notification);
    }
}

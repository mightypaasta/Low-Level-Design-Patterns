package patterns.linkedin.entities;

import patterns.linkedin.enums.NotificationType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private final String id;
    private final LocalDateTime createdAt;
    private final NotificationType type;
    private final String memberId;
    private final String content;
    private boolean isRead;

    public Notification(NotificationType type, String memberId, String content){
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.type = type;
        this.memberId = memberId;
        this.content = content;
        this.isRead = false;
    }

    public String getContent(){
        return content;
    }

    public NotificationType getNotificationType(){
        return type;
    }

    public String getMemberId(){
        return memberId;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public boolean isRead(){
        return isRead;
    }

    public void markAsRead(){
        isRead = true;
    }

}

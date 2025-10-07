package patterns.linkedin.entities;

import patterns.linkedin.enums.NotificationType;
import patterns.linkedin.observers.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post extends Subject {
    private final String id;
    private final String content;
    private final Member author;
    private final LocalDateTime createdAt;
    private final List<Like> likes;
    private final List<Comment> comments;

    public Post(String content, Member author){
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.addObserver(author);
    }

    public void addLike(Like like){
        likes.add(like);
        String notificationContent = String.format("Your post with id %s got liked by %s", id, like.getMember().getName());
        Notification notification = new Notification(NotificationType.POST_LIKE,like.getMember().getId(),notificationContent);
        notifyAllObserver(notification);
    }

    public void addComment(Comment comment){
        comments.add(comment);
        String notificationContent = String.format("%s commented on your post with id %s %nContent: %s",comment.getAuthor().getName(),id,comment.getContent());
        Notification notification = new Notification(NotificationType.POST_COMMENT,comment.getAuthor().getId(),notificationContent);
        notifyAllObserver(notification);
    }

    public LocalDateTime getCreatedAt(){ return createdAt; }
    public String getContent(){ return content; }
    public Member getAuthor(){ return author; }
    public int likesCount(){ return likes.size();}
    public int commentsCount(){ return comments.size();}

}

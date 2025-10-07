package patterns.linkedin.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final String id;
    private final String content;
    private final LocalDateTime createdAt;
    private final Member author;

    public Comment(String content, Member member){
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.author = member;
    }

    public String getContent(){
        return content;
    }

    public Member getAuthor(){
        return author;
    }
}

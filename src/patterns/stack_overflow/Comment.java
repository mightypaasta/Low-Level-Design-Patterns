package patterns.stack_overflow;

import java.time.LocalDateTime;
import java.util.UUID;

class Comment{
    private final String id;
    private final String content;
    private final User author;
    private final LocalDateTime creationDateTime;

    public Comment( String content, User author) {
        if (content == null || author == null) {
            throw new IllegalArgumentException("ID, content, and author cannot be null");
        }
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.creationDateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }
}
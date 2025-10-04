package patterns.task_manager.models;

import java.util.UUID;

public class Comment {
    private final String id;
    private final String content;
    private final User user;

    public Comment(String content, User user){
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.user = user;
    }

    public String getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public User getCommenter(){
        return user;
    }

}

package patterns.linkedin.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Like {
    private final LocalDateTime createdAt;
    private final Member member;

    public Like(Member member){
        this.member = member;
        this.createdAt = LocalDateTime.now();
    }

    public Member getMember() {
        return member;
    }
}

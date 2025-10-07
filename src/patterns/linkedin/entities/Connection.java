package patterns.linkedin.entities;

import patterns.linkedin.enums.ConnectionType;

import java.time.LocalDateTime;


public class Connection {
    private final Member from;
    private final Member to;
    private ConnectionType status;
    private final LocalDateTime requestAt;
    private LocalDateTime acceptedAt;

    public Connection(Member from, Member to){
        this.from = from;
        this.to = to;
        this.status = ConnectionType.PENDING;
        this.requestAt = LocalDateTime.now();
    }

    public void setStatus(ConnectionType status){
        this.status = status;
        if(status == ConnectionType.ACCEPTED){
            this.acceptedAt = LocalDateTime.now();
        }
    }

    public Member getFromMember() {return from;}
    public Member getToMember() {return to;}
    public ConnectionType getSatus() {return status;}
}

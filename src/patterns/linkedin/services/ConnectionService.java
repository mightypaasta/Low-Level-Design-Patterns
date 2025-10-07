package patterns.linkedin.services;

import patterns.linkedin.entities.Connection;
import patterns.linkedin.entities.Member;
import patterns.linkedin.entities.Notification;
import patterns.linkedin.enums.ConnectionType;
import patterns.linkedin.enums.NotificationType;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionService {
    private final Map<String,Connection> connectionMap;
    private final NotificationService notificationService;

    public ConnectionService(NotificationService notificationService){
        this.connectionMap = new ConcurrentHashMap<>();
        this.notificationService = notificationService;
    }

    public String requestConnection(Member from, Member to){
        Connection connection = new Connection(from,to);
        String requestId = UUID.randomUUID().toString();
        connectionMap.put(requestId,connection);

        Notification notification = new Notification(NotificationType.CONNECTION_REQUEST,to.getId(),"Connection Request by "+from.getName()+" , Request ID: "+requestId);
        notificationService.sendNotification(to,notification);
        return requestId;
    }

    public void acceptRequest(String requestId){
        Connection connection = connectionMap.get(requestId);
        if(requestId!=null && connection.getSatus() == ConnectionType.PENDING){
            connection.setStatus(ConnectionType.ACCEPTED);

            Member from = connection.getFromMember();
            Member to = connection.getToMember();

            from.addConnection(to);
            to.addConnection(from);

            System.out.printf("%s accepted the connection request by %s.%n",to.getName(),from.getName());
        }
    }
}

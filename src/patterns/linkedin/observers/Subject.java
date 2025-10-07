package patterns.linkedin.observers;

import patterns.linkedin.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<NotificationObserver> observerList = new ArrayList<>();

    public void addObserver(NotificationObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(NotificationObserver observer){
        observerList.remove(observer);
    }

    public void notifyAllObserver(Notification notification){
        for(NotificationObserver observer: observerList){
            observer.update(notification);
        }
    }
}

package patterns.linkedin.observers;

import patterns.linkedin.entities.Notification;

public interface NotificationObserver {
    void update(Notification notification);
}

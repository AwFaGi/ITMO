package org.example.mbean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Hits extends NotificationBroadcasterSupport implements HitsMBean {

    private int hits;
    private int all;

    private int missCount = 0;

    private int notificationId = 0;

    @Override
    public void registerShot(boolean isHit) {
        if(isHit){
            hits++; all++;
            missCount = 0;
        } else {
            all++;
            missCount++;

            if(missCount >= 4){
                Notification notification = new Notification("fourMissesInARow",
                        this, notificationId, "Missed 4 times in a row!");
                sendNotification(notification);

                notificationId++;

                missCount = 0;
            }

        }
    }

    @Override
    public int getHits() {
        return hits;
    }

    @Override
    public int getAll() {
        return all;
    }
}

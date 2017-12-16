package com.corso.policysystem;

import java.util.ArrayList;

public abstract class Notifiable {
    private ArrayList<Notification> notificationStack = new ArrayList<>(); //FIFO notification list

    protected void receiveNotification(Notification n){
        notificationStack.add(n);

        onReceiveNotification(n);
    }

    protected Notification getNotification() {
        if (!notificationStack.isEmpty()) {
            return notificationStack.get(notificationStack.size() - 1);
        }
        return null;
    }

    protected void onReceiveNotification(Notification n){
    }

}

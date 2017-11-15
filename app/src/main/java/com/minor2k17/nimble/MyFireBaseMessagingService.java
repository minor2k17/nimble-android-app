package com.minor2k17.nimble;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by shaur on 10-11-2017.
 */

public class MyFireBaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = "FCMnimble";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i(TAG,"From: " + remoteMessage.getFrom());
        Log.i(TAG,"Notification Message BODY:"+remoteMessage.getNotification().getBody());
        NotifyUser(remoteMessage.getFrom(),remoteMessage.getNotification().getBody());
    }
    public void NotifyUser(String from,String notification){
        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(from,notification,new Intent(getApplicationContext(),MainActivity.class));
    }
}
package com.minor2k17.nimble;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by shaur on 09-11-2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TOKEN_BROADCAST = "mynimbletoken";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase id:","Refreshed token: " + refreshedToken);

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        storeToken(refreshedToken);
    }
    private void storeToken(String token){
        SharedPreManager.getInstance(getApplicationContext()).storedToken(token);
    }
}
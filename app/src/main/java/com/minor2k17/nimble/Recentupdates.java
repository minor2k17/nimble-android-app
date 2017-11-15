package com.minor2k17.nimble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Recentupdates extends AppCompatActivity {

    TextView textView;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentupdates);
        textView = (TextView) findViewById(R.id.token_print);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // textView.setText(SharedPreManager.getInstance(Recentupdates.this).getToken());
            }
        };

        if(SharedPreManager.getInstance(this).getToken()!=null)
        {
            textView.setText(SharedPreManager.getInstance(Recentupdates.this).getToken());
            Log.i("MynimbleToken",SharedPreManager.getInstance(this).getToken());
        }

        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
    }
}
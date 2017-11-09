package com.minor2k17.nimble;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class no_internet extends AppCompatActivity implements View.OnClickListener {

    Button wifion, dataon;
    boolean state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        wifion = (Button) findViewById(R.id.wifi);
        dataon = (Button) findViewById(R.id.mobiledata);

        wifion.setOnClickListener(no_internet.this);
        dataon.setOnClickListener(no_internet.this);
    }

    @Override
    public void onClick(View view) {
        if (view == wifion){
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
            Toast.makeText(no_internet.this, "Wifi is ON", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(no_internet.this,MainActivity.class));
                }
            },3500);
        } else if (view == dataon){
            if (isMobileDataEnable()){
                toggleMobileDataConnection(false);
                dataon.setBackgroundColor(Color.parseColor("#93E2ff"));
            }
            else {
                toggleMobileDataConnection(true);
                dataon.setBackgroundColor(Color.parseColor("#93E2D5"));
            }

        }

    }

    public boolean isMobileDataEnable() {

        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true);
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (Exception e) {

        }
        return mobileDataEnabled;
    }

    public boolean toggleMobileDataConnection(boolean ON) {
        try {
            // create instance of connectivity manager and get system service

            final ConnectivityManager conman = (ConnectivityManager) this
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            // define instance of class and get name of connectivity manager
            // system service class
            final Class conmanClass = Class
                    .forName(conman.getClass().getName());
            // create instance of field and get mService Declared field
            final Field iConnectivityManagerField = conmanClass
                    .getDeclaredField("mService");
            // Attempt to set the value of the accessible flag to true
            iConnectivityManagerField.setAccessible(true);
            // create instance of object and get the value of field conman
            final Object iConnectivityManager = iConnectivityManagerField
                    .get(conman);
            // create instance of class and get the name of iConnectivityManager
            // field
            final Class iConnectivityManagerClass = Class
                    .forName(iConnectivityManager.getClass().getName());
            // create instance of method and get declared method and type
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass
                    .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            // Attempt to set the value of the accessible flag to true
            setMobileDataEnabledMethod.setAccessible(true);
            // dynamically invoke the iConnectivityManager object according to
            // your need (true/false)
            setMobileDataEnabledMethod.invoke(iConnectivityManager, ON);
        } catch (Exception e) {
        }
        return true;
    }
}

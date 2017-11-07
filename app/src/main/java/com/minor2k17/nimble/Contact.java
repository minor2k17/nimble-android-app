package com.minor2k17.nimble;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        /*
        This part of code prompts the user to connect to internet connection
         */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //get details on currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()){
            //Do-nothing
        }
        else{
            startActivity(new Intent(Contact.this,no_internet.class));
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView email = (TextView) findViewById(R.id.email);
       // TextView chat = (TextView) findViewById(R.id.chat);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Contact.this,Contact_email.class);
                startActivity(intent);
            }
        });

    }
}

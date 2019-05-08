package com.simshospitals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.simshospitals.login.preference;
import static com.simshospitals.login.saveit;

public class splash extends AppCompatActivity {

    int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sf4=getSharedPreferences(preference, Context.MODE_PRIVATE);
        final String check = sf4.getString(saveit,"");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(check.equals("")) {
                    Intent x = new Intent(splash.this, login.class);
                    finish();
                    startActivity(x);
                }
                else
                {
                    Intent x=new Intent(splash.this,MainActivity.class);
                    finish();
                    startActivity(x);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

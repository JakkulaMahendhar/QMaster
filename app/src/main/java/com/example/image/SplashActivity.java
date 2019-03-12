package com.example.image;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            Typeface typeface = Typeface.createFromAsset(getAssets(), "akshar.ttf");


            TextView appname = (TextView) findViewById(R.id.app_name);
            appname.setTypeface(typeface);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, Dashboard.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

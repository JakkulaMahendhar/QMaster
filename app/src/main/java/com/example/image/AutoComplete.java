package com.example.image;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete extends AppCompatActivity {

    TextView tv_aptitude,tv_logical,tv_verbal,tv_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_auto_complete);

            Typeface typeface = Typeface.createFromAsset(getAssets(), "akshar.ttf");

            tv_aptitude = (TextView) findViewById(R.id.tv_aptitude);
            tv_verbal = (TextView) findViewById(R.id.tv_verbal);
            tv_logical = (TextView) findViewById(R.id.tv_logical);
            tv_profile = (TextView) findViewById(R.id.tv_profileName);

            tv_aptitude.setTypeface(typeface);
            tv_verbal.setTypeface(typeface);
            tv_logical.setTypeface(typeface);
            tv_profile.setTypeface(typeface);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

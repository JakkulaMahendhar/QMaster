package com.example.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    TextView text_anim;

    // Animation
    Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        text_anim = (TextView) findViewById(R.id.text_anim);

        animFadein= AnimationUtils.loadAnimation(this, R.anim.animationfile);
        text_anim.setText("Animated Text Here!....");
        text_anim.startAnimation(animFadein);



    }
}

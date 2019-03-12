package com.example.image;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {

    boolean doublebackpressed = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            TextView tv_welcome, tv_topics, tv_aptitude, tv_logical, tv_verbal, tv_technical;
            Button btn_signup,btn_signIn;

            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "akshar.ttf");
            tv_welcome = (TextView) view.findViewById(R.id.tv_welcome);
            tv_aptitude = (TextView) view.findViewById(R.id.tv_aptitude);
            tv_logical = (TextView) view.findViewById(R.id.tv_logical);
            tv_technical = (TextView) view.findViewById(R.id.tv_technical);
            tv_verbal = (TextView) view.findViewById(R.id.tv_verbal);
            tv_topics = (TextView) view.findViewById(R.id.tv_topics);
            btn_signup = (Button) view.findViewById(R.id.btn_signUp);
            btn_signIn = (Button) view.findViewById(R.id.btn_signIn);
            tv_welcome.setTypeface(typeface);
            tv_aptitude.setTypeface(typeface);
            tv_logical.setTypeface(typeface);
            tv_technical.setTypeface(typeface);
            tv_verbal.setTypeface(typeface);
            tv_topics.setTypeface(typeface);
            btn_signup.setTypeface(typeface);
            btn_signIn.setTypeface(typeface);
            btn_signIn.setOnClickListener(this);
            btn_signup.setOnClickListener(this);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }



    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {

                case R.id.btn_signUp:
                    Intent i = new Intent(getActivity(),SignUpActivity.class);
                    startActivity(i);

                    //signUpDialog(view);
                    break;

                case R.id.btn_signIn:
                    Intent signIn = new Intent(getActivity(),SignInActivity.class);
                    startActivity(signIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

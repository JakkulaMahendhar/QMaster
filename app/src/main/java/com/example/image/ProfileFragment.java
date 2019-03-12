package com.example.image;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        try {
           TextView tv_aptitude,tv_logical,tv_verbal,tv_profile;
            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "akshar.ttf");
            view  = getLayoutInflater().inflate(R.layout.activity_auto_complete, container, false);
            tv_aptitude = (TextView) view.findViewById(R.id.tv_aptitude);
            tv_verbal = (TextView) view.findViewById(R.id.tv_verbal);
            tv_logical = (TextView) view.findViewById(R.id.tv_logical);
            tv_profile = (TextView) view.findViewById(R.id.tv_profileName);

            tv_aptitude.setTypeface(typeface);
            tv_verbal.setTypeface(typeface);
            tv_logical.setTypeface(typeface);
            tv_profile.setTypeface(typeface);

            String name = getArguments().getString("name");
            tv_profile.setText(name);

        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

}

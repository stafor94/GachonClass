package com.stafor.gachonclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ClassFragment extends Fragment {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_class, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.img_campus);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), ImageActivity.class);
                myIntent.putExtra("img", R.drawable.campusmap);
                startActivity(myIntent);
            }
        });
        return rootView;
    }
}

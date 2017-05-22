package com.stafor.gachonclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ClassFragment extends Fragment {
    LinearLayout layout;
    ImageView imageView;
    Button[] buttons = new Button[11];
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

        layout = (LinearLayout) rootView.findViewById(R.id.builds);
        for (int i = 0; i < 11; i++) {
            buttons[i] = new Button(getContext());
            layout.addView(buttons[i]);
        }
        buttons[0].setText("가천관");
        buttons[1].setText("비전타워");
        buttons[2].setText("법과대학");
        buttons[3].setText("공과대학1");
        buttons[4].setText("공과대학2");
        buttons[5].setText("바이오나노대학");
        buttons[6].setText("IT대학");
        buttons[7].setText("한의과대학");
        buttons[8].setText("예술대학1");
        buttons[9].setText("예술대학2");
        buttons[10].setText("글로벌센터");

        return rootView;
    }
}

package com.stafor.dbsample02;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

//메인 액티비티
public class MainActivity extends AppCompatActivity {

    DBHelper_everyday dbHelpereveryday;
    DBHelper_wanttobuy dbHelperwanttobuy;
    DBHelper_promise dbHelperpromise;
    DBHelper_dday dbHelperdday;

    // Every day selector
    TextView Everyday_head_text;
    TextView Everyday_middle_text1;
    TextView Everyday_middle_text2;
    TextView Everyday_middle_text3;
    TextView Everyday_middle_text4;
    TextView Everyday_middle_text5;
    TextView Everyday_middle_text6;

    // Promise selector
    TextView Promise_head_text;
    TextView Promise_middle_text1;
    TextView Promise_middle_text2;
    TextView Promise_middle_text3;


    // Best Restaurant selector
    TextView Best_restaurant_text;

    // Want to buy selector
    TextView Wanttobuy_head_text;
    TextView Wanttobuy_middle_text1;
    TextView Wanttobuy_middle_text2;
    TextView Wanttobuy_middle_text3;
    TextView Wanttobuy_middle_text4;
    TextView Wanttobuy_bottom_text;

    // Checklist selector
    TextView Checklist_text;

    // D-day selector
    TextView Dday_head_text;
    TextView Dday_middle_text1;
    TextView Dday_middle_text2;
    TextView Dday_middle_text3;

    LinearLayout Everyday_layout;
    LinearLayout Promise_layout;
    LinearLayout Best_restaurant_layout;
    LinearLayout Wanttobuy_layout;
    LinearLayout Dday_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelpereveryday = new DBHelper_everyday(this);
        dbHelperwanttobuy = new DBHelper_wanttobuy(this);
        dbHelperpromise = new DBHelper_promise(this);
        dbHelperdday = new DBHelper_dday(this);

        Everyday_head_text = (TextView)findViewById(R.id.Everyday_head_text);
        Everyday_middle_text1 = (TextView)findViewById(R.id.Everyday_middle_text1);
        Everyday_middle_text2 = (TextView)findViewById(R.id.Everyday_middle_text2);
        Everyday_middle_text3 = (TextView)findViewById(R.id.Everyday_middle_text3);
        Everyday_middle_text4 = (TextView)findViewById(R.id.Everyday_middle_text4);
        Everyday_middle_text5 = (TextView)findViewById(R.id.Everyday_middle_text5);
        Everyday_middle_text6 = (TextView)findViewById(R.id.Everyday_middle_text6);

        Promise_head_text = (TextView)findViewById(R.id.Promise_head_text);
        Promise_middle_text1 = (TextView)findViewById(R.id.Promise_middle_text1);
        Promise_middle_text2 = (TextView)findViewById(R.id.Promise_middle_text2);
        Promise_middle_text3 = (TextView)findViewById(R.id.Promise_middle_text3);


        Best_restaurant_text = (TextView)findViewById(R.id.Best_restaurant_text);

        Wanttobuy_head_text = (TextView)findViewById(R.id.Wanttobuy_head_text);
        Wanttobuy_middle_text1 = (TextView)findViewById(R.id.Wanttobuy_middle_text1);
        Wanttobuy_middle_text2 = (TextView)findViewById(R.id.Wanttobuy_middle_text2);
        Wanttobuy_middle_text3 = (TextView)findViewById(R.id.Wanttobuy_middle_text3);
        Wanttobuy_middle_text4 = (TextView)findViewById(R.id.Wanttobuy_middle_text4);
        Wanttobuy_bottom_text = (TextView)findViewById(R.id.Wanttobuy_bottom_text);
        Checklist_text = (TextView)findViewById(R.id.Checklist_text);

        Dday_head_text = (TextView)findViewById(R.id.Dday_head_text);
        Dday_middle_text1 = (TextView)findViewById(R.id.Dday_middle_text1);
        Dday_middle_text2 = (TextView)findViewById(R.id.Dday_middle_text2);
        Dday_middle_text3 = (TextView)findViewById(R.id.Dday_middle_text3);

        Everyday_layout = (LinearLayout)findViewById(R.id.Everyday_layout);
        Promise_layout = (LinearLayout)findViewById(R.id.Promise_layout);
        Best_restaurant_layout = (LinearLayout)findViewById(R.id.Best_restaurant_layout);
        Wanttobuy_layout = (LinearLayout)findViewById(R.id.Wanttobuy_layout);
        Dday_layout = (LinearLayout)findViewById(R.id.Dday_layout);

        Typeface typeface_Thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface typeface_Bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        Typeface typeface_ThinItalic = Typeface.createFromAsset(getAssets(), "fonts/Roboto-ThinItalic.ttf");
        Typeface typeface_BoldCondensed = Typeface.createFromAsset(getAssets(),"fonts/Roboto-BoldCondensed.ttf");

        Everyday_head_text.setTypeface(typeface_Thin);
        Promise_head_text.setTypeface(typeface_Thin);
        Wanttobuy_head_text.setTypeface(typeface_Thin);
        Dday_head_text.setTypeface(typeface_Thin);

        Everyday_middle_text1.setTypeface(typeface_Bold);
        Everyday_middle_text2.setTypeface(typeface_Bold);
        Everyday_middle_text3.setTypeface(typeface_Bold);
        Everyday_middle_text4.setTypeface(typeface_Bold);
        Everyday_middle_text5.setTypeface(typeface_Bold);
        Everyday_middle_text6.setTypeface(typeface_Bold);
        Promise_middle_text1.setTypeface(typeface_Bold);
        Promise_middle_text2.setTypeface(typeface_Bold);
        Promise_middle_text3.setTypeface(typeface_Bold);
        Wanttobuy_middle_text1.setTypeface(typeface_Bold);
        Wanttobuy_middle_text2.setTypeface(typeface_Bold);
        Wanttobuy_middle_text3.setTypeface(typeface_Bold);
        Wanttobuy_middle_text4.setTypeface(typeface_Bold);
        Dday_middle_text1.setTypeface(typeface_Bold);
        Dday_middle_text2.setTypeface(typeface_Bold);
        Dday_middle_text3.setTypeface(typeface_Bold);

        Checklist_text.setTypeface(typeface_ThinItalic);
        Best_restaurant_text.setTypeface(typeface_BoldCondensed);



        // 각 항목별 이동
        Everyday_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplication(), EverydayActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        Promise_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplication(), PromiseActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        Best_restaurant_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplication(), BestrestaurantActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        Wanttobuy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplication(), WanttobuyActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Dday_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplication(), DdayActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        Everyday_middle_text1.setText(dbHelpereveryday.printData1());
        Everyday_middle_text2.setText(dbHelpereveryday.printData2());
        Everyday_middle_text3.setText(dbHelpereveryday.printData3());
        Everyday_middle_text4.setText(dbHelpereveryday.printData4());
        Everyday_middle_text5.setText(dbHelpereveryday.printData5());
        Everyday_middle_text6.setText(dbHelpereveryday.printData6());


        Wanttobuy_middle_text1.setText(dbHelperwanttobuy.printData1());
        Wanttobuy_middle_text2.setText(dbHelperwanttobuy.printData2());
        Wanttobuy_middle_text3.setText(dbHelperwanttobuy.printData3());
        Wanttobuy_middle_text4.setText(dbHelperwanttobuy.printData4());

        Promise_middle_text1.setText(dbHelperpromise.printData1());
        Promise_middle_text2.setText(dbHelperpromise.printData2());
        Promise_middle_text3.setText(dbHelperpromise.printData3());

        Dday_middle_text1.setText(dbHelperdday.printData1());
        Dday_middle_text2.setText(dbHelperdday.printData2());
        Dday_middle_text3.setText(dbHelperdday.printData3());

    }
}
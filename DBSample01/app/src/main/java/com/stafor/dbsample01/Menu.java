package com.stafor.dbsample01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Menu extends Activity {

    String ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");

        //알림장버튼
        Button btn_notice = (Button) findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notice.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        //출석버튼
        Button btn_attend = (Button) findViewById(R.id.btn_attend);
        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calender.class);
                //intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });


        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

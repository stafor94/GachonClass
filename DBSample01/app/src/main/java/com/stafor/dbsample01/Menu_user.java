package com.stafor.dbsample01;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_user extends Activity {

    String ID;


    @Override
    protected void onResume() {
        super.onResume();
        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");

        //알림장버튼
        Button btn_notice=(Button)findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notice_user.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        //출석버튼
        Button btn_attend=(Button)findViewById(R.id.btn_attend);
        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calender_user.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        //전화버튼
        Button phoneB = (Button)findViewById(R.id.phoneB);
        phoneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-7373-8347"));
                startActivity(intent);
            }
        });

        //로그인화면으로 돌아가기
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

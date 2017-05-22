package com.stafor.dbsample01;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class Join extends Activity {

    //회원가입
    String SIDText;
    String SPWText;
    String SNAMEText;
    String SPHONEText;

    EditText editText_id;
    EditText editText_pw;
    EditText editText_number;
    EditText editText_name;

    DBHelper mDBHelper;
    Cursor cursor;
    SimpleCursorAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        editText_id = (EditText)findViewById(R.id.editText_id);
        editText_pw = (EditText)findViewById(R.id.editText_pw);
        editText_number = (EditText)findViewById(R.id.editText_number);
        editText_name = (EditText)findViewById(R.id.editText_name);

        Button btn_memberjoin = (Button)findViewById(R.id.btn_memberjoin);

        mDBHelper = new DBHelper(this, "info.db", null, 1);

        //회원가입 화면에서 회원정보를 가져온뒤 각각의 DB속성에 넣는다.
        btn_memberjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIDText = editText_id.getText().toString();
                SPWText = editText_pw.getText().toString();
                SPHONEText = editText_number.getText().toString();
                SNAMEText = editText_name.getText().toString();

                SQLiteDatabase db = mDBHelper.getWritableDatabase();
                db.execSQL(
                        "INSERT INTO MEMBER(MEMB_ID, PW, MEMB_NM, TEL) VALUES('" + SIDText + "','" + SPWText + "','" + SNAMEText+"','"+SPHONEText+"');");
                Toast.makeText(getApplicationContext(), SIDText + "님 회원가입이 완료되었습니다!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //취소 하면 이전 화면으로 돌아간다.
        Button cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

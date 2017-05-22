package com.stafor.dbsample01;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Attend_enroll extends Activity  {

    String today;
    String Sattend;
    String Sabsent;

    TextView date;
    EditText edit_attend;
    EditText edit_attend1;

    DBHelper mDBHelper;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    String ID;
    Integer ID_Num;
    static boolean count=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_enroll);

        //홈 버튼
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu_user.class);
                startActivity(intent);
            }
        });

        Intent getIntent = getIntent();
        ID=getIntent.getStringExtra("ID");
        today = getIntent.getStringExtra("Param1");

        TextView text = (TextView)findViewById(R.id.texttoday);
        text.setText(today);

        edit_attend = (EditText)findViewById(R.id.edit_attend);
        edit_attend1 = (EditText)findViewById(R.id.edit_attend1);

        //출석버튼클릭
        Button btn_attend=(Button)findViewById(R.id.btn_attend);
        mDBHelper = new DBHelper(this, "info.db", null, 1);
        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_attend.setText("출석");
                edit_attend1.setText("");

                Sattend = edit_attend.getText().toString();

                if(count==false){
                    SQLiteDatabase db1 = mDBHelper.getReadableDatabase();
                    cursor = db1.rawQuery("SELECT MEMB_NUM from MEMBER where MEMB_ID = '"+ID+"';", null);
                    cursor.moveToFirst();
                    ID_Num = cursor.getInt(0);
                    cursor.close();
                    db1.close();
                    count=true;
                }


                SQLiteDatabase db = mDBHelper.getWritableDatabase();
                db.execSQL("INSERT INTO ATTEND(_id, DATE, AT) VALUES('" + ID_Num + "', '" + today + "' , '" + Sattend + "');");
                Toast.makeText(getApplicationContext(),ID+"님의 "+today+"출석 정보가 저장되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Menu_user.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
        //결석 버튼 클릭
        Button btn_absent = (Button)findViewById(R.id.btn_absent);
        btn_absent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                edit_attend1.setText("결석");
                edit_attend.setText("");

                Sabsent = edit_attend1.getText().toString();

                if(count==false){
                    SQLiteDatabase db1 = mDBHelper.getReadableDatabase();
                    cursor = db1.rawQuery("SELECT MEMB_NUM from MEMBER where MEMB_ID = '"+ID+"';", null);
                    cursor.moveToFirst();
                    ID_Num = cursor.getInt(0);
                    cursor.close();
                    db1.close();
                    count=true;
                }


                SQLiteDatabase db = mDBHelper.getWritableDatabase();
                db.execSQL("INSERT INTO ATTEND( _id, DATE, AT) VALUES('" + ID_Num + "', '" + today + "' , '" + Sabsent + "');");
                Toast.makeText(getApplicationContext(),ID+"님의 "+today+"출석 정보가 저장되었습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Menu_user.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
    }
}




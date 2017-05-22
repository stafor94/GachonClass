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


public class Notice_enroll extends Activity {

    String Stitle;
    String Scontents;

    EditText editText_title;
    EditText editText_contents;

    DBHelper mDBHelper;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    String ID;
    Integer ID_Num;
    static boolean count = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_enroll);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");

        editText_title = (EditText)findViewById(R.id.title);
        editText_contents = (EditText)findViewById(R.id.contents);

        //
        Button btn_enroll=(Button)findViewById(R.id.btn_enroll);
        mDBHelper = new DBHelper(this, "info.db", null, 1);
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stitle = editText_title.getText().toString();
                Scontents = editText_contents.getText().toString();
                //인텐트를 통해 가져온 ID값을 가지고 MEMB_NUM을 불러온다.
                if(count==false){
                    SQLiteDatabase db1 = mDBHelper.getReadableDatabase();
                    cursor = db1.rawQuery("SELECT MEMB_NUM from MEMBER where MEMB_ID = '"+ID+"';", null);
                    cursor.moveToFirst();
                    ID_Num = cursor.getInt(0);
                    cursor.close();
                    db1.close();
                    count=true;
                }


                //MEMB_NUM이 쓴 공지 사항으로 등록한다.
                SQLiteDatabase db = mDBHelper.getWritableDatabase();
                db.execSQL(
                        "INSERT INTO NOTICE(TITLE, CONTENT, MEMB_NUM) VALUES( '" + Stitle + "', '" +Scontents + "', '"+ID_Num+"');");
                Toast.makeText(getApplicationContext(), "글이 등록되었습니다", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(), Notice.class);
                startActivity(intent);
            }
        });

        //취소버튼
        Button btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(), Notice.class);
                startActivity(intent);
            }
        });

    }
}

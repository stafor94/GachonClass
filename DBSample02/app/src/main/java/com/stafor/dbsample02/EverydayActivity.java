package com.stafor.dbsample02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//매일 할일 액티비티
public class EverydayActivity extends AppCompatActivity {

    ImageView back_everyday;
    ListView everyday_list;
    ImageView plus_everyday;
    TextView count_everyday;


    DBHelper_everyday dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everyday);

        dbhelper = new DBHelper_everyday(this);

        back_everyday = (ImageView)findViewById(R.id.back_everyday);
        plus_everyday = (ImageView)findViewById(R.id.plus_everyday);
        count_everyday = (TextView)findViewById(R.id.count_everyday);




        everyday_list = (ListView)findViewById(R.id.everyday_list);


        back_everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        plus_everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Insert_everydayActivity.class);
                startActivity(myintent);

            }
        });

        db = dbhelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM everydayTable;",null);
        count_everyday.setText("" + cursor.getCount());
        if(cursor.getCount()>0) {
            startManagingCursor(cursor);
            DBAdapter_everyday dbAdapter = new DBAdapter_everyday(this, cursor);
            everyday_list.setAdapter(dbAdapter);
        }

        everyday_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Snackbar.make(view, "삭제하겠습니까?", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = cursor.getString(cursor.getColumnIndex("content"));
                        dbhelper.delete(str);
                    }
                }).show();
            }
        });






}

}

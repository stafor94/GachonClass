package com.stafor.dbsample02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//약속 액티비티
public class PromiseActivity extends AppCompatActivity {

    ImageView back_promise;
    ImageView plus_promise;
    TextView count_promise;
    ListView promise_list;

    DBHelper_promise dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promise);

        back_promise = (ImageView)findViewById(R.id.back_promise);
        plus_promise = (ImageView)findViewById(R.id.plus_promise);
        count_promise = (TextView)findViewById(R.id.count_promise);
        promise_list = (ListView)findViewById(R.id.promise_list);

        dbhelper = new DBHelper_promise(this);

        back_promise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        plus_promise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Insert_promiseActivity.class);
                startActivity(myIntent);
            }
        });

        db = dbhelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM promiseTable;",null);
        count_promise.setText("" + cursor.getCount());
        if(cursor.getCount()>0) {
            startManagingCursor(cursor);
            DBAdapter_promise dbAdapter = new DBAdapter_promise(this, cursor);
            promise_list.setAdapter(dbAdapter);
        }

        promise_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

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

//버킷리스트 액티비티
public class WanttobuyActivity extends AppCompatActivity {

    ImageView back_wanttobuy;
    ImageView plus_wanttobuy;
    TextView count_wanttobuy;
    TextView wanttobuy_to_layout_text;
    ListView wanttobuy_list;

    DBHelper_wanttobuy dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanttobuy);

        back_wanttobuy = (ImageView)findViewById(R.id.back_wanttobuy);
        plus_wanttobuy = (ImageView)findViewById(R.id.plus_wanttobuy);
        count_wanttobuy = (TextView)findViewById(R.id.count_wanttobuy);
        wanttobuy_to_layout_text = (TextView)findViewById(R.id.wanttobuy_to_layout_text);
        wanttobuy_list = (ListView)findViewById(R.id.wanttobuy_list);

        dbhelper = new DBHelper_wanttobuy(this);

        wanttobuy_to_layout_text.setText("" + dbhelper.printprice());

        back_wanttobuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        plus_wanttobuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Insert_wanttobuyActivity.class);
                startActivity(myIntent);

            }
        });


        db = dbhelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM wanttobuyTable;", null);
        count_wanttobuy.setText("" + cursor.getCount());
        if(cursor.getCount()>0) {
            startManagingCursor(cursor);
            DBAdapter_wanttobuy dbAdapter = new DBAdapter_wanttobuy(this, cursor);
            wanttobuy_list.setAdapter(dbAdapter);
        }

        wanttobuy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Snackbar.make(view, "삭제하겠습니까?", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = cursor.getString(cursor.getColumnIndex("name"));
                        dbhelper.delete(str);
                    }
                }).show();
            }
        });
    }
}

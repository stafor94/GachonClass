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
import android.widget.Toast;

import java.util.Calendar;

//디-데이 액티비티
public class DdayActivity extends AppCompatActivity {

    ImageView back_dday;
    ImageView plus_dday;
    ListView dday_list;
    TextView count_dday;

    DBHelper_dday dbhelper;
    SQLiteDatabase db;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);

        dbhelper = new DBHelper_dday(this);

        back_dday = (ImageView)findViewById(R.id.back_dday);
        plus_dday = (ImageView)findViewById(R.id.plus_dday);
        dday_list = (ListView)findViewById(R.id.dday_list);
        count_dday = (TextView)findViewById(R.id.count_dday);

        back_dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        plus_dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Insert_ddayActivity.class);
                startActivity(myIntent);
            }
        });

        db = dbhelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM ddayTable;",null);
        count_dday.setText("" + cursor.getCount());
        if(cursor.getCount()>0) {
            startManagingCursor(cursor);
            DBAdapter_dday dbAdapter = new DBAdapter_dday(this, cursor);
            dday_list.setAdapter(dbAdapter);
        }

        dday_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);

                int result = dday(cursor.getInt(1), cursor.getInt(2),cursor.getInt(3));
                Toast.makeText(getApplicationContext(), "D day = " + result, Toast.LENGTH_SHORT).show();
                Snackbar.make(view, " 삭제하겠습니까?" , Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = cursor.getString(cursor.getColumnIndex("content"));
                        dbhelper.delete(str);
                    }
                }).show();
            }
        });

    }

    // 디데이 계산
    public int dday(int year, int month, int day) {
        int tyear;
        int tmonth;
        int tday;
        int result;

        long d;
        long t;
        long r;

        Calendar calendar = Calendar.getInstance();
        tyear = calendar.get(Calendar.YEAR);
        tmonth = calendar.get(Calendar.MONTH);
        tday = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar dCalendar = Calendar.getInstance();
        dCalendar.set(year, month, day);

        t = calendar.getTimeInMillis();
        d = dCalendar.getTimeInMillis();

        r = (d-t)/(24*60*60*1000);
        result = (int)r -30;

        if (d>t) {
            result = -result;
        } else {
            result = -result;
        }
        return  result;
    }


}

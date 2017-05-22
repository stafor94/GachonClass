package com.stafor.dbsample02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

//약속 입력 액티비티
public class Insert_promiseActivity extends AppCompatActivity {

    EditText insert_promise_edit;
    Button insert_promise_btn;
    DatePicker promise_datepicker;
    TimePicker promise_timepicker;


    private int mhour;
    private int mminutes;
    private int myear;
    private int mmonth;
    private int mday;

    private DBHelper_promise dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_promise);

        dbhelper = new DBHelper_promise(this);

        insert_promise_edit = (EditText)findViewById(R.id.insert_promise_edit);
        insert_promise_btn = (Button)findViewById(R.id.insert_promise_btn);
        promise_datepicker = (DatePicker)findViewById(R.id.promise_datepicker);
        promise_timepicker = (TimePicker)findViewById(R.id.promise_timepicker);




        // 처음 datepicker 를 오늘 날짜로 초기화 한다
        // 그리고 리스너를 등록한다.
        promise_datepicker.init(promise_datepicker.getYear(), promise_datepicker.getMonth(), promise_datepicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myear = year;
                        mmonth = (monthOfYear +1);
                        mday = dayOfMonth;

                    }
                });

        promise_timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(getApplicationContext(), "time" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                mhour = hourOfDay;
                mminutes = minute;
            }
        });

        insert_promise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String str = insert_promise_edit.getText().toString();
                    dbhelper.insert(myear, mmonth, mday, mhour, mminutes, str);
                    finish();
                }catch (Exception e) {
                    Toast.makeText(getApplication(), "입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}

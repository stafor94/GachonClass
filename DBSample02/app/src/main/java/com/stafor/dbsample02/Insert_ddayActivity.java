package com.stafor.dbsample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
//디-데이 입력 액티비티
public class Insert_ddayActivity extends AppCompatActivity {

    DatePicker dday_picker;
    EditText insert_dday_edit;
    Button insert_dday_btn;

    private DBHelper_dday dbhelper;

    private int myear;
    private int mmonth;
    private int mday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dday);

        dbhelper = new DBHelper_dday(this);

        insert_dday_edit = (EditText)findViewById(R.id.insert_dday_edit);
        insert_dday_btn = (Button)findViewById(R.id.insert_dday_btn);
        dday_picker = (DatePicker)findViewById(R.id.dday_picker);

        // 처음 datepicker 를 오늘 날짜로 초기화 한다
        // 그리고 리스너를 등록한다.
        dday_picker.init(dday_picker.getYear(), dday_picker.getMonth(), dday_picker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myear = year;
                        mmonth = (monthOfYear +1);
                        mday = dayOfMonth;
                    }
                });

        insert_dday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String str = insert_dday_edit.getText().toString();
                    dbhelper.insert(myear, mmonth, mday, str);
                    finish();
                }catch (Exception e) {
                    Toast.makeText(getApplication(), "입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

package com.stafor.dbsample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//버킷리스트 입력 액티비티
public class Insert_wanttobuyActivity extends AppCompatActivity {

    private EditText insert_wanttobuy_name_edit;
    private EditText insert_wanttobuy_price_edit;
    private Button insert_wanttobuy_btn;

    private DBHelper_wanttobuy dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_wanttobuy);

        dbhelper = new DBHelper_wanttobuy(this);

        insert_wanttobuy_name_edit = (EditText)findViewById(R.id.insert_wanttobuy_name_edit);
        insert_wanttobuy_price_edit = (EditText)findViewById(R.id.insert_wanttobuy_price_edit);
        insert_wanttobuy_btn = (Button)findViewById(R.id.insert_wanttobuy_btn);


        insert_wanttobuy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name = insert_wanttobuy_name_edit.getText().toString();
                    String price = insert_wanttobuy_price_edit.getText().toString();
                    dbhelper.insert(name,price);
                    finish();
                }catch (Exception e) {
                    Toast.makeText(getApplication(), "입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

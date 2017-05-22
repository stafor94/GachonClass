package com.stafor.dbsample02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//맛집 입력 액티비티
public class Insert_bestrestaurantActivity extends AppCompatActivity {

    private EditText insert_bestaurant_name_edit;
    private EditText insert_bestaurant_locate_edit;
    private Button insert_bestrestaurant_btn;

    private DBHelper_bestrestaurant dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bestrestaurant);

        dbHelper = new DBHelper_bestrestaurant(this);

        insert_bestaurant_name_edit = (EditText)findViewById(R.id.insert_bestrestaurant_name_edit);
        insert_bestaurant_locate_edit = (EditText)findViewById(R.id.insert_bestrestaurant_locate_edit);
        insert_bestrestaurant_btn = (Button)findViewById(R.id.insert_bestrestaurant_btn);


        insert_bestrestaurant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = insert_bestaurant_name_edit.getText().toString();
                    String locate = insert_bestaurant_locate_edit.getText().toString();
                    dbHelper.insert(name, locate);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplication(), "입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

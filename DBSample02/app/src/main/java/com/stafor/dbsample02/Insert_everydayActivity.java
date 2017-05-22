package com.stafor.dbsample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//매일 할 일 입력 액티비티
public class Insert_everydayActivity extends AppCompatActivity {

    private EditText insert_everyday_edit;
    private Button insert_everyday_btn;

    private DBHelper_everyday dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_everyday);

        dbHelper = new DBHelper_everyday(this);

        insert_everyday_edit = (EditText)findViewById(R.id.insert_everyday_edit);
        insert_everyday_btn = (Button)findViewById(R.id.insert_everyday_btn);

        insert_everyday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String doit = insert_everyday_edit.getText().toString();
                    dbHelper.insert(doit);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(getApplication(), "입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
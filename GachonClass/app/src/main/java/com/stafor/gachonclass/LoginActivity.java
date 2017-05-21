package com.stafor.gachonclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn, signinBtn, findidBtn, findpasswordBtn;
    EditText idEdit, passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEdit = (EditText) findViewById(R.id.edit_id);
        passwordEdit = (EditText) findViewById(R.id.edit_password);

        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 입력한 값을 바탕으로 로그인을 시도한다
                if (login(id, password)) {  // login 메소드가 true를 반환하면
                    Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                        Intent myIntent = getIntent();
                        myIntent.putExtra("id", id);
                        setResult(RESULT_OK, myIntent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {    // login 메소드가 false를 반환하면
                    Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    idEdit.setText(null);
                    passwordEdit.setText(null);
                }
            }
        });

    }

    public boolean login(String id, String password) {
        if (id.length() < 4 || password.length() < 8) {   // 아이디나 비밀번호를 입력하지 않으면
            return false;
        } else {    // 아이디와 비밀번호를 입력하면
            return true;
        }
    }
}

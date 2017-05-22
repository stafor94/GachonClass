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
    String id, password;
    final static int REQUEST_CODE = 1002;

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
                id = idEdit.getText().toString();    // 아이디를 받는다
                password = passwordEdit.getText().toString();    // 비밀번호를 받는다

                // 입력한 값을 바탕으로 로그인을 시도한다
                if (login(id, password)) {  // login 메소드가 true를 반환하면
                    Toast.makeText(getApplicationContext(), R.string.success_login, Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                        returnResult(); // 메인엑티비티로 정보를 넘기고 종료
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {    // login 메소드가 false를 반환하면
                    Toast.makeText(getApplicationContext(), R.string.fail_login, Toast.LENGTH_SHORT).show();
                    // 입력창을 초기화
                    idEdit.setText(null);
                    passwordEdit.setText(null);
                }
            }
        });

        signinBtn = (Button) findViewById(R.id.btn_signin);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
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
    // 가입하기로 이동
    public void signin() {
        Intent myIntent = new Intent(this, SigninActivity.class);
        startActivityForResult(myIntent, REQUEST_CODE);
    }
    // 메인엑티비티로 응답을 보낸다
    public void returnResult() {
        Intent myIntent = getIntent();
        myIntent.putExtra("id", id);    // id를 인텐트에 담는다
        setResult(RESULT_OK, myIntent); // RESULT_OK와 인텐트를 반환
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                id = data.getStringExtra("id");
                password = data.getStringExtra("password");
                returnResult();
            }
        }
    }
}

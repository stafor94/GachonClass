package com.stafor.gachonclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button homeBtn, classBtn, mypageBtn, loginBtn;
    HomeFragment homeFrag;
    final static int REQUEST_CODE = 1001;
    String id;
    int state = 0; // 0 = home, 1 = class, 2 = mypage, 3 = settings
    boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashAcitivty.class)); // 스플래시 화면을 보여준다

        // 앱 실행 시 홈 화면을 보여준다
        homeFrag = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFrag).commit();

        homeBtn = (Button) findViewById(R.id.btn_home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();   // 이전의 프레그먼트를 찾아서 지워준다
                state = 0;
                getSupportFragmentManager().beginTransaction().add(R.id.container, homeFrag).commit();
            }
        });
        classBtn = (Button) findViewById(R.id.btn_class);
        classBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
                state = 1;
            }
        });
        mypageBtn = (Button) findViewById(R.id.btn_mypage);
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
                state = 2;
            }
        });
        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login) {
                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(myIntent, REQUEST_CODE);
                } else {
                    login = false;
                    loginBtn.setText("로그인");
                    Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                id = data.getStringExtra("id");
                Toast.makeText(this, id + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                login = true;
                loginBtn.setText("로그아웃");
            }
        }
    }

    // container에 연결된 프레그먼트를 제거
    public void removeFragment() {
        switch (state) {
            case 0: // home
                getSupportFragmentManager().beginTransaction().remove(homeFrag).commit();
                break;
            case 1: // class
                break;
            case 2: // mypage
                break;
            case 3: // settings
                break;
            default:
                break;
        }
    }
}

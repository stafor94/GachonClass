package com.stafor.dbsample02;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//스플래쉬 액티비티
public class SplashActivity extends AppCompatActivity {

    TextView Splash_head1;
    TextView Splash_head2;
    TextView Splash_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 객체 참조
        Splash_head1 = (TextView)findViewById(R.id.Splash_head1);
        Splash_head2 = (TextView)findViewById(R.id.Splash_head2);
        Splash_bottom = (TextView)findViewById(R.id.Splash_bottom);


        // 글꼴 변경
        Splash_head1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf"));
        Splash_head2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf"));
        Splash_bottom.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Italic.ttf"));

        startLoading();


    }

    // 로딩 화면 2초뒤에 사라짐 사라지고 메인 페이지를 불러온다.
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, 2000);
    }


}

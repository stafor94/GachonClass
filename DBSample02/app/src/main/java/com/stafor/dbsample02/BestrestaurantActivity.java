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

//맛집 관리(Best Restaurant) 클래스
public class BestrestaurantActivity extends AppCompatActivity {
    //프로그램에서 사용할 위젯 및 객체 변수 선언
    TextView count_bestrestaurant;
    ImageView back_best_restaurant;
    ImageView plus_bestrestaurant;
    ListView bestrestaurant_list;
    DBHelper_bestrestaurant dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestrestaurant);

        back_best_restaurant = (ImageView)findViewById(R.id.back_best_restaurant);
        plus_bestrestaurant = (ImageView)findViewById(R.id.plus_bestrestaurant);
        bestrestaurant_list = (ListView)findViewById(R.id.bestrestaurant_list);
        count_bestrestaurant = (TextView)findViewById(R.id.count_bestrestaurant);

        //데이터베이스 핼퍼(dbhelper) 객체 생성
        dbhelper = new DBHelper_bestrestaurant(this);

        //뒤로가기 버튼을 눌렀을 때 이벤트 처리
        back_best_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                /* 액티비티간 화면 전환 효과
                  - 액티비티가 바뀔 때 페이지 슬라이딩 처리
                  - overridePendingTransition(나타날 액티비티에 적용할 액션정보, 사라질 액티비티에 적용할 액션정보
                 */
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        //"+" 버튼을 눌렀을 때 이벤트 처리
        plus_bestrestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Insert_bestrestaurantActivity.class);
                startActivity(myIntent);

            }
        });

        //DB에 저장할 수 있는 db 객체를 가져온다.
        db = dbhelper.getWritableDatabase();

        //Do_bestrestaurant.db에서 쿼리(검색)
        cursor = db.rawQuery("SELECT * FROM bestrestaurantTable;",null);
        count_bestrestaurant.setText("" + cursor.getCount());
        if(cursor.getCount()>0) {
            startManagingCursor(cursor);
            DBAdapter_bestrestaurant dbAdapter = new DBAdapter_bestrestaurant(this, cursor);
            bestrestaurant_list.setAdapter(dbAdapter);
        }

        //맛집 리스트뷰에서 맛집 하나는 선택한 경우 이벤트 처리
        bestrestaurant_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                /* Snackbar를 사용하기 위해서는 Support:design Library를 추가해야 함
                   - 설정방법
                     app --> 오른쪽 마우스 --> open module settings --> app --> dependencies --> "+" Library dependency
                 */
                Snackbar.make(view, "삭제하겠습니까?", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = cursor.getString(cursor.getColumnIndex("name"));
                        dbhelper.delete(str);
                    }
                }).show();
            }
        });
    }
}

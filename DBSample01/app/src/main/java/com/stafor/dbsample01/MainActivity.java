package com.stafor.dbsample01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText idInput, passwordInput;

    String ID, PW, dbID, dbPW;

    DBHelper mDBHelper;
    Cursor cursor;
    int REQUEST_CODE =1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this, "info.db", null, 1);

        idInput = (EditText)findViewById(R.id.id);
        passwordInput = (EditText)findViewById(R.id.pw);

        //DB에서 ID와 PW를 불러와 비교한뒤 일치하면 다음 화면으로 넘어간다.
        //만약 관리자의 ID와 일치한다면 공지사항을 관리할 수 있는 화면으로 넘어간다.
        Button btn_login=(Button)findViewById(R.id.btn_login);
        assert btn_login != null;
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ID = idInput.getText().toString();
                PW = passwordInput.getText().toString();

                SQLiteDatabase db = mDBHelper.getReadableDatabase();

                //ID검색
                cursor = db.rawQuery(
                        "select MEMB_ID, PW from MEMBER where MEMB_ID='"+ID+"';",null
                );

                cursor.moveToFirst();
                if(cursor == null){
                    Toast.makeText(getApplicationContext(),"회원 정보를 확인해 주세요.",Toast.LENGTH_LONG).show();
                }else {
                    dbID = cursor.getString(0);
                    dbPW = cursor.getString(1);
                    cursor.close();
                }


                //사용자 ID, PW확인
                //Toast.makeText(getApplicationContext(),dbID+", "+dbPW,Toast.LENGTH_LONG).show();

                if(ID.toString().equals(dbID) && PW.toString().equals(dbPW)){
                    //관리자 아이디(mana)일 때 화면전환
                    if(ID.toString().equals("mana")){
                        Intent intent=new Intent(getApplicationContext(), Menu.class);
                        intent.putExtra("ID", ID.toString());
                        startActivity(intent);
                     //관리자 아닌 사용자일 때 화면전환
                    }else{Intent intent=new Intent(getApplicationContext(), Menu_user.class);
                        intent.putExtra("ID", ID.toString());
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"회원 정보를 확인해 주세요.",Toast.LENGTH_LONG).show();
                }


            }
        });

        //회원가입 버튼
        Button btn_join=(Button)findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });

    }
}

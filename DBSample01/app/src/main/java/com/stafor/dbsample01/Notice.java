package com.stafor.dbsample01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static java.sql.DriverManager.println;

public class Notice extends AppCompatActivity {
    //public static final String TAG = "CursorAdapter";
    private static final String DATABASE_NAME="info.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="NOTICE";

    private DBHelper mDBHelper;
    private SQLiteDatabase db;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");

        //홈버튼
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        //공지등록버튼
        Button btn_up = (Button) findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notice_enroll.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listView);


        boolean isOpen = openDatabase();

        if(isOpen){
            Cursor cursor = executeRawQueryParam();
            //공지사항을 Itemlist에 띄우는 메서드
            startManagingCursor(cursor);
            String[] columns = new String[] {"_id","TITLE", "CONTENT"};
            int[] to = new int[] {R.id.notice_num_entry, R.id.title_entry, R.id.contents_entry};
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,R.layout.itemlist, cursor, columns, to);
            listView.setAdapter(mAdapter);
        }

    }


    private boolean openDatabase(){
        println("opening database[" + DATABASE_NAME + "].");
        mDBHelper = new DBHelper(this, "/sdcard/SQLiteDB/info.db", null, 1);
        db = mDBHelper.getWritableDatabase();
        return true;

    }
    private Cursor executeRawQueryParam(){
        println("\nexecuteRawQueryParam called.\n");
        String SQL = " select _id, TITLE, CONTENT "
                + " from " + TABLE_NAME;
        Cursor c1 = db.rawQuery(SQL, null);

        return c1;
    }

    }




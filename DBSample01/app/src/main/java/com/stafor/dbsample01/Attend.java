package com.stafor.dbsample01;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static java.sql.DriverManager.println;

public class Attend extends Activity {
    private static final String DATABASE_NAME="info.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="ATTEND";

    private DBHelper mDBHelper;
    private SQLiteDatabase db;
    String ID;
    String today;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        TextView texttoday = (TextView)findViewById(R.id.texttoday);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");
        today = getIntent.getStringExtra("Param1");

        texttoday.append(today);

        ListView listView = (ListView) findViewById(R.id.list_attend);

        boolean isOpen = openDatabase();

        if(isOpen){
            Cursor cursor = executeRawQueryParam();
            //공지사항을 Itemlist에 띄우는 메서드
            startManagingCursor(cursor);
            String[] columns = new String[] {"_id","MEMB_NM","AT"};
            int[] to = new int[] {R.id.id_entry,R.id.name_entry, R.id.attend_entry};
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,R.layout.attend_item, cursor, columns, to);
            listView.setAdapter(mAdapter);
            //Toast.makeText(getApplicationContext(),"데이터 오픈!",Toast.LENGTH_LONG).show();
        }



        //홈 버튼
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

    }

    private boolean openDatabase() {
        println("opening database[" + DATABASE_NAME + "].");
        mDBHelper = new DBHelper(this, "/sdcard/SQLiteDB/info.db", null, 1);
        db = mDBHelper.getWritableDatabase();
        return true;
    }
    private Cursor executeRawQueryParam(){
        println("\nexecuteRawQueryParam called.\n");
        String SQL = " select _id, MEMB_NM, AT"
                + " from " + TABLE_NAME+" , MEMBER " +
                "WHERE DATE = '"+today+"' AND _id = MEMB_NUM";
        Cursor c1 = db.rawQuery(SQL, null);

        return c1;
    }


}

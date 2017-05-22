package com.stafor.dbsample01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static java.sql.DriverManager.println;

public class Notice_user extends AppCompatActivity {

    private static final String DATABASE_NAME="info.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="NOTICE";

    private DBHelper mDBHelper;
    private SQLiteDatabase db;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_user);

        Intent getIntent = getIntent();
        ID = getIntent.getStringExtra("ID");

        //홈 버튼
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu_user.class);
                startActivity(intent);
            }
        });


        ListView listView = (ListView) findViewById(R.id.listView);


        boolean isOpen = openDatabase();

        if(isOpen){
            Cursor cursor = executeRawQueryParam();

            startManagingCursor(cursor);
            String[] columns = new String[] {"_id","TITLE", "CONTENT"};
            int[] to = new int[] {R.id.notice_num_entry, R.id.title_entry, R.id.contents_entry};
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,R.layout.itemlist, cursor, columns, to);
            listView.setAdapter(mAdapter);
        }

    }


    private boolean openDatabase(){
        println("opening database[" + DATABASE_NAME + "].");
        mDBHelper = new DBHelper(this, "info.db", null, 1);
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




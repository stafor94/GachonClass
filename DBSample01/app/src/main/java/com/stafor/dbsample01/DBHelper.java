package com.stafor.dbsample01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    Cursor cursor;

    private static final String DATABASE_NAME="info.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="infoTable";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context=context;
    }
    public void onCreate(SQLiteDatabase db){
        //DB Table을 만든다.
        //MEMBER, NOTICE, ATTEND Table생성
        db.execSQL("CREATE TABLE MEMBER(\n" +
                        "       MEMB_NUM INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "       MEMB_ID TEXT NOT NULL,\n" +
                        "       PW TEXT NOT NULL,\n" +
                        "       MEMB_NM TEXT NOT NULL,\n" +
                        "       TEL TEXT NOT NULL\n" +
                        ");"
        );
        db.execSQL("CREATE TABLE NOTICE(\n" +
                        "       _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "       TITLE TEXT NOT NULL,\n" +
                        "       CONTENT TEXT,\n" +
                        "       MEMB_NUM INTEGER NOT NULL,\n" +
                        "       \n" +
                        "       FOREIGN KEY (MEMB_NUM) REFERENCES MEMBER(MEMB_NUM)\n" +
                        ");"
        );
        db.execSQL(
               "CREATE TABLE ATTEND(\n" +
                       "       DATE TEXT NOT NULL,\n" +
                       "       _id INTEGER NOT NULL,\n" +
                       "       AT TEXT NOT NULL,\n" +
                       "       \n" +
                       "        PRIMARY KEY(DATE, _id)" +
                       "       FOREIGN KEY (_id) REFERENCES MEMBER(MEMB_NUM)\n" +
                       "       \n" +
                       "       \n" +
                       ");"
        );

        Toast.makeText(context, "onCreate() 메소드 호출", Toast.LENGTH_LONG).show();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Toast.makeText(context, "onUpgrade() 메소드 호출", Toast.LENGTH_LONG).show();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

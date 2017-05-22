package com.stafor.dbsample02;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//약속 DBHelper 클래스
public class DBHelper_promise extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    Cursor cursor;


    // Database name
    private static final String DATABASE_NAME = "Do_promise.db";
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Table name
    private static final String TABLE_NAME = "promiseTable";

    //DBHelper 생성자(Context, DBname, cursor, DBversion)
    public DBHelper_promise(Context context) {
        // 데이터베이스 이름과 버전 정보를 이용하여 상위 생성자 호출
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /* 데이터 베이스 create
    *  - 생성자에서 넘겨 받은 이름의 DB와 버전의 DB가 존재하지 않을 때 호출
    *  - 새로운 데이터 베이스를 생성할때 사용*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* 테이블을 생성하기 위해 sql문으로 작성하여 execSQL 문 실행 */
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, year INTEGER, month INTEGER, day INTEGER, hour INTEGER, minute INTEGER, content TEXT);");

    }

     /* 데이터베이스 Version Upgrade
    *  - DB 가 존재하지만 버전이 다르면 호출됨
    *  - DB를 변경하고, 버전읇 변경할때 여러가지 업그레이드 작업 수행 가능*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* 테이블을 업그레이드 하기 위해 SQL문을 작성하여 execSQL문 실행
        *  - 기존의 테이블을 삭제한 후 테이블 재생성*/
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    // 테이블의 레코드 insert
    public void insert(int year, int month, int day, int hour, int minute, String content) {
        db = getWritableDatabase();

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES(null, " + year + ", " + month + "," + day + "," + hour + "," + minute + ",'" + content + "');");
        db.close();
    }

    // 테이블의 레코드 delete
    public void delete(String content) {
        db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE content  = '" + content + "';");
        db.close();
    }

    public String printData1() {
        db = getReadableDatabase();
        String str=" ";

        cursor = db.rawQuery("SELECT content FROM " + TABLE_NAME + ";", null);
        if ( cursor.moveToPosition(0) == false ) {
            str = " ";
        } else {
            str += cursor.getString(0);
        }
        cursor.close();
        return str;
    }
    public String printData2() {
        db = getReadableDatabase();
        String str=" ";

        cursor = db.rawQuery("SELECT content FROM " + TABLE_NAME + ";", null);
        if ( cursor.moveToPosition(1) == false ) {
            str = " ";
        } else {
            str += cursor.getString(0);
        }
        cursor.close();
        return str;
    }
    public String printData3() {
        db = getReadableDatabase();
        String str=" ";

        cursor = db.rawQuery("SELECT content FROM " + TABLE_NAME + ";", null);
        if ( cursor.moveToPosition(2) == false ) {
            str = " ";
        } else {
            str += cursor.getString(0);
        }
        cursor.close();
        return str;
    }
}

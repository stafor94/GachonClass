package com.stafor.dbsample02;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

//매일 할일 DBAdapter 커서 클래스
public class DBAdapter_everyday extends CursorAdapter {

    public DBAdapter_everyday(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        final TextView te2 = (TextView)view.findViewById(R.id.everyday_list_item2);


        te2.setText(cursor.getString(cursor.getColumnIndex("content")));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listlayout_everyday, parent, false);
        return v;
    }
}

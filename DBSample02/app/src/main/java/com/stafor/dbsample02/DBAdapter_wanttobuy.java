package com.stafor.dbsample02;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

//버킷리스트 DBAdapter 커서 클래스
public class DBAdapter_wanttobuy extends CursorAdapter {

    public DBAdapter_wanttobuy(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        final TextView te_want2 = (TextView)view.findViewById(R.id.wanttobuy_list_item2);
        final TextView te_want3 = (TextView)view.findViewById(R.id.wanttobuy_list_item3);


        te_want2.setText(cursor.getString(1));
        te_want3.setText(cursor.getString(2));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listlayout_wanttobuy, parent, false);
        return v;
    }
}

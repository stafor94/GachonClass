package com.stafor.dbsample02;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

//디-데이 DBAdapter 커서 클래스
public class DBAdapter_dday extends CursorAdapter {

    public DBAdapter_dday(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final TextView te_d1 = (TextView)view.findViewById(R.id.dday_list_item1);
        final TextView te_d2 = (TextView)view.findViewById(R.id.dday_list_item2);
        final TextView te_d3 = (TextView)view.findViewById(R.id.dday_list_item3);
        final TextView te_d4 = (TextView)view.findViewById(R.id.dday_list_item4);

        te_d1.setText(cursor.getString(1));
        te_d2.setText(cursor.getString(2));
        te_d3.setText(cursor.getString(3));
        te_d4.setText(cursor.getString(4));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listlayout_dday, parent, false);
        return v;
    }
}

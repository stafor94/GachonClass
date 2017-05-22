package com.stafor.dbsample02;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

//약속 DBAdapter 커서 클래스
public class DBAdapter_promise extends CursorAdapter {

    public DBAdapter_promise(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final TextView te_pro2 = (TextView)view.findViewById(R.id.promise_list_item2);
        final TextView te_pro3 = (TextView)view.findViewById(R.id.promise_list_item3);
        final TextView te_pro4 = (TextView)view.findViewById(R.id.promise_list_item4);
        final TextView te_pro5 = (TextView)view.findViewById(R.id.promise_list_item5);
        final TextView te_pro6 = (TextView)view.findViewById(R.id.promise_list_item6);
        final TextView te_pro7 = (TextView)view.findViewById(R.id.promise_list_item7);


        te_pro2.setText(cursor.getString(1));
        te_pro3.setText(cursor.getString(2));
        te_pro4.setText(cursor.getString(3));
        te_pro5.setText(cursor.getString(4));
        te_pro6.setText(cursor.getString(5));
        te_pro7.setText(cursor.getString(6));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listlayout_promise, parent, false);
        return v;
    }
}

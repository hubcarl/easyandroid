package com.easy.team.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String name = "easy_data";
    private static Integer version = 1;


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table easy_favorite(id integer primary key autoincrement, title varchar(256), url varchar(1024), status integer, createtime datetime)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

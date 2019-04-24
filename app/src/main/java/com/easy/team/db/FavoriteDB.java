package com.easy.team.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.easy.team.utils.DatabaseHelper;

public class FavoriteDB {

    private SQLiteDatabase db;
    private DatabaseHelper helper;

    public FavoriteDB(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        Log.d("--database--",  db.getPath());
    }

    public long add(String title, String url) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("url", url);
        return db.insert("easy_favorite", null, cv);
    }
}

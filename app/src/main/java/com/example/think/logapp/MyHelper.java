package com.example.think.logapp;

/**
 * Created by Think on 2017/12/6.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Think on 2017/12/6.
 */

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "logInfo.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  LogInformation(_id INTEGER PRIMARY KEY AUTOINCREMENT ,logName char(30),logContext char(200))");
        /*Toast.makeText(mContext,"Create Successded",0).show();*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}



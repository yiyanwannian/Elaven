package com.example.helloketty.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by HelloKetty on 2018/5/10.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String dbname = "elaven";
    private static int version = 1;

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }

    // 变成全局单例
    public static DatabaseHelper Instance(Context context) {
        synchronized (DatabaseHelper.class) {
            if (instance == null) {
                instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // 用户草稿信息
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS text_userinfo(name text,user_id text,gender text,email text,phone_number text,address text)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


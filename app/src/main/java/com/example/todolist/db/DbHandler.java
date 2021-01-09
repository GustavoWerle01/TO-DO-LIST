package com.example.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todo_list";
    public static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";

    public DbHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCmd = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, title VARCHAR(50), description TEXT, finished VARCHAR(5), priority_level INTEGER, created_at DATE, complete_in DATE)";
        db.execSQL(sqlCmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlCmdDrop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String sqlCmd = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, title VARCHAR(50), description TEXT, finished VARCHAR(5), priority_level INTEGER, created_at DATE, complete_in DATE)";
        db.execSQL(sqlCmdDrop);
        db.execSQL(sqlCmd);
    }
}

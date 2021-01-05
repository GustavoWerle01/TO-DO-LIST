package com.example.todolist.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class CrudHelperImpl implements CRUDHelper<Todo> {

    private final DbHandler handler;
    private static final String TABLE_NAME = "tasks";

    public CrudHelperImpl(DbHandler handler) {
        this.handler = handler;
    }

    @Override
    public void create(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("description", todo.getDescription());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                list.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public void delete(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();
        db.delete(TABLE_NAME, " id = ?", new String[] { String.valueOf(todo.getId())});
        db.close();
    }
}

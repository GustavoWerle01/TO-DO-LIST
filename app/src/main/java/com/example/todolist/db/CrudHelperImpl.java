package com.example.todolist.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class CrudHelperImpl implements CRUDHelper<Todo> {

    private DbHandler handler = null;
    private static final String TABLE_NAME = "todos";

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
    public Todo findById(int id) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {"id", "title", "description"},
                "id = ?", new String[] {(String.valueOf(id))}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Todo todo = new Todo(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        return todo;
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<Todo>();
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                list.add(todo);
            } while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public int update(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("description", todo.getDescription());
        return db.update(TABLE_NAME, values, " id = ?", new String[] { String.valueOf(todo.getId())});
    }

    @Override
    public void delete(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();
        db.delete(TABLE_NAME, " id = ?", new String[] { String.valueOf(todo.getId())});
        db.close();
    }
}

package com.example.todolist.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository<Todo> {

    private final DbHandler handler;
    private static final String TABLE_NAME = "tasks";

    public TodoRepository(DbHandler handler) {
        this.handler = handler;
    }

    @Override
    public void create(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", todo.title);
        values.put("description", todo.description);
        values.put("finished", todo.finished);
        values.put("priority_level", todo.priority_level);
//        values.put("created_at", todo.created_at.toString());
//        values.put("complete_in", todo.complete_in.toString());

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
                Todo todo = new Todo();
                todo.id = cursor.getLong(0);
                todo.title = cursor.getString(1);
                todo.description = cursor.getString(2);
                todo.finished = cursor.getString(3).equals("true");
                todo.priority_level = cursor.getInt(4);
//                todo.created_at = cursor.getString(5);
//                todo.complete_in = cursor.getString(6);
                list.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public void delete(Todo todo) {
        SQLiteDatabase db = this.handler.getWritableDatabase();
        db.delete(TABLE_NAME, " id = ?", new String[] { String.valueOf(todo.id)});
        db.close();
    }

    @Override
    public void update(Todo todo) {
//        SQLiteDatabase db = this.handler.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        ContentValues values = new ContentValues();
//        values.put("title", todo.title);
//        values.put("description", todo.description);
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
    }
}

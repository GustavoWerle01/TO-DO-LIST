package com.example.todolist.db;

import java.util.List;

public interface CRUDHelper<T> {
    void create(T klazz);
    List<T> findAll();
    void delete(T klazz);
}

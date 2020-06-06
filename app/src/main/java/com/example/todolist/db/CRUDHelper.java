package com.example.todolist.db;

import java.util.List;

public interface CRUDHelper<T> {
    public void create(T klazz);
    public T findById(int id);
    public List<T> findAll();
    public int update(T klazz);
    public void delete(T klazz);
}

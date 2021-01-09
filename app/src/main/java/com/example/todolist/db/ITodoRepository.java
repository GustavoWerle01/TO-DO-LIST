package com.example.todolist.db;

import java.util.List;

public interface ITodoRepository<T> {
    void create(T todo);
    List<T> findAll();
    void delete(T todo);
    void update(T todo);
}

package com.example.todolist.entities;

import java.util.Date;

public class Todo {

    public Long id;
    public String title;
    public String description;
    public Boolean finished;
    public Integer priority_level; // 1=High, 2=Medium, 3=Low
    public Date created_at;
    public Date complete_in;

}

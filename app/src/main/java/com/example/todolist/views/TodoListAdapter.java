package com.example.todolist.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.entities.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewerHolder>{

    private Context ctx;
    private List<Todo> todoList;

    public TodoListAdapter(Context ctx, List<Todo> todoList) {
        this.ctx = ctx;
        this.todoList = todoList;
    }

    @Override
    public TodoViewerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoViewerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todos, parent, false));
    }

    @Override
    public void
     onBindViewHolder(TodoViewerHolder holder, int pos) {
        holder.bindElements(this.ctx, todoList.get(pos));
    }

    @Override
    public int getItemCount() {
        if (todoList == null) {
            return 0;
        }

        return todoList.size();
    }
}

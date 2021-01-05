package com.example.todolist.views;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.entities.Todo;

class TodoViewerHolder extends RecyclerView.ViewHolder {

    private TextView txtTodoTitle;
    private TextView txtTodoDesc;

    public TodoViewerHolder(View view) {
        super(view);
        findUsefulComponents(view);
    }

    private void findUsefulComponents(View view) {
        txtTodoTitle = (TextView) view.findViewById(R.id.todo_list);
        txtTodoDesc = (TextView) view.findViewById(R.id.todo_desc);
    }

    public void fill(Todo todo){
        txtTodoTitle.setId(Integer.parseInt(todo.getId().toString()));
        txtTodoTitle.setText(todo.getTitle());
        txtTodoDesc.setText(todo.getDescription());
    }
}

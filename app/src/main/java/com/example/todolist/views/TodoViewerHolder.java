package com.example.todolist.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.entities.Todo;

class TodoViewerHolder extends RecyclerView.ViewHolder {

    private TextView txtTodoId;
    private TextView txtTodoTitle;
    private TextView txtTodoDesc;

    public TodoViewerHolder(View view) {
        super(view);

        this.txtTodoTitle = (TextView) view.findViewById(R.id.todo_list);
        this.txtTodoDesc = (TextView) view.findViewById(R.id.todo_desc);
        this.txtTodoId = (TextView) view.findViewById(R.id.todo_id);
    }

    public void bindElements(Context ctx, Todo todo){
        txtTodoTitle.setId(Integer.parseInt(todo.getId().toString()));
        txtTodoTitle.setText(todo.getTitle());
        txtTodoDesc.setText(todo.getDescription());
    }
}

package com.example.todolist.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.entities.Todo;

class TodoViewerHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private TextView txtTodoTitle;
    private TextView txtTodoDesc;
    private CardView cardColor;

    public TodoViewerHolder(View view) {
        super(view);
        context = view.getContext();
        findUsefulComponents(view);
    }

    private void findUsefulComponents(View view) {
        txtTodoTitle = (TextView) view.findViewById(R.id.todo_list);
        txtTodoDesc = (TextView) view.findViewById(R.id.todo_desc);
        cardColor = view.findViewById(R.id.cardListTodoColor);
    }

    public void fill(Todo todo) {
        txtTodoTitle.setText(todo.title);
        txtTodoDesc.setText(todo.description);
        setCardColor(todo.priority_level);
    }

    private void setCardColor(Integer priority_level) {
        switch (priority_level) {
            case 1:
                cardColor.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed, null));
                break;
            case 2:
                cardColor.setCardBackgroundColor(context.getResources().getColor(R.color.colorOrange, null));
                break;
            case 3:
                cardColor.setCardBackgroundColor(context.getResources().getColor(R.color.colorBlue, null));
                break;
            default:
                cardColor.setCardBackgroundColor(context.getResources().getColor(R.color.colorGray, null));
                break;
        }
    }
}

package com.example.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.db.CrudHelperImpl;
import com.example.todolist.db.DbHandler;
import com.example.todolist.entities.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_task_title;
    private EditText txt_task_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        FloatingActionButton btn_create_tak = findViewById(R.id.btn_create_task);
        btn_create_tak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            this.txt_task_title = (EditText) findViewById(R.id.txt_task_title);
            this.txt_task_description = (EditText) findViewById(R.id.txt_task_desc);

            if (this.txt_task_title.getText().toString().equals("")) {
                throw new Exception("Insira o título da tarefa");
            }

            if (this.txt_task_description.getText().toString().trim().equals("")) {
                throw new Exception("Insira a descrição da tarefa");
            }

            DbHandler handler = new DbHandler(this);
            CrudHelperImpl db = new CrudHelperImpl(handler);

            Todo todo = new Todo();
            todo.setTitle(this.txt_task_title.getText().toString());
            todo.setDescription(this.txt_task_description.getText().toString());
            db.create(todo);

            Intent addActivity = new Intent(this, MainActivity.class);
            startActivity(addActivity);
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


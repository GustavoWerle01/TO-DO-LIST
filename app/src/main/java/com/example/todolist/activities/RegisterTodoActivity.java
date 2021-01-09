package com.example.todolist.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.R;
import com.example.todolist.db.TodoRepository;
import com.example.todolist.db.DbHandler;
import com.example.todolist.entities.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterTodoActivity extends AppCompatActivity {

    private EditText txt_task_title;
    private EditText txt_task_description;
    private ImageView image_voltar;
    private FloatingActionButton fab_salvar;
    private RadioButton radio_high;
    private RadioButton radio_medium;
    private RadioButton radio_low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findUsefulComponents();
        setImageVoltarBehaviour();
        setFabSalvarBehaviour();
    }

    private void setFabSalvarBehaviour() {
        fab_salvar.setOnClickListener(v -> {
            try {
                validateData();
                saveTodoIntoDB();
                finish();
                Toast.makeText(this, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveTodoIntoDB() {
        DbHandler handler = new DbHandler(this);
        TodoRepository db = new TodoRepository(handler);
        Todo todo = new Todo();
        todo.title = txt_task_title.getText().toString();
        todo.description = txt_task_description.getText().toString();
        todo.finished = false;
        if (radio_high.isChecked()) {
            todo.priority_level = 1;
        } else if (radio_medium.isChecked()) {
            todo.priority_level = 2;
        } else {
            todo.priority_level = 3;
        }
        db.create(todo);
    }

    private void validateData() throws Exception {
        if (this.txt_task_title.getText().toString().equals(""))
            throw new Exception("Insira o título da tarefa");
        if (this.txt_task_description.getText().toString().trim().equals(""))
            throw new Exception("Insira a descrição da tarefa");
    }

    private void setImageVoltarBehaviour() {
        image_voltar.setOnClickListener(v -> finish());
    }

    private void findUsefulComponents() {
        image_voltar = findViewById(R.id.imageAddVoltar);
        fab_salvar = findViewById(R.id.btn_create_task);
        txt_task_title = findViewById(R.id.txt_task_title);
        txt_task_description = findViewById(R.id.txt_task_desc);
        radio_high = findViewById(R.id.radioButtonAddPriorityHigh);
        radio_medium = findViewById(R.id.radioButtonAddPriorityMedium);
        radio_low = findViewById(R.id.radioButtonAddPriorityLow);
    }
}


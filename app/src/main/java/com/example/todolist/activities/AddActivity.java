package com.example.todolist.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.R;
import com.example.todolist.db.CrudHelperImpl;
import com.example.todolist.db.DbHandler;
import com.example.todolist.entities.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    private EditText txt_task_title;
    private EditText txt_task_description;
    private ImageView image_voltar;
    private FloatingActionButton fab_salvar;

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
            } catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveTodoIntoDB() {
        DbHandler handler = new DbHandler(this);
        CrudHelperImpl db = new CrudHelperImpl(handler);
        Todo todo = new Todo();
        todo.setTitle(txt_task_title.getText().toString());
        todo.setDescription(txt_task_description.getText().toString());
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
        txt_task_title = (EditText) findViewById(R.id.txt_task_title);
        txt_task_description = (EditText) findViewById(R.id.txt_task_desc);
    }
}


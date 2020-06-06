package com.example.todolist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.todolist.R;
import com.example.todolist.db.CRUDHelper;
import com.example.todolist.db.CrudHelperImpl;
import com.example.todolist.db.DbHandler;
import com.example.todolist.entities.Todo;
import com.example.todolist.views.TodoListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Todo> todos = new ArrayList<Todo>();
    private TodoListAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        populateRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent addActivity = new Intent(this, AddActivity.class);
        startActivity(addActivity);
    }

    private void populateRecyclerView(){
        DbHandler handler = new DbHandler(this);
        CrudHelperImpl db = new CrudHelperImpl(handler);
        todos = db.findAll();
        todoAdapter = new TodoListAdapter(this, todos);
        RecyclerView todo_list = (RecyclerView) findViewById(R.id.todo_list);

        todo_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        todo_list.setItemAnimator(new DefaultItemAnimator());
        todo_list.setAdapter(todoAdapter);
        setItemAnimation();
    };

    private void setItemAnimation() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteTodo(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        RecyclerView todo_list = (RecyclerView) findViewById(R.id.todo_list);
        itemTouchHelper.attachToRecyclerView(todo_list);
    }

    public void deleteTodo(final int pos){
        new AlertDialog.Builder(this)
                .setTitle("To do List")
                .setMessage("Deseja realmente excluir a tarefa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int btn) {
                        dialog.dismiss();
                        deleteTodoFromDB(pos);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        populateRecyclerView();
                    }
                })
                .show();
    }

    public void deleteTodoFromDB(int pos) {
        DbHandler handler = new DbHandler(this);
        CrudHelperImpl db = new CrudHelperImpl(handler);

        Todo todo = todos.get(pos);
        db.delete(todo);
        Toast.makeText(this, "Deletado com sucesso", Toast.LENGTH_SHORT).show();
        populateRecyclerView();
    }
}
